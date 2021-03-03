package com.cybertek.implementation;

import com.cybertek.dto.TaskDTO;
import com.cybertek.entity.Task;
import com.cybertek.mapper.TaskMapper;
import com.cybertek.repository.TaskRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Tag("regression")
class TaskServiceImplTest {
    @Mock
    TaskRepository taskRepository;
    @Mock
    TaskMapper taskMapper;

    @InjectMocks
    TaskServiceImpl taskService;


    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L})
    void findByIdTest(long arg){
        Task task = new Task();
        TaskDTO taskDTO = new TaskDTO();

        when(taskRepository.findById(arg)).thenReturn(Optional.of(task));
        when(taskMapper.convertToDto(task)).thenReturn(taskDTO);

        TaskDTO taskDTO1 = taskService.findById(arg);

        verify(taskRepository).findById(arg);

        assertNotNull(taskDTO1);

    }
    @Test
    void findByIdBddTest(){
        //given
        Task task = new Task();
        given(taskRepository.findById(Mockito.anyLong())).willReturn(Optional.of(task));
        given(taskMapper.convertToDto(task)).willReturn(new TaskDTO());
        //when
        TaskDTO taskDTO = taskService.findById(Mockito.anyLong());
        //then
        then(taskRepository).should().findById(Mockito.anyLong());
        then(taskRepository).should(never()).findById(-5L);

        assertNull(taskDTO);
    }

}