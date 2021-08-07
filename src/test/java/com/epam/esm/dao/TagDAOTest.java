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
        Tag tag = new Tag();
        tag.setName("Tag");
        tagDAO.create(tag);
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