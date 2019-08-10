package xyz.zhuoxuan.jinnuo.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.zhuoxuan.jinnuo.base.dao.BaseMapper;
import xyz.zhuoxuan.jinnuo.entity.Product;
import xyz.zhuoxuan.jinnuo.vo.ProductVo;


import java.util.List;

public interface ProductMapper {

    Integer insert(Product product);

    Integer updateByPrimaryKey(Product entity);

    List<Product> findByName(String name);

    Integer updateStock(@Param("id") Integer id, @Param("stock") Integer stock);

    List<Product> list();

    Product findById(Integer id);

    ProductVo selectByProductId(Integer id);

    Integer updateById(Product product);

    List<ProductVo> selectByNameAndCategoryIds(@Param("productName") String productName, @Param("place") Integer place);

    Integer delete(Integer id);
}
