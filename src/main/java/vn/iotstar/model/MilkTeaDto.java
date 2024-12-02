package vn.iotstar.model;

import org.springframework.web.multipart.MultipartFile;

import vn.iotstar.entity.MilkTeaType;

import java.math.BigDecimal;

public class MilkTeaDto {

    private String milkTeaName;
    private MilkTeaType milkTeaType; 
    private BigDecimal price;
    private BigDecimal discountPrice;
    private String description;
    private String introduction;

    private MultipartFile[] images; // Dùng mảng để nhận nhiều ảnh

    

	/**
	 * @return the milkTeaName
	 */
	public String getMilkTeaName() {
		return milkTeaName;
	}



	/**
	 * @param milkTeaName the milkTeaName to set
	 */
	public void setMilkTeaName(String milkTeaName) {
		this.milkTeaName = milkTeaName;
	}



	/**
	 * @return the milkTeaType
	 */
	public MilkTeaType getMilkTeaType() {
		return milkTeaType;
	}



	/**
	 * @param milkTeaType the milkTeaType to set
	 */
	public void setMilkTeaType(MilkTeaType milkTeaType) {
		this.milkTeaType = milkTeaType;
	}



	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}



	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}



	/**
	 * @return the discountPrice
	 */
	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}



	/**
	 * @param discountPrice the discountPrice to set
	 */
	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}



	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}



	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}



	/**
	 * @return the introduction
	 */
	public String getIntroduction() {
		return introduction;
	}



	/**
	 * @param introduction the introduction to set
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}



	/**
	 * @return the images
	 */
	public MultipartFile[] getImages() {
		return images;
	}



	/**
	 * @param images the images to set
	 */
	public void setImages(MultipartFile[] images) {
		this.images = images;
	}



	public MilkTeaDto() {
	}
    
    
}