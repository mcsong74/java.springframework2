package com.cybertek.implementation;

import com.cybertek.mapper.MapperUtil;
import com.cybertek.repository.UserRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // to use Mockito, this annotation is required
@Tag("regression")
class UserServiceImplTest {

    @Mock
    UserRepository userRepository; //deleteByUserName using UserRepository, need to mock the repository
    @Mock
    MapperUtil mapperUtil;

    @InjectMocks //above Mocks will be injected below UserServiceImpl
    UserServiceImpl userService; //has dependency to UserRepository(in UserServiceImplementation, UserRepository is
    // injected and which created dependency, so need to add @InjectMocks annotation.

    @Test
    void deleteByUserName() {
        //call method from service implementation for testing
        userService.deleteByUserName("mike@cybertek.com");
        //verification
//        verify(userRepository).deleteByUserName("mike@cybertek.com");
//        verify(userRepository, times(2)).deleteByUserName("mike@cybertek.com");
//        verify(userRepository, atLeastOnce()).deleteByUserName("mike@cybertek.com");
        verify(userRepository, atMost(5)).deleteByUserName("mike@cybertek.com");


    }
}