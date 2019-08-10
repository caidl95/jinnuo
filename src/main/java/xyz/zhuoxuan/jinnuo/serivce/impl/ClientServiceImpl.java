package xyz.zhuoxuan.jinnuo.serivce.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.zhuoxuan.jinnuo.base.service.AbstractService;
import xyz.zhuoxuan.jinnuo.commom.ServerResponse;
import xyz.zhuoxuan.jinnuo.entity.Client;
import xyz.zhuoxuan.jinnuo.mapper.ClientMapper;
import xyz.zhuoxuan.jinnuo.serivce.IClientService;
import xyz.zhuoxuan.jinnuo.serivce.ex.UpdateException;


import java.util.List;

@Service
public class ClientServiceImpl  implements IClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ServerResponse addClient(Client client) {
        if (StringUtils.isBlank(client.getName()) || StringUtils.isBlank(client.getPhone()))
            return ServerResponse.createByErrorMessage("姓名或手机不能为空！");
        Client result = clientMapper.findByNameAndPhone(client.getName(),client.getPhone());
        if (result != null)
            return ServerResponse.createByErrorMessage("已有相同客户，无需添加");
        client.setIsDefault(0);
        Integer rows = clientMapper.insert(client);
        if (rows != 1)
            throw new UpdateException("数据更新时出现异常");
        return ServerResponse.createBySuccess("插入成功！",client);
    }

    @Override
    public ServerResponse delete(Integer id) {
        Client client =clientMapper.findById(id);
        if (client == null)
            return ServerResponse.createByErrorMessage("未查询到此用户");
        client.setIsDefault(1);//'有效状态 0- 有效 ，1-删除',
        Integer rows =clientMapper.updateByPrimaryKeySelective(client);
        if (rows != 1)
            throw new UpdateException("数据更新时出现异常");
        return ServerResponse.createBySuccess("删除成功！");
    }

    @Override
    public ServerResponse update(Client client) {
        Integer rows =clientMapper.updateByPrimaryKeySelective(client);
        if (rows != 1)
            throw new UpdateException("数据更新时出现异常");
        return ServerResponse.createBySuccess("更新成功！");
    }

    @Override
    public ServerResponse<PageInfo> list(String keyword,String phone,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        if (StringUtils.isBlank(keyword) && StringUtils.isBlank(phone)) {
            List<Client> clientList = clientMapper.list();
            PageInfo pageInfo = new PageInfo(clientList);
            return ServerResponse.createBySuccess(pageInfo);
        }
        if(StringUtils.isNotBlank(keyword))
            keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        List<Client> clientList = clientMapper.selectByNameAndPhone(StringUtils.isBlank(keyword)?null:keyword,StringUtils.isBlank(phone)?null:phone);
        System.err.println(clientList);
        PageInfo pageInfo = new PageInfo(clientList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getClientById(Integer id) {
        Client client = clientMapper.findById(id);
        if (client == null)
            return ServerResponse.createByErrorMessage("未找到客户信息！");
        return ServerResponse.createBySuccess(client);
    }


}
