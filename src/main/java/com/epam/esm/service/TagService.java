package com.epam.esm.service;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.dao.impl.TagDAOImpl;
import com.epam.esm.entities.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
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

    public TagService (){
    }
    public TagService(TagDAOImpl tagDAOImpl){
        this.tagDAO = tagDAOImpl;
    }

    @Transactional(rollbackFor = SQLException.class,propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
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

    public Tag findByNameLike(String name){
        return tagDAO.findByNameLike(name);
    }

    public List<Tag> allTags(){
        return tagDAO.allTags();
    }

    public List<Tag> findTagsByGift(int id){
        return tagDAO.findTagsByGift(id);
    }
}
