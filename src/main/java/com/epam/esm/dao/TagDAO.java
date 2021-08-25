package com.epam.esm.dao;

import com.epam.esm.entities.Tag;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
public interface TagDAO {

    Tag create(Tag tag);
    Tag read(int id);
    boolean update(Map<Object, Object> fields, int id, LocalDateTime date);
    void delete(int id);
    Tag findByName(String name);
    Tag findByNameLike(String name);
    List<Tag> allTags();
    List<Tag> findTagsByGift(int id);
}
