package xyz.zhuoxuan.jinnuo.entity;

import xyz.zhuoxuan.jinnuo.base.entity.BaseEntity;

import java.util.Date;

public class Client extends BaseEntity {

    private Integer id;//客户信息表id',
    private String name;// '客户名称',
    private String phone;// '手机号码',
    private String email;// '邮箱',
    private String addr;//'所住地址',
    private Integer isDefault;//'有效状态 0- 有效 ，1-删除',

    public Client() {
    }

    public Client(Integer id, String name, String phone, String email, String addr, Integer isDefault, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.addr = addr;
        this.isDefault = isDefault;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", addr='" + addr + '\'' +
                ", isDefault=" + isDefault +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
