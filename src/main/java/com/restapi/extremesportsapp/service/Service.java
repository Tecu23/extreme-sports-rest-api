package com.restapi.extremesportsapp.service;

import com.restapi.extremesportsapp.entity.Sport;

import java.util.List;

public interface Service<T> {

    public List<T> getAll();

    public T getOne(Integer id);

    public void saveItem(T item);

    public void deleteItemById(Integer id);

}
