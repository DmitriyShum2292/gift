package com.epam.esm.service;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.entities.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class TagServiceTest {
    @Mock
    private TagDAO tagDAO;
    private TagService tagService;

    @BeforeEach
    public void before(){
        MockitoAnnotations.openMocks(this);
        this.tagService = new TagService(tagDAO);
    }
    @Test
    void create() {
        Tag tag = new Tag(1,"Tag");
        given(tagDAO.findByName("Tag")).willReturn(null);
        given(tagDAO.create(tag)).willReturn(tag);
        boolean result = tagService.create(tag);
        assertThat(result).isTrue();
    }
}