package xyz.zhuoxuan.jinnuo.entity;

import java.math.BigDecimal;

public class SellProduct {

    private Integer id;//销售商品表Id',
    private Integer sellId;// 销售表id',
    private Integer categoryId;// '分类id,对应category表的主键',
    private Integer productId;// '商品Id',
    private String productName;// 商品名称',
    private Integer num;//购买的数量
    private BigDecimal price;//  '单价',
    private Integer place;// 商品存放位置 1-在仓库 2-在店面',


    public SellProduct() {}

    public SellProduct(Integer id, Integer sellId, Integer categoryId, Integer productId, String productName,Integer num, BigDecimal price, Integer place) {
        this.id = id;
        this.sellId = sellId;
        this.categoryId = categoryId;
        this.productId = productId;
        this.productName = productName;
        this.num = num;
        this.price = price;
        this.place = place;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellId() {
        return sellId;
    }

    public void setSellId(Integer sellId) {
        this.sellId = sellId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "SellProduct{" +
                "id=" + id +
                ", sellId=" + sellId +
                ", categoryId=" + categoryId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", place=" + place +
                ", num=" + num +
                '}';
    }
}
