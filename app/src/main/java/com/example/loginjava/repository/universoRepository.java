package com.example.loginjava.repository;

import com.example.loginjava.database.entity.tUniverso;

import java.util.List;

public interface universoRepository {
    List<tUniverso> getAllUniverso();
    tUniverso findUniversoById(int id);
    void inserttUniverso(tUniverso tuniverso);
    void updatetUniverso(tUniverso tuniverso);
    void deletetUniverso(tUniverso tuniverso);
}
