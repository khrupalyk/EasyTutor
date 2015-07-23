package com.easytutor.service;

import com.easytutor.dao.UserDAO;
import com.easytutor.models.User;
import com.easytutor.utils.ApplicationContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 23.07.15.
 */

@Service("userService")
public class MyUserDetailsService implements UserDetailsService {


    UserDAO userDAO = (UserDAO) ApplicationContextProvider.getApplicationContext().getBean(UserDAO.class);


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDAO.getUserByName(s);

        List<GrantedAuthority> authority = new ArrayList<>();

        user.getRoles().forEach(e -> authority.add(new SimpleGrantedAuthority(e.getRole())));

        org.springframework.security.core.userdetails.User springUser
                = new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.isEnabled(), true, true, true, authority);

        return springUser;
    }
}
