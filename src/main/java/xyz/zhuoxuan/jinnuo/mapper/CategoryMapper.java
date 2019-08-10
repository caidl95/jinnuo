package xyz.zhuoxuan.jinnuo.mapper;

import xyz.zhuoxuan.jinnuo.entity.Category;
import java.util.List;

public interface CategoryMapper  {


    Integer insert(Category category);

    Integer updateByPrimaryKeySelective(Category category);

    Category selectById(Integer id);

    Category selectByName(String name);

    List<Category> selectCategoryChildrenByParentId(Integer parentId);

}
