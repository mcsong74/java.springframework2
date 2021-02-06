package com.cybertek.service;

import com.cybertek.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityService implements UserDetailsService {

    private UserService userService;

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String str) throws UsernameNotFoundException {
        //get the user from database and sent it to user principle if login form exists
        //for this prj, no form exists
        User foundUser = loadUser(str);
        if (foundUser == null){
            throw new UsernameNotFoundException("User not found! " + str);
        }
        //with form, will return new UserPrinciple()
        return new org.springframework.security.core.userdetails
                .User(foundUser.getUsername(), foundUser.getPassword(), listAuthority(foundUser));
    }

    public User loadUser(String value){
        boolean isEmail = value.contains("@");
        return isEmail? userService.readByEmail(value):userService.readByUsername(value);
    }

    private List<GrantedAuthority> listAuthority(User user){
        List<GrantedAuthority> grantedAuthoritiesList=new ArrayList<>();
        grantedAuthoritiesList.add(new SimpleGrantedAuthority(user.getRole().name()));
        return grantedAuthoritiesList;
    }


}
