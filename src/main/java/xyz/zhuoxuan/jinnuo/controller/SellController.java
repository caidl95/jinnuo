package xyz.zhuoxuan.jinnuo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.zhuoxuan.jinnuo.base.conreoller.BaseController;
import xyz.zhuoxuan.jinnuo.commom.ServerResponse;
import xyz.zhuoxuan.jinnuo.pojo.Receive;
import xyz.zhuoxuan.jinnuo.serivce.ISellService;
import xyz.zhuoxuan.jinnuo.util.ArrToNullUtil;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/sell")
public class SellController extends BaseController {

    @Autowired
    private ISellService sellService;

    @PostMapping("/add")
    public ServerResponse add(HttpSession session, Receive receive) {
        receive.setUid(getUidFromSession(session));
        if (ArrToNullUtil.arrIsNull(receive.getProductName()))
            return ServerResponse.createByErrorMessage("未传入商品名称！");
        return sellService.insert(receive);
    }

    @GetMapping("/list")
    public ServerResponse list(@RequestParam(value = "endTime", required = false) String endTime) {
        System.out.println(endTime);
        return sellService.list(endTime);
    }


}
