package xyz.zhuoxuan.jinnuo.serivce;

import xyz.zhuoxuan.jinnuo.commom.ServerResponse;
import xyz.zhuoxuan.jinnuo.pojo.Receive;

public interface ISellService {

    ServerResponse insert(Receive receive);

    ServerResponse list(String endTime);
}
