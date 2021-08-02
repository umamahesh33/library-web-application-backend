package com.minorproject.libraryapplication.services;

import com.minorproject.libraryapplication.Model.applicationUser;
import com.minorproject.libraryapplication.repositories.userRepository;
import com.minorproject.libraryapplication.security.userCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userService implements UserDetailsService{

    @Autowired
    userCache userCache;

    @Autowired
    userRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        applicationUser user = userCache.getUserFromCache(username);
        if (user == null) {
            user = (applicationUser) userRepository.findByUsername(username);
            userCache.setUserInCache(user);
        }
        return user;
    }
}
