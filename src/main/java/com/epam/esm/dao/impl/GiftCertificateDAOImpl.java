package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.entities.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * DAO layer for GiftCertificate
 * using JdbcTemplate
 * @author dmitriy
 * @version 1.0
 */
@Component
public class GiftCertificateDAOImpl  implements GiftCertificateDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GiftCertificateDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public GiftCertificateDAOImpl() {

    }

    @Override
    public GiftCertificate create(GiftCertificate certificate){
        jdbcTemplate.update("INSERT INTO gift_certificate(name,description,price,duration,create_date)" +
                        "VALUES (?,?,?,?,?)", certificate.getName(), certificate.getDescription(),
                certificate.getPrice(), certificate.getDuration(), certificate.getCreateDate());
        return certificate;
    }
    @Override
    public GiftCertificate read(int id){
        return jdbcTemplate.query("SELECT * FROM gift_certificate WHERE id = ?",new Object[]{id},
                new BeanPropertyRowMapper<>(GiftCertificate.class)).stream().findAny().orElse(null);
    }

    @Override
    public boolean update(Map<Object,Object> fields, int id, LocalDateTime date){

        for(Map.Entry<Object,Object>entry : fields.entrySet()){
            jdbcTemplate.update("UPDATE gift_certificate SET "+entry.getKey().toString()+"=? WHERE id=?",
                    fields.get(entry.getKey()), id);
        }
        jdbcTemplate.update("UPDATE gift_certificate SET last_update_date = ? WHERE id=?",date,id);
        return true;
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM gift_certificate WHERE id = ?",id);
    }

    @Override
    public List<GiftCertificate> allGifts(){
        return jdbcTemplate.query("SELECT * FROM gift_certificate",new Object[]{},
                new BeanPropertyRowMapper<>(GiftCertificate.class)).stream().collect(Collectors.toList());
    }

    @Override
    public GiftCertificate findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM gift_certificate WHERE name = ?",new Object[]{name},
                new BeanPropertyRowMapper<>(GiftCertificate.class)).stream().findAny().orElse(null);
    }

    @Override
    public boolean addTagToGift(int giftId,int tagId){
        jdbcTemplate.update("INSERT INTO gift_tag (gift_id,tag_id) VALUES (?,?)",
                giftId, tagId);
        return true;
    }

    @Override
    public List<GiftCertificate> findGiftsByTag(int id){
        return jdbcTemplate.query("SELECT * FROM gift_certificate a JOIN gift_tag b ON a.id = b.gift_id " +
                        "WHERE tag_id = ? ",
                new Object[]{id},
                new BeanPropertyRowMapper<>(GiftCertificate.class)).stream().collect(Collectors.toList());
    }

    @Override
    public List<GiftCertificate> findGiftsByTagSortedByDateDESC(int id){
        return jdbcTemplate.query("SELECT * FROM gift_certificate a JOIN gift_tag b ON a.id = b.gift_id " +
                        "WHERE tag_id = ? ORDER BY a.create_date DESC",
                new Object[]{id},
                new BeanPropertyRowMapper<>(GiftCertificate.class)).stream().collect(Collectors.toList());
    }


}
