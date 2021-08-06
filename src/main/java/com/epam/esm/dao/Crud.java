package com.epam.esm.dao;

import java.time.LocalDateTime;
import java.util.Map;

public interface Crud <T> {
    public void create(T t);
    public T read(int id);
    public void update(Map<Object,Object> fields, int id, LocalDateTime date);
    public void delete(int id);
}
