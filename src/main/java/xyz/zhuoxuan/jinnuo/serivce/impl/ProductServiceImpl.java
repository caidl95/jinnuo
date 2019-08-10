package xyz.zhuoxuan.jinnuo.serivce.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.zhuoxuan.jinnuo.commom.ServerResponse;
import xyz.zhuoxuan.jinnuo.entity.Category;
import xyz.zhuoxuan.jinnuo.entity.Product;
import xyz.zhuoxuan.jinnuo.mapper.CategoryMapper;
import xyz.zhuoxuan.jinnuo.mapper.ProductMapper;
import xyz.zhuoxuan.jinnuo.pojo.ProductRequest;
import xyz.zhuoxuan.jinnuo.serivce.IProductService;
import xyz.zhuoxuan.jinnuo.serivce.ex.UpdateException;
import xyz.zhuoxuan.jinnuo.vo.ProductVo;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ServerResponse<String> add(ProductRequest productRequest ) {
        List<Product> result = productMapper.findByName(productRequest.getName());
        boolean isInsert = true;
        if (!result.isEmpty()){
            for (Product pdt : result){
                if (pdt.getPlace() == productRequest.getPlace()){
                    Integer stock = productRequest.getStock() + pdt.getStock();
                    Integer rows = productMapper.updateStock(pdt.getId(),stock);
                    if (rows != 1){
                        throw new UpdateException("数据更新时出现异常");
                    }else {
                        isInsert = false;
                    }
                }
            }
        }
       if (isInsert){
           if (productRequest.getCategoryName() == null)
               return ServerResponse.createByErrorMessage("商品名称不能为空");
           Category category = categoryMapper.selectByName(productRequest.getCategoryName());
           if (category == null)
               return ServerResponse.createByErrorMessage("无此分类，需先添加");
           Product product = getProduct(productRequest,category);
           Integer rows = productMapper.insert(product);
           if (rows != 1 )
               throw new UpdateException("数据更新时出现异常");

       }
        return ServerResponse.createBySuccess("添加成功");
    }

    private Product getProduct(ProductRequest productRequest,Category category){
        Product product = new Product();
        product.setId(productRequest.getId());
        if (category!=null)
            product.setCategoryId(category.getId());
        product.setName(productRequest.getName());
        product.setSubtitle(productRequest.getSubtitle());
        product.setMainImage(productRequest.getMainImage());
        product.setDetail(productRequest.getDetail());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        if ( productRequest.getStatus() == null ) {
            product.setStatus(1);
        }else {
            product.setStatus(productRequest.getStatus());
        }
        product.setPlace(productRequest.getPlace());

        return product;
    }



    @Override
    public ServerResponse<String> delete(Integer id ) {
        Product product = productMapper.findById(id);
        if (product == null)
            throw new UpdateException("数据更新时出现异常");
        if (product.getStatus() == 3)
            ServerResponse.createBySuccess("已经标记为删除无需修改！");
        product.setStatus(3);
        Integer rows = productMapper.delete(id);
        if (rows != 1)
            throw new UpdateException("数据更新时出现异常");
        return ServerResponse.createBySuccess("删除成功！");
    }

    @Override
    public ServerResponse<String> update(ProductRequest productRequest) {
        Category category = categoryMapper.selectByName(productRequest.getCategoryName());
        if (category == null)
            return ServerResponse.createByErrorMessage("无此商品分类！");
        Product product = getProduct(productRequest,category);
        int rowCount = productMapper.updateByPrimaryKey(product);
        if(rowCount !=1)
            throw new UpdateException("数据更新时出现异常");
        return ServerResponse.createBySuccess("更新产品成功");
    }

    @Override
    public ServerResponse getProductById(Integer id) {
        ProductVo productVo = productMapper.selectByProductId(id);
        if (productVo == null)
            return ServerResponse.createByErrorMessage("商品不存在！");
        return ServerResponse.createBySuccess(productVo);
    }

    @Override
    public ServerResponse<PageInfo> getProductByKeywordCategory(String keyword,Integer  place, Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        if(StringUtils.isNotBlank(keyword))
            keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        List<ProductVo> productVoList= productMapper.selectByNameAndCategoryIds(StringUtils.isBlank(keyword)?null:keyword,place);
        PageInfo pageInfo = new PageInfo(productVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }


}



