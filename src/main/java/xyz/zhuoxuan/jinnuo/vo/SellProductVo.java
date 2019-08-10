package xyz.zhuoxuan.jinnuo.vo;

import java.math.BigDecimal;

public class SellProductVo {

    private Integer sellProductId;//商品Id
    private Integer categoryId;// '分类id,对应category表的主键',
    private Integer productId;//商品id,对应product表的主键',
    private String categoryName;
    private Integer place;//商品存放位置 1-在仓库 2-在店面',
    private String productName;//商品名称
    private BigDecimal price;//'单价',
    private Integer num;//购买的数量

    public SellProductVo(Integer sellProductId, Integer categoryId, Integer productId, String categoryName, Integer place, String productName, BigDecimal price, Integer num) {
        this.sellProductId = sellProductId;
        this.categoryId = categoryId;
        this.productId = productId;
        this.categoryName = categoryName;
        this.place = place;
        this.productName = productName;
        this.price = price;
        this.num = num;
    }

    public Integer getSellProductId() {
        return sellProductId;
    }

    public void setSellProductId(Integer sellProductId) {
        this.sellProductId = sellProductId;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "SellProductVo{" +
                "sellProductId=" + sellProductId +
                ", categoryId=" + categoryId +
                ", productId=" + productId +
                ", categoryName='" + categoryName + '\'' +
                ", place=" + place +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", num=" + num +
                '}';
    }
}
