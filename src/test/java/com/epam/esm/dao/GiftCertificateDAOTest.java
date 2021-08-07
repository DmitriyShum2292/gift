package com.epam.esm.dao;

import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.GiftTag;
import config.InitDB;
import config.JdbcConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GiftCertificateDAOTest {

    private static JdbcConfig jdbcConfig = new JdbcConfig();

    private static InitDB initDB = new InitDB();

    private GiftCertificateDAO giftCertificateDAO = new GiftCertificateDAO(jdbcConfig.getJdbcTemplate());



    @AfterAll
    static void closeDB(){
        try {
            jdbcConfig.getJdbcTemplate().getDataSource().getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
     void createCertificate(){
        GiftCertificate certificate = new GiftCertificate();
        certificate.setName("Certificate");
        certificate.setPrice(3);
        certificate.setDescription("some text");
        giftCertificateDAO.create(certificate);
        giftCertificateDAO.addTagToGift(1,1);
    }

    @Test
    void create() {
        InitDB init = new InitDB();
        init.initGift(jdbcConfig);
        GiftCertificate certificateExists = giftCertificateDAO.findByName("Certificate");
        assertThat(certificateExists.getName().equals("Certificate"));
    }

    @Test
    void read() {
        GiftCertificate certificateExists = giftCertificateDAO.read(1);
        assertThat(certificateExists.getName().equals("Certificate"));
    }

    @Test
    void update() {
        Map<Object,Object> fields = new HashMap<>();
        fields.put("name","newGift");
        LocalDateTime date = LocalDateTime.now();
        giftCertificateDAO.update(fields,1,date);

        GiftCertificate certificateExists = giftCertificateDAO.read(1);
        assertThat(certificateExists.getName().equals("newGift"));

    }

    @Test
    void delete() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setName("Certificate1");
        giftCertificateDAO.delete(2);
        assertThat(giftCertificateDAO.read(2)==null);
    }

    @Test
    void allGifts() {
        GiftCertificate first = new GiftCertificate();
        GiftCertificate second = new GiftCertificate();
        giftCertificateDAO.create(first);
        giftCertificateDAO.create(second);
        List<GiftCertificate> certificates = giftCertificateDAO.allGifts();
        assertThat(certificates.size()==3);
    }

    @Test
    void findByName() {
        GiftCertificate certificate = giftCertificateDAO.findByName("Certificate");
        assertThat(certificate.getName().equals("Certificate"));
    }

    @Test
    void addTagToGift() {
        int giftId = 1;
        int tagId = 1;
        giftCertificateDAO.addTagToGift(giftId,tagId);
        GiftTag giftTag = jdbcConfig.getJdbcTemplate().query("SELECT * FROM gift_tag WHERE gift_id = ?",
                new Object[]{giftId}, new BeanPropertyRowMapper<>(GiftTag.class)).stream().findAny().orElse(null);
        assertThat(giftTag.getGiftId()==tagId);
    }

    @Test
    void findGiftsByTag() {
        int tagId = 1;
        List<GiftCertificate> certificates = giftCertificateDAO.findGiftsByTag(1);
        assertThat(certificates.size()>=1);
    }
}