package com.epam.esm.dao;

import com.epam.esm.dao.impl.TagDAOImpl;
import com.epam.esm.entities.Tag;
import config.InitDB;
import config.JdbcConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TagDAOImplTest {

    private Tag tag;

    private static JdbcConfig jdbcConfig = new JdbcConfig();

    private static InitDB initDB = new InitDB();

    private TagDAOImpl tagDAOImpl = new TagDAOImpl(jdbcConfig.getJdbcTemplate());

    @BeforeAll
    static void initialize(){;
        initDB.initGift(jdbcConfig);
        initDB.initTag(jdbcConfig);
        initDB.initGiftTag(jdbcConfig);
    }

    @BeforeEach
    void createTag(){
        tag = new Tag(1,"Tag");

        tagDAOImpl.create(tag);
    }

    @Test
    void create(){
        tagDAOImpl.create(tag);
        assertThat(tagDAOImpl.findByName("Tag").getName()).isEqualTo("Tag");
    }

    @Test
    void read(){
        assertThat(tagDAOImpl.read(1).getName()).isEqualTo("Tag");
    }
    @Test
    void delete(){
        tagDAOImpl.create(new Tag(2,"newTag"));
        assertThat(tagDAOImpl.findByName("newTag").getName()).isEqualTo("newTag");
    }

    @Test
    void findByName() {
        Tag tag = tagDAOImpl.findByName("Tag");
        System.out.println(tag.getName());
        assertThat(tag.getName().equals("Tag"));
    }

    @Test
    void allTags() {
        Tag tag = new Tag();
        tag.setName("newTag");
        tagDAOImpl.create(tag);
        List<Tag>tags = tagDAOImpl.allTags();
        System.out.println(tags);
        assertThat(tags.size()>=2);
    }

    @Test
    void findTagsByGift() {
        int id = 1;
        List<Tag> tags = tagDAOImpl.findTagsByGift(1);
        assertThat(tags.size()>=1);
    }
}