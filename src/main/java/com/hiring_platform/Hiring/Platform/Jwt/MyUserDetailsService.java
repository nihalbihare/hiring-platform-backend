package com.hiring_platform.Hiring.Platform.Jwt;

import com.hiring_platform.Hiring.Platform.dto.UserDTO;
import com.hiring_platform.Hiring.Platform.exception.JobPortalException;
import com.hiring_platform.Hiring.Platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            UserDTO userDTO = userService.getUserByEmail(email);
            return new CustomUserDetails(userDTO.getId(),email,userDTO.getName(),
                    userDTO.getPassword(),userDTO.getProfileId(),
                    userDTO.getAccountType(),new ArrayList<>());
        } catch (JobPortalException e) {
            e.printStackTrace();
        }
        return null;


    }
}
