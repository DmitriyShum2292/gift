package com.epam.esm.service;

import com.epam.esm.dao.impl.TagDAOImpl;
import com.epam.esm.entities.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

class TagServiceTest {
    @Mock
    private TagDAOImpl tagDAOImpl;
    private TagService tagService;
    Tag tag;

    @BeforeEach
    public void before(){
        MockitoAnnotations.openMocks(this);
        this.tagService = new TagService(tagDAOImpl);
        tag = new Tag(1,"Tag");
    }
    @Test
    void create() {
        Tag tag = new Tag(1,"Tag");
        given(tagDAOImpl.findByName("Tag")).willReturn(null);
        given(tagDAOImpl.create(tag)).willReturn(tag);
        boolean result = tagService.create(tag);
        assertThat(result).isTrue();
    }
    @Test
    void findByName(){
        given(tagDAOImpl.findByName("Tag")).willReturn(tag);
        assertThat(tagService.findByName("Tag")).isEqualTo(tag);
    }
    @Test
    void allTag(){
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        given(tagDAOImpl.allTags()).willReturn(tags);
        assertThat(tags.size()==1);
    }
    @Test
    void findTagsByGift(){
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        given(tagDAOImpl.findTagsByGift(1)).willReturn(tags);
        assertThat(tagService.findTagsByGift(1).size()==1);
    }
}