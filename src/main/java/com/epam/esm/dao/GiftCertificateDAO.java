package com.epam.esm.dao;

import com.epam.esm.entities.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GiftCertificateDAO implements Crud<GiftCertificate>{


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GiftCertificateDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(GiftCertificate certificate){
        jdbcTemplate.update("INSERT INTO gift_certificate(name,description,price,duration,create_date)" +
                            "VALUES (?,?,?,?,?)", certificate.getName(), certificate.getDescription(),
                    certificate.getPrice(), certificate.getDuration(), certificate.getCreateDate());
    }
    @Override
    public GiftCertificate read(int id){
        return jdbcTemplate.query("SELECT * FROM gift_certificate WHERE id = ?",new Object[]{id},
                new BeanPropertyRowMapper<>(GiftCertificate.class)).stream().findAny().orElse(null);
    }

    @Override
    public void update(Map<Object,Object> fields,int id,LocalDateTime date){
        for (Object obj : fields.keySet()){
            jdbcTemplate.update("UPDATE gift_certificate SET "+obj.toString()+"=? WHERE id=?",fields.get(obj), id);
        }
        jdbcTemplate.update("UPDATE gift_certificate SET last_update_date = ? WHERE id=?",date,id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM gift_certificate WHERE id = ?",id);
    }

    public List<GiftCertificate> allGifts(){
        return jdbcTemplate.query("SELECT * FROM gift_certificate",new Object[]{},
                new BeanPropertyRowMapper<>(GiftCertificate.class)).stream().collect(Collectors.toList());
    }

    public GiftCertificate findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM gift_certificate WHERE name = ?",new Object[]{name},
                new BeanPropertyRowMapper<>(GiftCertificate.class)).stream().findAny().orElse(null);
    }

    public void addTagToGift(int giftId,int tagId){
        jdbcTemplate.update("INSERT INTO gift_tag (gift_id,tag_id) VALUES (?,?)",
                giftId, tagId);
    }

    public List<GiftCertificate> findGiftsByTag(int id){
        return jdbcTemplate.query("SELECT * FROM gift_certificate a JOIN gift_tag b ON a.id = b.gift_id " +
                        "WHERE tag_id = ? ORDER BY a.create_date DESC",
                new Object[]{id},
                new BeanPropertyRowMapper<>(GiftCertificate.class)).stream().collect(Collectors.toList());
    }

}
