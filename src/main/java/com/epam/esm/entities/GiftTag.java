package com.epam.esm.entities;

public class GiftTag {
    private int giftId;
    private int tagId;

    public GiftTag() {
    }

    public GiftTag(int giftId, int tagId) {
        this.giftId = giftId;
        this.tagId = tagId;
    }

    public int getGiftId() {
        return giftId;
    }

    public void setGiftId(int giftId) {
        this.giftId = giftId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
