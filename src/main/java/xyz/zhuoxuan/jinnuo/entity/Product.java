package xyz.zhuoxuan.jinnuo.entity;

import xyz.zhuoxuan.jinnuo.base.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class Product extends BaseEntity {
    private Integer id;
    private Integer categoryId;//'分类id,对应category表的主键'
    private String name; //'商品名称'
    private String subtitle; //'商品副标题'
    private String mainImage;//  '产品主图,url相对地址前缀',
    private String detail;//商品详情',
    private BigDecimal price ;//'价格,单位-元保留两位小数',
    private Integer stock;// '库存数量',
    private Integer status;// '商品状态.1-在售 2-下架 3-删除',
    private Integer place;//商品存放位置 1-在仓库 2-在店面',

    public Product (){}

    public Product (Integer id,Integer categoryId,String name,String subtitle,String mainImage,String detail,BigDecimal price,Integer stock,Integer status,Integer place,Date createTime,Date updateTime){
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.subtitle = subtitle;
        this.mainImage = mainImage;
        this.detail = detail;
        this.price = price;
        this.stock = stock;
        this.status =status;
        this.place = place;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer category_id) {
        this.categoryId = category_id;
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

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String main_image) {
        this.mainImage = main_image;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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
        return "Product{" +
                "id=" + id +
                ", categoryId=" + categoryId +

                ", name='" + name + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", detail='" + detail + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", status=" + status +
                ", place=" + place +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
