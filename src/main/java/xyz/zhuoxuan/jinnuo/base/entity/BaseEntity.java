package xyz.zhuoxuan.jinnuo.base.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Entity基类
 *
 * @author peng.xy
 * @createDate 2018年5月20日 下午12:23:29
 */
public abstract class BaseEntity implements Serializable {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Date createTime;//创建时间',

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Date updateTime;// '更新时间',


    /**
     * 序列化ID
     */
    protected static final long serialVersionUID = 1L;

    /**
     * 生成UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
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


    public void preUpadte() {
        this.updateTime = new Date();

    }

    public void preUpadte(Date updateTime, String updateUser) {
        this.updateTime = updateTime;

    }

    public void preInsert() {
        this.createTime = new Date();
    }
}
