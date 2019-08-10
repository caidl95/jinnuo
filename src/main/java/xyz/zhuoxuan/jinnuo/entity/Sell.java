package xyz.zhuoxuan.jinnuo.entity;

import xyz.zhuoxuan.jinnuo.base.entity.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

public class Sell extends BaseEntity {

    private Integer id;//销售表Id',
    private Integer clientId;//'用户信息表',
    private Integer payType;// '支付平台:1-支付宝,2-微信,3-现金',
    private BigDecimal payment;// '实际付款金额',
    private Date endTime;//'交易完成时间',

    public Sell() {
    }

    public Sell(Integer id, Integer clientId, Integer payType, BigDecimal payment, Date endTime, Date createTime, Date updateTime) {
        this.id = id;
        this.clientId = clientId;
        this.payType = payType;
        this.payment = payment;
        this.endTime = endTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Sell{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", payType=" + payType +
                ", payment=" + payment +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
