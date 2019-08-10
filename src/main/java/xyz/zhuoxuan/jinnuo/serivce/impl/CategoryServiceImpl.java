package xyz.zhuoxuan.jinnuo.serivce.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import xyz.zhuoxuan.jinnuo.base.service.AbstractService;
import xyz.zhuoxuan.jinnuo.commom.ServerResponse;
import xyz.zhuoxuan.jinnuo.entity.Category;
import xyz.zhuoxuan.jinnuo.mapper.CategoryMapper;
import xyz.zhuoxuan.jinnuo.serivce.ICategoryService;
import xyz.zhuoxuan.jinnuo.serivce.ex.UpdateException;

import java.util.List;
import java.util.Set;


@Service
public class CategoryServiceImpl  implements ICategoryService {

    Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    public ServerResponse addCategory(String categoryName, Integer parentId) {
        if (parentId == null || StringUtils.isBlank(categoryName))
            return ServerResponse.createByErrorMessage("添加品类参数错误");
        Category result = categoryMapper.selectByName(categoryName);
        if (result != null)
            return ServerResponse.createByErrorMessage("已有此份类，无法添加");
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(1);
        category.setSortOrder(5);
        int rowCount = categoryMapper.insert(category);
        if (rowCount != 1)
            throw new UpdateException("数据更新时出现异常");
        return ServerResponse.createBySuccess("添加品类成功");
    }

    @Override
    public ServerResponse updateCategoryName(Integer categoryId, String categoryName) {
        if (categoryId == null || StringUtils.isBlank(categoryName))
            return ServerResponse.createByErrorMessage("更新品类参数错误");
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if (rowCount != 1)
            throw new UpdateException("数据更新时出现异常");
        return ServerResponse.createByErrorMessage("更新品类名成功");
    }


    @Override
    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId) {
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        if (CollectionUtils.isEmpty(categoryList))
            log.info("未找到当前分类的子分类");
        return ServerResponse.createBySuccess(categoryList);
    }

    @Override
    public ServerResponse selectCategoryAndChildrenById(Integer categoryId) {
        Set<Category> categorySet = Sets.newHashSet();//初始化set
        findChildCategory(categorySet, categoryId);
        List<Integer> categoryIdList = Lists.newArrayList();
        if (categoryId != null) {
            for (Category category : categorySet) {
                categoryIdList.add(category.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryIdList);
    }

    /**
     * 递归算法，算出子节点
     */
    private Set<Category> findChildCategory(Set<Category> categorySet, Integer categoryId) {
        Category category = categoryMapper.selectById(categoryId);
        if (category != null)
            categorySet.add(category);
        //查找子节点，递归算法一点要有一个退出条件
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        for (Category categoryNewest : categoryList) {
            findChildCategory(categorySet, categoryNewest.getId());
        }
        return categorySet;
    }
}
