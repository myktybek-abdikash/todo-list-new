package com.todo_list_new.service;

import com.todo_list_new.configuration.SecurityUtils;
import com.todo_list_new.exception.InvalidPasswordException;
import com.todo_list_new.exception.UserAlreadyExistsException;
import com.todo_list_new.exception.UserNotFoundException;
import com.todo_list_new.exception.ValidationException;
import com.todo_list_new.mapper.UserMapper;
import com.todo_list_new.model.Users;
import com.todo_list_new.model.dto.user.*;
import com.todo_list_new.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public UserResponseDTO register(UserRegistrationDTO userRegistrationDTO) {
        if (userRegistrationDTO==null || userRegistrationDTO.getName()==null ||
                userRegistrationDTO.getPassword()==null || userRegistrationDTO.getEmail()==null) {
            throw new ValidationException("Registration data cannot be null");
        }
        if (userRepository.existsByName(userRegistrationDTO.getName())){
            throw new UserAlreadyExistsException("User already exists");
        }

        try {
            Users user = new Users();
            user.setName(userRegistrationDTO.getName());
            user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
            user.setEmail(userRegistrationDTO.getEmail());

            userRepository.save(user);
            return userMapper.toDTO(user);
        } catch (DataAccessException e){
            throw new RuntimeException("Service exception", e);
        }
    }

    @Transactional
    public String verify(UserRequestDTO userRequestDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userRequestDTO.getName(), userRequestDTO.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userRequestDTO.getName());
        }
        return "fail";
    }

    public UserResponseDTO findById(int id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Transactional
    public UserResponseDTO updateById(int id, UserUpdateDTO userUpdateDTO) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));

        if (userUpdateDTO == null || userUpdateDTO.getName() == null || userUpdateDTO.getEmail() == null) {
            throw new ValidationException("Updating data cannot be null");
        }

        if (!user.getName().equals(userUpdateDTO.getName()) &&
                userRepository.existsByName(userUpdateDTO.getName())) {
            throw new UserAlreadyExistsException("Username already in use");
        }

        if (!user.getEmail().equals(userUpdateDTO.getEmail()) &&
                userRepository.existsByEmail(userUpdateDTO.getEmail())) {
            throw new UserAlreadyExistsException("Email already in use");
        }

        userMapper.toUpdatedEntity(userUpdateDTO, user);
        return userMapper.toDTO(user);
    }

    @Transactional
    public ChangePasswordResponseDTO changePassword(ChangePasswordRequestDTO changePasswordRequestDTO) {
        String username = SecurityUtils.getCurrentUsername();
        Users user = userRepository.findByName(username).orElseThrow(() ->
                new UserNotFoundException("User not found with name " + username));

        if (!passwordEncoder.matches(changePasswordRequestDTO.getOldPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Old password is incorrect");
        }

        if (passwordEncoder.matches(changePasswordRequestDTO.getNewPassword(), user.getPassword())) {
            throw new InvalidPasswordException("New password must be different from the old one");
        }

        user.setPassword(passwordEncoder.encode(changePasswordRequestDTO.getNewPassword()));
        userRepository.save(user);

        return new ChangePasswordResponseDTO("Password changed successfully");
    }

    @Transactional
    public void deleteById(int id) {
        Users user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found with id " + id));
        userRepository.delete(user);
    }
}
