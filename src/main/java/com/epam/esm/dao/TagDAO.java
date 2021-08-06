package com.epam.esm.dao;

import com.epam.esm.entities.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TagDAO implements Crud<Tag>{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TagDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Tag tag) {
        jdbcTemplate.update("INSERT INTO tag (name) VALUES (?)",tag.getName());
    }

    @Override
    public Tag read(int id) {
        return null;
    }

    @Override
    public void update(Map<Object, Object> fields, int id, LocalDateTime date) {

    }

    @Override
    public void delete(int id) {

    }

    public Tag findByName(String name){
        return jdbcTemplate.query("SELECT * FROM tag WHERE name = ?",new Object[]{name},
                new BeanPropertyRowMapper<>(Tag.class)).stream().findAny().orElse(null);
    }

    public List<Tag> allTags(){
        return jdbcTemplate.query("SELECT * FROM tag ",new Object[]{},
                new BeanPropertyRowMapper<>(Tag.class)).stream().collect(Collectors.toList());
    }

    public List<Tag> findTagsByGift(int id){
        return jdbcTemplate.query("SELECT * FROM tag a JOIN gift_tag b ON a.id = b.tag_id " +
                        "WHERE gift_id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Tag.class)).stream().collect(Collectors.toList());
    }

}
