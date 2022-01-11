package com.example.loginjava.repository;

import com.example.loginjava.database.dao.universoDAO;
import com.example.loginjava.database.entity.tUniverso;

import java.util.List;

public class universoRepositoryImpl implements universoRepository{

    universoDAO  dao;

    public universoRepositoryImpl(universoDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<tUniverso> getAllUniverso() {
        return dao.getAll();
    }

    @Override
    public tUniverso findUniversoById(int id) {
        return dao.findById(id);
    }

    @Override
    public void inserttUniverso(tUniverso tuniverso) {
        dao.insert(tuniverso);
    }

    @Override
    public void updatetUniverso(tUniverso tuniverso) {
        dao.update(tuniverso);
    }

    @Override
    public void deletetUniverso(tUniverso tuniverso) {
        dao.delete(tuniverso);
    }
}
