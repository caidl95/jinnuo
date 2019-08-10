package xyz.zhuoxuan.jinnuo.vo;

public class ClientVo {

    private Integer id;//'用户信息表',
    private String name; //客户名称
    private String phone;//客户手机号码

    public ClientVo() {
    }

    public ClientVo(Integer id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
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
}
