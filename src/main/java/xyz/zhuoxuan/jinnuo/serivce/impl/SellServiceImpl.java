package xyz.zhuoxuan.jinnuo.serivce.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.zhuoxuan.jinnuo.commom.ServerResponse;
import xyz.zhuoxuan.jinnuo.entity.Client;
import xyz.zhuoxuan.jinnuo.entity.Product;
import xyz.zhuoxuan.jinnuo.entity.Sell;
import xyz.zhuoxuan.jinnuo.entity.SellProduct;
import xyz.zhuoxuan.jinnuo.mapper.ClientMapper;
import xyz.zhuoxuan.jinnuo.mapper.ProductMapper;
import xyz.zhuoxuan.jinnuo.mapper.SellMapper;
import xyz.zhuoxuan.jinnuo.mapper.SellProductMapper;
import xyz.zhuoxuan.jinnuo.pojo.Receive;
import xyz.zhuoxuan.jinnuo.serivce.IClientService;
import xyz.zhuoxuan.jinnuo.serivce.ISellService;
import xyz.zhuoxuan.jinnuo.serivce.ex.ProductQuantityNotSufficientFoundException;
import xyz.zhuoxuan.jinnuo.serivce.ex.UpdateException;
import xyz.zhuoxuan.jinnuo.util.DateTimeUtil;
import xyz.zhuoxuan.jinnuo.vo.SellVo;

import java.util.List;

@Service
public class SellServiceImpl  implements ISellService {

    Logger log = LoggerFactory.getLogger(SellServiceImpl.class);

    @Autowired
    private SellMapper sellMapper;

    @Autowired
    private SellProductMapper sellProductMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private IClientService clientService;

    @Transactional
    @Override
    public ServerResponse insert(Receive receive) {
        List<Product> productList;
        Integer sellId = null;
        for (int i = 0; i < receive.getProductName().length; i++) {
            if (StringUtils.equals(receive.getProductName()[i],""))
                continue;
            productList = productMapper.findByName(receive.getProductName()[i]);
            if (productList.isEmpty())
                return ServerResponse.createByErrorMessage("暂无" + receive.getProductName()[i] + "的商品商品名或有误！");
            sellId = execute(productList, receive, i, sellId ,receive.getNum()[i]);
        }
        return ServerResponse.createBySuccess("添加成功！");
    }

    @Override
    public ServerResponse list(String endTime) {
        if (StringUtils.isNotBlank(endTime) && !"".equals(endTime))
            endTime = new StringBuilder().append("%").append(endTime).append("%").toString();
        if (StringUtils.equals(endTime,"") || endTime == null)
            endTime = null;
        List<SellVo> sellVoList = sellMapper.selectByEndTime(endTime);
        return ServerResponse.createBySuccess(sellVoList);
    }

    private Integer execute(List<Product> productList, Receive receive, Integer i, Integer sellId ,Integer num) {
        for (Product product : productList) {
            if (product.getPlace() == receive.getPlace()) {
                if (i == 0) {
                    Client client = clientMapper.findByNameAndPhone(receive.getClientName(), receive.getPhone());
                    if (client == null) {
                        client = new Client();
                        client.setName(receive.getClientName());
                        client.setPhone(receive.getPhone());
                        client.setIsDefault(0);
                        clientService.addClient(client);//默认是正常 ，抛异常
                    }
                    Sell sell = getSell(client, receive);
                    Integer rows = sellMapper.insert(sell);
                    if (rows != 1)
                        throw new UpdateException("sell:insert:数据更新时出现异常");
                    sellId = sell.getId();
                }
                SellProduct sellProduct = getSellProduct(product, sellId, receive ,num);
                Integer rows = sellProductMapper.insert(sellProduct);
                if (rows != 1)
                    throw new UpdateException("sell:insert:数据更新时出现异常");
                num = product.getStock() - num;
                if (num <= 0)
                    throw new ProductQuantityNotSufficientFoundException("库存数量不足");
                rows = productMapper.updateStock(product.getId(), num);
                if (rows != 1)
                    throw new UpdateException("product:updateStock:数据更新时出现异常");
                log.info(receive.getUid() + "号操作员添加销售记录成功");
                return sellId;
            }
        }
        return null;
    }

    private SellProduct getSellProduct(Product product, Integer sellId, Receive receive ,Integer num) {
        SellProduct sellProduct = new SellProduct();
        sellProduct.setSellId(sellId);
        sellProduct.setCategoryId(product.getCategoryId());
        sellProduct.setProductId(product.getId());
        sellProduct.setProductName(product.getName());
        sellProduct.setNum(num);
        sellProduct.setPrice(product.getPrice());
        sellProduct.setPlace(product.getPlace());
        return sellProduct;
    }

    private Sell getSell(Client client, Receive receive) {
        Sell sell = new Sell();
        sell.setClientId(client.getId());
        sell.setPayType(receive.getPayType());
        sell.setPayment(receive.getPayment());
        sell.setEndTime(DateTimeUtil.strToDate(receive.getEndTime()));
        return sell;
    }

}
