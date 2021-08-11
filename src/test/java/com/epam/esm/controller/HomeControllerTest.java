package com.epam.esm.controller;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

    private MockMvc mockMvc;
    private HomeController homeController;
    @InjectMocks
    private GiftCertificateService giftCertificateService;
    @Mock
    private TagService tagService;
    @Mock
    private GiftCertificateDAO giftCertificateDAO;
    private ObjectMapper mapper = new ObjectMapper();
    private GiftCertificate certificate;

    @Before
    public void setUp(){
        this.giftCertificateDAO = giftCertificateDAO;
        this.tagService = new TagService();
        this.giftCertificateService = new GiftCertificateService(giftCertificateDAO,tagService);
        this.homeController = new HomeController(giftCertificateService,tagService);
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
        this.certificate = new GiftCertificate();
        certificate.setId(1);
        certificate.setName("Certificate");
    }


    @Test
    public void read() throws Exception {
        given(giftCertificateService.read(1)).willReturn(certificate);
        String json = mapper.writeValueAsString(certificate);
        mockMvc.perform(MockMvcRequestBuilders.get("/read/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString(json)));
    }


    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/1"))
                .andExpect(status().isOk());

    }

    @Test
    public void allGifts() throws Exception {
        List<GiftCertificate> certificates = new ArrayList<>();
        certificates.add(certificate);

        String content = mapper.writeValueAsString(certificates);
        given(giftCertificateService.allGifts()).willReturn(certificates);

        mockMvc.perform(MockMvcRequestBuilders.get("/allgifts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(content)));
    }

    @Test
    public void giftsByTag() throws Exception {
        List<GiftCertificate> certificates = new ArrayList<>();
        certificates.add(certificate);
        String content = mapper.writeValueAsString(certificates);

        given(giftCertificateService.findGiftsByTag(1)).willReturn(certificates);

        mockMvc.perform(MockMvcRequestBuilders.get("/giftsbytag/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(content)));
    }
}