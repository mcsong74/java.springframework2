package com.cybertek.repository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface DataRepository {
    int[] findAll();
    int[] findById(int id);
}
