package com.epam.esm.controller;

import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Tag;
import com.epam.esm.exception.NotFoundException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
/**
 * Rest controller for Tag
 * @author dmitriy
 * @version 1.0
 */

@RestController
@RequestMapping("/tag")
public class TagController {

    private final GiftCertificateService giftCertificateService;
    private final TagService tagService;

    @Autowired
    public TagController(GiftCertificateService giftCertificateService, TagService tagService) {
        this.giftCertificateService = giftCertificateService;
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<Tag>> alltags(){
        List<Tag> allTags = tagService.allTags();
        if (allTags.isEmpty()){
            throw new NotFoundException("No tag found",1000);
        }
        return new ResponseEntity<>(allTags, HttpStatus.OK);
    }

    @GetMapping("/{id}/certificate")
    public ResponseEntity<List<GiftCertificate>> giftsByTag(@PathVariable int id){
        List<GiftCertificate> gifts = giftCertificateService.findGiftsByTag(id);
        if (giftCertificateService.read(id)==null){
            throw new NotFoundException("No tag with tag id: "+id+" found",id);
        }
        if (gifts.isEmpty()){
            throw new NotFoundException("No certificate found with this tag id: "+id);
        }
        return new ResponseEntity<>(gifts,HttpStatus.OK);
    }

}
