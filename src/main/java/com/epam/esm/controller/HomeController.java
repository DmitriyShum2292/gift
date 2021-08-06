package com.epam.esm.controller;

import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Tag;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    private GiftCertificateService giftCertificateService;
    @Autowired
    private TagService tagService;

    @GetMapping("/home")
    public String home(){
        return "Hello!";
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<GiftCertificate> read(@PathVariable int id){
        if (giftCertificateService.read(id)==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(giftCertificateService.read(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity create (@RequestBody GiftCertificate certificate){
        giftCertificateService.create(certificate);
        return new ResponseEntity( HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id,@RequestBody Map<Object,Object> fields){
        giftCertificateService.update(fields,id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        giftCertificateService.delete(id);
        return new ResponseEntity(HttpStatus.OK);

    }

    @GetMapping("/alltags")
    public ResponseEntity alltags(){
        List<Tag> allTags = tagService.allTags();
        return new ResponseEntity(allTags,HttpStatus.OK);
    }
    @GetMapping("/allgifts")
    public ResponseEntity allGifts(){
        List<GiftCertificate> allGifts = giftCertificateService.allGifts();
        return new ResponseEntity(allGifts,HttpStatus.OK);
    }

    @GetMapping("/tagsbygift/{id}")
    public ResponseEntity tagsByGift(@PathVariable int id){
        List<Tag> tags = tagService.findTagsByGift(id);
        return new ResponseEntity(tags,HttpStatus.OK);
    }

    @GetMapping("/giftsbytag/{id}")
    public ResponseEntity giftsByTag(@PathVariable int id){
        List<GiftCertificate> gifts = giftCertificateService.findGiftsByTag(id);
        return new ResponseEntity(gifts,HttpStatus.OK);
    }

}
