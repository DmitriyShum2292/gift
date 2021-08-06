package com.epam.esm.dao;

import com.epam.esm.entities.GiftCertificate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GiftCertificateDAOTest {


    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("giftrole");
        dataSource.setPassword("");
        return dataSource;
    }
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
    private GiftCertificateDAO giftCertificateDAO = new GiftCertificateDAO(jdbcTemplate());

    @Test
    void method(){
        JdbcTemplate jdbc = jdbcTemplate();
        jdbc.update("CREATE TABLE IF NOT EXISTS gift_certificate (" +
                "id serial constraint gift_certificate_pk primary key," +
                "name varchar ," +
                "description varchar, " +
                "price integer," +
                "duration integer," +
                "create_date timestamp," +
                "last_update_date timestamp," +
                "tags integer" +
                ")");
    }

    @Test
    void create() {
        method();
        GiftCertificate certificate = new GiftCertificate();
        GiftCertificate certificateExists;
        certificate.setName("Shoes");
        certificate.setPrice(3);
        certificate.setDescription("some text");
        giftCertificateDAO.create(certificate);
        certificateExists = giftCertificateDAO.findByName("Shoes");
        System.out.println(certificateExists.getPrice());
        assertThat(certificateExists.getName().equals(certificate.getName()));
    }

    @Test
    void read() {
//        int id = 18;
//        GiftCertificate certificate = giftCertificateDAO.read(id);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void allGifts() {
    }

    @Test
    void findByName() {
    }

    @Test
    void addTagToGift() {
    }

    @Test
    void findGiftsByTag() {
    }
}