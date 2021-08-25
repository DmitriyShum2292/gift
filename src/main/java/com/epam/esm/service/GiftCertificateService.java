package com.epam.esm.service;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public GiftCertificateService (){

    }

    public GiftCertificateService(GiftCertificateDAO giftCertificateDAO, TagService tagService){
        this.giftCertificateDAO = giftCertificateDAO;
        this.tagService = tagService;
    }

    @Transactional(rollbackFor = SQLException.class,propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.READ_COMMITTED)
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

    @Transactional(rollbackFor = SQLException.class,propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.READ_COMMITTED)
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

    public List<GiftCertificate> getCertificatesWithTags(){
        List<GiftCertificate> allGifts = allGifts();

        allGifts = allGifts.stream().map(s->{s.setTags(tagService.findTagsByGift(s.getId()));
                                            return s;}).collect(Collectors.toList());
        return allGifts;
    }

    public List<GiftCertificate> certificateByTagNameSorted(String name){
        Tag tag = tagService.findByNameLike(name);
        List<GiftCertificate> certificates;

        certificates = findGiftsByTag(tag.getId());

        certificates = certificates.stream().map(s->{s.setTags(tagService.findTagsByGift(s.getId()));
            return s;}).collect(Collectors.toList());
        return certificates;
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
    public List<GiftCertificate> findGiftsByTagSortedByDateDESC(int id){
        return giftCertificateDAO.findGiftsByTagSortedByDateDESC(id);
    }
}
