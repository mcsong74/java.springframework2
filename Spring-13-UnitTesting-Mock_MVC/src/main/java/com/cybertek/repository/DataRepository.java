package com.cybertek.repository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public interface DataRepository {
    int[] findAll();
}
