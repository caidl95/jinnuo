package xyz.zhuoxuan.jinnuo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.zhuoxuan.jinnuo.commom.ServerResponse;
import xyz.zhuoxuan.jinnuo.pojo.Receive;
import xyz.zhuoxuan.jinnuo.serivce.ISellService;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SellServiceTest {

    @Autowired
    ISellService sellService;

    @Test
    public void insert() {
        Receive receive = new Receive();
        receive.setUid(1);
        receive.setClientName("蔡栋良");
        String [] arr = {"小金砖，小银砖"};
        receive.setProductName(arr);
        Integer[] num = {1,2};
        receive.setNum(num);
        receive.setPhone("13662925808");
        receive.setPayment(new BigDecimal(1000));
        receive.setPayType(1);
        receive.setEndTime("2019-06-26 11:35:00");
        ServerResponse serverResponse = sellService.insert(receive);
        System.err.println(serverResponse.getMsg());
    }

    @Test
    public void list(){
        ServerResponse result = sellService.list("2019-06-26");
        System.err.println(result.getData());
    }
}
