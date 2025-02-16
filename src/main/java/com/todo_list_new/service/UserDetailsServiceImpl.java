package com.todo_list_new.service;

import com.todo_list_new.model.UserPrincipical;
import com.todo_list_new.model.Users;
import com.todo_list_new.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByName(username).orElseThrow(() ->
                new UsernameNotFoundException(username));

        return new UserPrincipical(user);
    }
}
