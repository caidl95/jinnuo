package xyz.zhuoxuan.jinnuo.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.zhuoxuan.jinnuo.base.dao.BaseMapper;
import xyz.zhuoxuan.jinnuo.entity.Client;
import xyz.zhuoxuan.jinnuo.vo.ClientVo;

import java.util.List;

public interface ClientMapper {

    Integer insert(Client entity);

    Integer updateByPrimaryKeySelective(Client client);

    Integer updateByPrimaryKey(Client client);

    Client findById( Integer id);

    ClientVo selectById(Integer id);

    List<Client> list();

    List<Client> selectByNameAndPhone(@Param("name") String name, @Param("phone") String phone);

    Client findByNameAndPhone(@Param("name") String name, @Param("phone") String phone);

    Client selectByPhone(String phone);


}
