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
/**
 * Main Controller
 * @author dmitriy
 * @version 1.0
 */

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

    @PostMapping("/create")
    public ResponseEntity<String> create (@RequestBody GiftCertificate certificate){
        if(giftCertificateService.create(certificate)) {
            return new ResponseEntity<>("Success!",HttpStatus.OK);
        }
        return new ResponseEntity<>("Certificate exists!",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<GiftCertificate> read(@PathVariable int id){
        GiftCertificate certificate = giftCertificateService.read(id);
        if (certificate==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(certificate,HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable int id,@RequestBody Map<Object,Object> fields){
        if(giftCertificateService.update(fields,id)) {
            return new ResponseEntity<>("Success!",HttpStatus.OK);
        }
        return new ResponseEntity<>("Error!",HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        giftCertificateService.delete(id);
        return new ResponseEntity<>("Deleted" ,HttpStatus.OK);

    }

    @GetMapping("/alltags")
    public ResponseEntity<List<Tag>> alltags(){
        List<Tag> allTags = tagService.allTags();
        return new ResponseEntity<>(allTags,HttpStatus.OK);
    }
    @GetMapping("/allgifts")
    public ResponseEntity<List<GiftCertificate>> allGifts(){
        List<GiftCertificate> allGifts = giftCertificateService.allGifts();
        return new ResponseEntity<>(allGifts,HttpStatus.OK);
    }

    @GetMapping("/tagsbygift/{id}")
    public ResponseEntity<List<Tag>> tagsByGift(@PathVariable int id){
        List<Tag> tags = tagService.findTagsByGift(id);
        return new ResponseEntity<>(tags,HttpStatus.OK);
    }

    @GetMapping("/giftsbytag/{id}")
    public ResponseEntity<List<GiftCertificate>> giftsByTag(@PathVariable int id){
        List<GiftCertificate> gifts = giftCertificateService.findGiftsByTag(id);
        return new ResponseEntity<>(gifts,HttpStatus.OK);
    }

}
