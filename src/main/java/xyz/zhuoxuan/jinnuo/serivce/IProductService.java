package xyz.zhuoxuan.jinnuo.serivce;

import com.github.pagehelper.PageInfo;
import xyz.zhuoxuan.jinnuo.commom.ServerResponse;
import xyz.zhuoxuan.jinnuo.pojo.ProductRequest;


public interface IProductService  {

    ServerResponse<String> add(ProductRequest productRequest);

    ServerResponse<PageInfo> getProductByKeywordCategory(String keyword, Integer place, Integer pageNum, Integer pageSize);

    ServerResponse<String> delete(Integer id);

    ServerResponse<String> update(ProductRequest productRequest);

    ServerResponse getProductById(Integer id);
}
