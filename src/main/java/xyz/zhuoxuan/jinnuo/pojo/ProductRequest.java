package xyz.zhuoxuan.jinnuo.pojo;

import java.math.BigDecimal;

public class ProductRequest {

    private Integer id;
    private String name; //'商品名称'
    private String subtitle; //'商品副标题'
    private String placeName;//商品存放位置
    private String categoryName;
    private Integer stock;// '库存数量',
    private String detail;//商品详情',
    private BigDecimal price;//'价格,单位-元保留两位小数',
    private String mainImage;//  '产品主图,url相对地址前缀',
    private Integer status;//商品状态
    private Integer place;

    public ProductRequest() {
    }

    public ProductRequest(Integer id, String name, String subtitle, String placeName, String categoryName, Integer stock, String detail, BigDecimal price, Integer status, Integer place) {
        this.id = id;
        this.name = name;
        this.subtitle = subtitle;
        this.placeName = placeName;
        this.categoryName = categoryName;
        this.stock = stock;
        this.detail = detail;
        this.price = price;
        this.status = status;
        this.place = place;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", placeName='" + placeName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", stock=" + stock +
                ", detail='" + detail + '\'' +
                ", price=" + price +
                ", mainImage='" + mainImage + '\'' +
                ", status=" + status +
                ", place=" + place +
                '}';
    }
}
