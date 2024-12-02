package vn.iotstar.model;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class MilkTeaDto {

    private String milkTeaName;
    private int milkTeaTypeID;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private String description;
    private String introduction;

    private MultipartFile[] images; // Dùng mảng để nhận nhiều ảnh

    // Getter và Setter
    public String getMilkTeaName() {
        return milkTeaName;
    }

    public void setMilkTeaName(String milkTeaName) {
        this.milkTeaName = milkTeaName;
    }

    public int getMilkTeaTypeID() {
        return milkTeaTypeID;
    }

    public void setMilkTeaTypeID(int milkTeaTypeID) {
        this.milkTeaTypeID = milkTeaTypeID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public MultipartFile[] getImages() {
        return images;
    }

    public void setImages(MultipartFile[] images) {
        this.images = images;
    }
}