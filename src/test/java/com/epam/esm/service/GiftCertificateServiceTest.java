package com.epam.esm.service;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

class GiftCertificateServiceTest {

    @Mock
    private GiftCertificateDAO giftCertificateDAO;
    @Mock
    private TagService tagService;
    private GiftCertificateService giftCertificateService;

    @BeforeEach
    public void before(){
        MockitoAnnotations.openMocks(this);
        this.giftCertificateService = new GiftCertificateService(giftCertificateDAO,tagService);
    }
    @Test
    void create() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setName("Certificate");
        Tag tag = new Tag(1,"Tag");
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        certificate.setTags(tags);
        given(giftCertificateDAO.create(certificate)).willReturn(certificate);
        given(tagService.findByName("Tag")).willReturn(tag);
        given(tagService.create(tag)).willReturn(true);
        given(giftCertificateDAO.findByName("Certificate")).willReturn(certificate);
        given(giftCertificateDAO.addTagToGift(1,1)).willReturn(true);

        boolean result = giftCertificateService.create(certificate);
        assertThat(result).isTrue();
    }
    @Test
    void read() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setName("Certificate");
        given(giftCertificateDAO.read(1)).willReturn(certificate);

        GiftCertificate exists = giftCertificateService.read(1);
        assertThat(exists!=null);
    }
    @Test
    void update(){
        GiftCertificate certificate = new GiftCertificate();
        certificate.setName("Certificate");
        Map<Object,Object> fields = new HashMap<>();
        fields.put("name","Certificate");
        LocalDateTime dateTime = LocalDateTime.now();

        given(giftCertificateDAO.update(fields,1,dateTime)).willReturn(true);
        given(giftCertificateDAO.read(1)).willReturn(certificate);

        boolean result = giftCertificateService.update(fields,1);
        assertThat(result).isTrue();
    }
    @Test
    void allGifts() {
        List<GiftCertificate> certificates = new ArrayList<>();
        certificates.add(new GiftCertificate());
        given(giftCertificateDAO.allGifts()).willReturn(certificates);

        assertThat(giftCertificateService.allGifts().size()).isEqualTo(1);
    }
    @Test
    void addTagToGift(){
        given(giftCertificateDAO.addTagToGift(1,1)).willReturn(true);
        boolean result = giftCertificateService.addTagToGift(1,1);

        assertThat(result).isTrue();
    }

}