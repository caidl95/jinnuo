package xyz.zhuoxuan.jinnuo.mapper;
import org.apache.ibatis.annotations.Param;
import xyz.zhuoxuan.jinnuo.entity.Sell;
import xyz.zhuoxuan.jinnuo.vo.SellVo;
import java.util.List;

public interface SellMapper  {

    Integer insert(Sell entity);

    List<Sell> list();

    List<SellVo> selectByEndTime(@Param("endTime") String endTime);

}
