package com.cybertek.service;

import com.cybertek.entity.User;
import com.cybertek.enums.UserState;
import com.cybertek.exception.ServiceException;
import com.cybertek.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    //injection with constructor
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    //service implementation --------------------------------------------------------
    public User readByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
        //without .orElse(...), returns Optional

    }
    public User readByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
        //without .orElse(...), returns Optional
    }
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional //ddl(delete, update), handles error, any changes in database, good to add
    public User createUser(User user) throws ServiceException {
        User foundUserByEmail = readByEmail(user.getEmail());
        User foundUserByUsername = readByUsername(user.getUsername());
        if(foundUserByEmail != null) { //if duplicate, throws exception
            throw new ServiceException("This user already exists, please change your email");
        }
        if(foundUserByUsername != null) {//if duplicate, throws exception
            throw new ServiceException("This user already exists, please change your username");
        }
        //set new password for a new user
        user.setState(UserState.ACTIVE);
        user.setIsDeleted(false);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setIsVerified(false); //after verification, will be changing to true
        return userRepository.save(user);
    }
    @Transactional //ddl(delete, update), handles error, any changes in database, good to add
    public User verifyUser(User user) {
        user.setIsVerified(true);
        user.setState(UserState.ACTIVE);
        return userRepository.save(user);
    }
    @Transactional //ddl(delete, update), prevents/handles error, any changes in database, good to add
    public void deleteUser(Integer id) throws ServiceException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) { //if user not exist, throws exception
            throw new ServiceException("This user does not exist");
        }
        user.setIsVerified(false);
        userRepository.save(user);
    }
    @Transactional//ddl(delete, update), prevents/handles error, any changes in database, good to add
    public User resetPassword(User user) throws ServiceException {
        User foundUser = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (foundUser == null) {  //if user by email not exist, throws exception
            throw new ServiceException("User with email does not exists: " + user.getEmail());
        }
        foundUser.setState(UserState.ACTIVE);
        //update password
        foundUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(foundUser);
    }
}
