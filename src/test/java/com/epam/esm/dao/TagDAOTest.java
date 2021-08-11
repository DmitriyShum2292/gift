package com.epam.esm.dao;

import com.epam.esm.entities.Tag;
import config.InitDB;
import config.JdbcConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TagDAOTest {

    private Tag tag;

    private static JdbcConfig jdbcConfig = new JdbcConfig();

    private static InitDB initDB = new InitDB();

    private TagDAO tagDAO = new TagDAO(jdbcConfig.getJdbcTemplate());

    @BeforeAll
    static void initialize(){;
        initDB.initGift(jdbcConfig);
        initDB.initTag(jdbcConfig);
        initDB.initGiftTag(jdbcConfig);
    }

    @BeforeEach
    void createTag(){
        tag = new Tag(1,"Tag");

        tagDAO.create(tag);
    }

    @Test
    void create(){
        tagDAO.create(tag);
        assertThat(tagDAO.findByName("Tag").getName()).isEqualTo("Tag");
    }

    @Test
    void read(){
        assertThat(tagDAO.read(1).getName()).isEqualTo("Tag");
    }
    @Test
    void delete(){
        tagDAO.create(new Tag(2,"newTag"));
        assertThat(tagDAO.findByName("newTag").getName()).isEqualTo("newTag");
    }

    @Test
    void findByName() {
        Tag tag = tagDAO.findByName("Tag");
        System.out.println(tag.getName());
        assertThat(tag.getName().equals("Tag"));
    }

    @Test
    void allTags() {
        Tag tag = new Tag();
        tag.setName("newTag");
        tagDAO.create(tag);
        List<Tag>tags = tagDAO.allTags();
        System.out.println(tags);
        assertThat(tags.size()>=2);
    }

    @Test
    void findTagsByGift() {
        int id = 1;
        List<Tag> tags = tagDAO.findTagsByGift(1);
        assertThat(tags.size()>=1);
    }
}