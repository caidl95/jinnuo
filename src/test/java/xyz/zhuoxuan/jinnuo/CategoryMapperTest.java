package xyz.zhuoxuan.jinnuo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.zhuoxuan.jinnuo.entity.Category;
import xyz.zhuoxuan.jinnuo.mapper.CategoryMapper;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryMapperTest {
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void select(){
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(100001);
        System.err.println(categoryList);
    }

    @Test
    public void insert(){
        Category category = new Category();
        category.setName("家具");
        category.setParentId(0);
        Integer rows = categoryMapper.insert(category);
        System.err.println(rows);
    }

}
