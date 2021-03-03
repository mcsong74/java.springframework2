package com.cybertek.implementation;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.entity.Project;
import com.cybertek.mapper.ProjectMapper;
import com.cybertek.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    @Mock
    ProjectRepository projectRepository;
    @Mock
    ProjectMapper projectMapper;

    @InjectMocks
    ProjectServiceImpl projectService;


    @Test
    void getByProjectCode() {
        Project project = new Project(); //empty project entity
        ProjectDTO projectDTO = new ProjectDTO();

        when(projectRepository.findByProjectCode("PR01")).thenReturn(project);
        when(projectMapper.convertToDto(project)).thenReturn(projectDTO);

        ProjectDTO projectDTO1 = projectService.getByProjectCode("PR01");
        verify(projectRepository).findByProjectCode(Mockito.anyString());
        verify(projectMapper).convertToDto(Mockito.any(Project.class));

        verify(projectService).getByProjectCode(Mockito.anyString());

        assertNotNull(projectDTO1);

    }

    @Test
    void getByProjectsCode_exceptionTest(){
        when(projectRepository.findByProjectCode("")).thenThrow(new RuntimeException("Project Not Found"));

        Throwable exception = assertThrows(RuntimeException.class, ()-> projectService.getByProjectCode(""));

        verify(projectRepository).findByProjectCode(Mockito.anyString());

        assertEquals(exception.getMessage(), "Project Not Found");

    }



}