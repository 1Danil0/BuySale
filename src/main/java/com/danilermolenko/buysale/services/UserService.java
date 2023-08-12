package com.danilermolenko.buysale.services;

import com.danilermolenko.buysale.dto.UserDto;
import com.danilermolenko.buysale.entities.Role;
import com.danilermolenko.buysale.entities.User;
import com.danilermolenko.buysale.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public boolean addUser(UserDto userDto){
        User user = new User();
        String email = userDto.getEmail();
        if(userRepository.findByEmail(email) != null){
            return false;
        }
        user.getRoles().add(Role.ROLE_USER);
        user.setName(userDto.getName());
        user.setActive(true);
        user.setUsername(email);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPhoneNumber(userDto.getPhoneNumber());
        userRepository.save(user);
        return true;
    }
}
