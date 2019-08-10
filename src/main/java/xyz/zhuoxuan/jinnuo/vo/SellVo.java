package xyz.zhuoxuan.jinnuo.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SellVo {

    private Integer sellId;//销售表Id
    private ClientVo clientVo;//用户vo类
    private List<SellProductVo> sellProductVos;//商品类
    private Integer payType;//支付平台:1-支付宝,2-微信,3-现金
    private BigDecimal payment;//实际付款金额
    private Date endTime ;//交易完成时间
    private Date createTime;//创建时间
    private Date updateTime;//更新时间

    public SellVo(){}

    public SellVo(Integer sellId, ClientVo clientVo, List<SellProductVo> sellProductVos,  Integer payType, BigDecimal payment, Date endTime, Date createTime, Date updateTime) {
        this.sellId = sellId;
        this.clientVo = clientVo;
        this.sellProductVos = sellProductVos;
        this.payType = payType;
        this.payment = payment;
        this.endTime = endTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getSellId() {
        return sellId;
    }

    public void setSellId(Integer sellId) {
        this.sellId = sellId;
    }

    public ClientVo getClientVo() {
        return clientVo;
    }

    public void setClientVo(ClientVo clientVo) {
        this.clientVo = clientVo;
    }

    public List<SellProductVo> getSellProductVos() {
        return sellProductVos;
    }

    public void setSellProductVos(List<SellProductVo> sellProductVos) {
        this.sellProductVos = sellProductVos;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SellVo{" +
                "sellId=" + sellId +
                ", sellProductVos=" + sellProductVos +
                ", payType=" + payType +
                ", payment=" + payment +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
