package com.epam.esm.dao;

import com.epam.esm.entities.GiftCertificate;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
public interface GiftCertificateDAO {

    GiftCertificate create(GiftCertificate certificate);
    GiftCertificate read(int id);
    boolean update(Map<Object,Object> fields, int id, LocalDateTime date);
    void delete(int id);
    List<GiftCertificate> allGifts();
    GiftCertificate findByName(String name);
    boolean addTagToGift(int giftId,int tagId);
    List<GiftCertificate> findGiftsByTag(int id);
    List<GiftCertificate> findGiftsByTagSortedByDateDESC(int id);

}
