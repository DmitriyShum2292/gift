package com.epam.esm.service;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Sevice layer for GiftCertificate between Controller and GiftCertificateDAO
 * @author dmitriy
 * @version 1.0
 */

@Service
public class GiftCertificateService {

    @Autowired
    private GiftCertificateDAO giftCertificateDAO;
    @Autowired
    private TagService tagService;

    public GiftCertificateService(GiftCertificateDAO giftCertificateDAO, TagService tagService){
        this.giftCertificateDAO=giftCertificateDAO;
        this.tagService = tagService;
    }

    public boolean create (GiftCertificate certificate){
        LocalDateTime date = LocalDateTime.now();
        List<Tag> tags = certificate.getTags();
        certificate.setCreateDate(date);
        if (giftCertificateDAO.findByName(certificate.getName())==null) {
            giftCertificateDAO.create(certificate);
        }
        for(Tag tag : tags){
            tagService.create(tag);
            giftCertificateDAO.addTagToGift(giftCertificateDAO.findByName(certificate.getName()).getId(),
                    tagService.findByName(tag.getName()).getId());
        }
        return true;
    }

    public GiftCertificate read(int id){
        return giftCertificateDAO.read(id);
    }

    public boolean update(Map<Object,Object> fields, int id){
        LocalDateTime date = LocalDateTime.now();
        if(giftCertificateDAO.read(id)!=null){
            giftCertificateDAO.update(fields,id,date);
            return true;
        }
        return false;
    }
    public void delete(int id){
        giftCertificateDAO.delete(id);
    }

    public List<GiftCertificate> allGifts(){
        return giftCertificateDAO.allGifts();
    }

    public GiftCertificate findByName(String name){
        return giftCertificateDAO.findByName(name);
    }

    public boolean addTagToGift(int giftId,int tagId){
        giftCertificateDAO.addTagToGift(giftId,tagId);
        return true;
    }

    public List<GiftCertificate> findGiftsByTag(int id){
        return giftCertificateDAO.findGiftsByTag(id);
    }
}
