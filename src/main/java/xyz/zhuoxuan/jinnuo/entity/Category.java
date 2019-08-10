package xyz.zhuoxuan.jinnuo.entity;

import xyz.zhuoxuan.jinnuo.base.entity.BaseEntity;

import java.util.Date;

public class Category extends BaseEntity {

    private Integer id ;//'类别Id',
    private Integer parentId;// 父类别id当id=0时说明是根节点,一级类别',
    private String name;// '类别名称',
    private Integer status;// '类别状态1-正常,2-已废弃',
    private Integer sortOrder;// '排序编号,同类展示顺序,数值相等则自然排序',


    public Category (){}
    public Category (Integer id,Integer parentId,String name,Integer status,Integer sortOrder,Date createTime ,Date updateTime){
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.status = status;
        this.sortOrder = sortOrder;
        this.createTime = createTime ;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }


    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", sortOrder=" + sortOrder +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
