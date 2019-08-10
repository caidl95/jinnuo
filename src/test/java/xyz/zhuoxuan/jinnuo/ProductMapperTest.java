package xyz.zhuoxuan.jinnuo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.zhuoxuan.jinnuo.commom.ServerResponse;
import xyz.zhuoxuan.jinnuo.entity.Product;
import xyz.zhuoxuan.jinnuo.mapper.ProductMapper;
import xyz.zhuoxuan.jinnuo.pojo.ProductRequest;
import xyz.zhuoxuan.jinnuo.serivce.IProductService;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductMapperTest {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    IProductService productService;

    @Test
    public void insert() {
        Product product =new Product();
        product.setCategoryId(1);
        product.setName("小金砖");
        product.setSubtitle("云南普洱");
        product.setMainImage("http://192.168.1.1");
        product.setDetail("这是一款很好喝的茶叶");
        product.setPrice(new BigDecimal(100));
        product.setStock(100);
        product.setStatus(1);
        product.setPlace(1);
        Integer rows = productMapper.insert(product);
        System.err.println(rows);
    }

    @Test
    public void findByName(){
       List<Product> product = productMapper.findByName("小银砖");
       System.err.println(product);
    }

    @Test
    public void  updateStock(){
        Integer rows = productMapper.updateStock(1,10);
        System.err.println(rows);
    }

    @Test
    public void add(){
        ProductRequest product =new ProductRequest();
        product.setCategoryName("茶叶");
        product.setName("小银砖");
        product.setSubtitle("云南普洱");
        product.setMainImage("http://192.168.1.1");
        product.setDetail("这是一款很好喝的茶叶");
        product.setPrice(new BigDecimal(100));
        product.setStock(100);
        product.setPlaceName("仓库");
        ServerResponse result = productService.add(product);
        System.err.println(result.isSuccess());
    }

    @Test
    public void list(){
        ServerResponse result = productService.getProductByKeywordCategory("砖",10000,1,10);
        System.err.println(result.getData());
    }




}
