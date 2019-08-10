package xyz.zhuoxuan.jinnuo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.zhuoxuan.jinnuo.commom.ServerResponse;
import xyz.zhuoxuan.jinnuo.entity.Client;
import xyz.zhuoxuan.jinnuo.mapper.ClientMapper;
import xyz.zhuoxuan.jinnuo.serivce.IClientService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientMapperTest {

    @Autowired
    ClientMapper clientMapper;

    @Autowired
    IClientService clientService;

    @Test
    public void insert(){
        Client client = new Client();
        client.setName("黄嘉荣");
        client.setPhone("13662925808");
        client.setEmail("850039992@qq.com");
        client.setAddr("东莞市");
        client.setIsDefault(0);
        clientMapper.insert(client);
        System.err.println(client);
    }


    @Test
    public void list(){
        List<Client> clients = clientMapper.list();
        System.err.println(clients);
    }

    @Test
    public void serviceList(){
        ServerResponse serverResponse = clientService.list("蔡",null,1,10);
        System.err.println(serverResponse.getData());
    }
}
