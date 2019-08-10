package xyz.zhuoxuan.jinnuo.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.zhuoxuan.jinnuo.entity.SellProduct;

import java.util.List;

public interface SellProductMapper {

    Integer insert(SellProduct sellProduct);


    List<SellProduct> selectBySellId(Integer sellId);
}
