package xyz.zhuoxuan.jinnuo.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.zhuoxuan.jinnuo.base.conreoller.BaseController;
import xyz.zhuoxuan.jinnuo.commom.ServerResponse;
import xyz.zhuoxuan.jinnuo.entity.Client;
import xyz.zhuoxuan.jinnuo.serivce.IClientService;


import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/client")
public class ClientController extends BaseController {

    @Autowired
    private IClientService clientService;

    @PostMapping("/add")
    public ServerResponse<String> insert(Client client) {
        return clientService.addClient(client);
    }

    @RequestMapping("/{id}/delete")
    public ServerResponse<String> delete(@PathVariable("id") Integer id, HttpSession session) {
        if (getRoleAdminFromSession(session)) {
            return clientService.delete(id);
        } else {
            return ServerResponse.createByErrorMessage("无此操作权限！");
        }
    }

    @PostMapping("/update")
    public ServerResponse update(Client client, HttpSession session) {
        if (getRoleAdminFromSession(session)) {
            return clientService.update(client);
        } else {
            return ServerResponse.createByErrorMessage("无此操作权限！");
        }
    }


    @GetMapping("/list")
    public ServerResponse<PageInfo> list(@RequestParam(value = "keyword", required = false) String keyword,
                                         @RequestParam(value = "phone", required = false) String phone,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return clientService.list(keyword, phone, pageNum, pageSize);
    }

    @RequestMapping("/{id}/client")
    public ServerResponse getClientById(@PathVariable("id") Integer id){
        return clientService.getClientById(id);
    }

}
