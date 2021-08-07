package com.epam.esm.service;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.entities.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service layer for Tag between Home Controller and Tag
 * @author dmitriy
 * @version 1.0
 */

@Service
public class TagService {

    @Autowired
    private TagDAO tagDAO;

    public TagService(TagDAO tagDAO){
        this.tagDAO = tagDAO;
    }
    public boolean create(Tag tag){
        if (tagDAO.findByName(tag.getName())==null){
            tagDAO.create(tag);
            return true;
        }
        return false;
    }
    public Tag findByName(String name){
        return tagDAO.findByName(name);
    }

    public List<Tag> allTags(){
        return tagDAO.allTags();
    }

    public List<Tag> findTagsByGift(int id){
        return tagDAO.findTagsByGift(id);
    }
}
