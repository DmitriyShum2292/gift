package com.epam.esm.controller;

import com.epam.esm.dto.ResponseSuccessDTO;
import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Tag;
import com.epam.esm.exception.BadRequestException;
import com.epam.esm.exception.NotFoundException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
/**
 * Rest controller for GiftCertificate
 * @author dmitriy
 * @version 1.0
 */

@RestController
@RequestMapping("/certificate")
public class GiftCertificateController {

    private final GiftCertificateService giftCertificateService;
    private final TagService tagService;
    private final int noId = 1000;

    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService,TagService tagService) {
        this.giftCertificateService = giftCertificateService;
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseEntity<?> create (@RequestBody GiftCertificate certificate){
        if(giftCertificateService.findByName(certificate.getName())!=null) {
            throw new BadRequestException("Certificate with name: "+certificate.getName()+" exists!",
                    noId);
        }
        giftCertificateService.create(certificate);
        ResponseSuccessDTO successDTO = new ResponseSuccessDTO("Created",HttpStatus.CREATED);
        return new ResponseEntity<>(successDTO,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable int id){
        GiftCertificate certificate = giftCertificateService.read(id);
        if (certificate==null){
            throw new NotFoundException("Certificate not found!",id);
        }
        return new ResponseEntity<>(certificate,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id,@RequestBody Map<Object,Object> fields){
        if(giftCertificateService.read(id)==null) {
            throw new NotFoundException("Not updated.Certificate not found!",id);
        }
        giftCertificateService.update(fields,id);
        ResponseSuccessDTO successDTO = new ResponseSuccessDTO("Updated: "+id,HttpStatus.CREATED);
        return new ResponseEntity<>(successDTO,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        if (giftCertificateService.read(id)==null){
            throw new NotFoundException("Not deleted.Certificate not found!",id);
        }
        giftCertificateService.delete(id);
        ResponseSuccessDTO successDTO = new ResponseSuccessDTO("Deleted id: "+id,HttpStatus.CREATED);
        return new ResponseEntity<>(successDTO,HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<?> allGifts(){
        List<GiftCertificate> allGifts = giftCertificateService.allGifts();
        if (allGifts.isEmpty()){
            throw new NotFoundException("No certificate found",noId);
        }
        return new ResponseEntity<>(allGifts,HttpStatus.OK);
    }

    @GetMapping("/{id}/tags")
    public ResponseEntity<?> tagsByGift(@PathVariable int id){
        List<Tag> tags = tagService.findTagsByGift(id);
        if (tags.isEmpty()){
            throw new NotFoundException("Certificate with this id not found",id);
        }
        return new ResponseEntity<>(tags,HttpStatus.OK);
    }

    @GetMapping("/tags")
    public ResponseEntity<?> giftsWithTags(){
        List<GiftCertificate> certificates = giftCertificateService.getCertificatesWithTags();
        if (certificates.isEmpty()){
            throw new NotFoundException("No certificate found",noId);
        }
        return new ResponseEntity<>(certificates,HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<?> findGiftsByTagNameSorted(@RequestParam String name){
        List<GiftCertificate> certificates = giftCertificateService.certificateByTagNameSorted(name);
        if (certificates.isEmpty()){
            throw new NotFoundException("No certificate found",noId);
        }
        return new ResponseEntity<>(certificates,HttpStatus.OK);
    }

}
