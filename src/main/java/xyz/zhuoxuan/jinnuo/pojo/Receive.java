package xyz.zhuoxuan.jinnuo.pojo;

import java.math.BigDecimal;
import java.util.Arrays;

public class Receive {

    private Integer id;
    private String  clientName ; //客户名称
    private String phone ;//客户手机号码
    private String [] productName;//商品名称
    private Integer [] num;// '数量',
    private Integer  payType;// '支付平台:1-支付宝,2-微信,3-现金',
    private Integer place ;//商品存放位置 1-在仓库 2-在店面',
    private BigDecimal payment;// '实际付款金额',
    private String endTime ;//'交易完成时间',

    public Receive(){}
    public Receive(Integer uid,String clientName,String phone,String [] productName,Integer [] num,Integer payType,Integer place,BigDecimal payment,String endTime){
        this.id = uid;
        this.clientName = clientName;
        this.phone = phone;
        this.productName = productName;
        this.num = num;
        this.payType = payType;
        this.place = place;
        this.payment = payment;
        this.endTime = endTime;
    }

    public Integer getUid() {
        return id;
    }

    public void setUid(Integer uid) {
        this.id = uid;
    }


    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String [] getProductName() {
        return productName;
    }

    public void setProductName(String [] productName) {
        this.productName = productName;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Integer [] getNum() {
        return num;
    }

    public void setNum(Integer [] num) {
        this.num = num;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Receive{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", phone='" + phone + '\'' +
                ", productName=" + Arrays.toString(productName) +
                ", num=" + Arrays.toString(num) +
                ", payType=" + payType +
                ", place=" + place +
                ", payment=" + payment +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
