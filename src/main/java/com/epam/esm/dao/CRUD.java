package com.epam.esm.dao;

import java.time.LocalDateTime;
import java.util.Map;

public interface CRUD<T> {
    public T create(T t);
    public T read(int id);
    public boolean update(Map<Object,Object> fields, int id, LocalDateTime date);
    public void delete(int id);
}
