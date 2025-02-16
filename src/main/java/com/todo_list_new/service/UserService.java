package com.todo_list_new.service;

import com.todo_list_new.configuration.SecurityUtils;
import com.todo_list_new.exception.UserAlreadyExistsException;
import com.todo_list_new.exception.UserNotFoundException;
import com.todo_list_new.exception.ValidationException;
import com.todo_list_new.mapper.UserMapper;
import com.todo_list_new.model.Users;
import com.todo_list_new.model.dto.UserLoginDTO;
import com.todo_list_new.model.dto.UserRegistrationDTO;
import com.todo_list_new.model.dto.UserRequestDTO;
import com.todo_list_new.model.dto.UserResponseDTO;
import com.todo_list_new.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Transactional
    public UserResponseDTO register(UserRegistrationDTO userRegistrationDTO) {
        if (userRegistrationDTO==null || userRegistrationDTO.getName()==null ||
                userRegistrationDTO.getPassword()==null || userRegistrationDTO.getEmail()==null) {
            throw new ValidationException("Registration data cannot be null");
        }
        if (userRepository.existsByName(userRegistrationDTO.getName())){
            throw new UserAlreadyExistsException("User already exists");
        }
        Users user = new Users();
        user.setName(userRegistrationDTO.getName());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setEmail(userRegistrationDTO.getEmail());

        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Transactional
    public String verify(UserLoginDTO userLoginDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getName(), userLoginDTO.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userLoginDTO.getName());
        }
        return "fail";
    }

    public UserResponseDTO findById(int id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Transactional
    public void deleteById(int id) {
        Users user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found"));
        userRepository.delete(user);
    }

    public UserResponseDTO changePassword(UserRequestDTO userRequestDTO) {
        String username = SecurityUtils.getCurrentUsername();
        Users user = userRepository.findByName(username).orElseThrow(() ->
                new UserNotFoundException("User not found with name " + username));

        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        userRepository.save(user);
        return userMapper.toDTO(user);
    }
}
