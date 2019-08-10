package xyz.zhuoxuan.jinnuo.serivce;

import com.github.pagehelper.PageInfo;
import xyz.zhuoxuan.jinnuo.commom.ServerResponse;
import xyz.zhuoxuan.jinnuo.entity.Client;


public interface IClientService {

    ServerResponse addClient(Client client);

    ServerResponse delete(Integer id);

    ServerResponse update(Client client);

    ServerResponse<PageInfo> list(String keyword, String phone, int pageNum, int pageSize);

    ServerResponse getClientById(Integer id);
}
