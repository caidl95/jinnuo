package xyz.zhuoxuan.jinnuo.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.zhuoxuan.jinnuo.base.conreoller.BaseController;
import xyz.zhuoxuan.jinnuo.commom.ServerResponse;
import xyz.zhuoxuan.jinnuo.pojo.ProductRequest;
import xyz.zhuoxuan.jinnuo.serivce.IFileService;
import xyz.zhuoxuan.jinnuo.serivce.IProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IFileService fileService;

    @PostMapping("/add")
    @Transactional
    public ServerResponse<String> addProduct(ProductRequest productRequest, HttpSession session, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (getRoleAdminFromSession(session)) {
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = fileService.upload(file, path);
            String url = path + "/" + targetFileName;
            productRequest.setMainImage(url);
            return productService.add(productRequest);
        } else {
            return ServerResponse.createByErrorMessage("无此操作权限");
        }
    }

    @RequestMapping("/{id}/delete")
    public ServerResponse<String> delete(@PathVariable("id") Integer id, HttpSession session, HttpServletRequest request) {
        if (getRoleAdminFromSession(session)) {
            return productService.delete(id);
        } else {
            return ServerResponse.createByErrorMessage("无此操作权限");
        }
    }

    @PostMapping("/update")
    public ServerResponse<String> update(ProductRequest productRequest, HttpSession session) {
        if (getRoleAdminFromSession(session)) {
            return productService.update(productRequest);
        } else {
            return ServerResponse.createByErrorMessage("无此操作权限");
        }
    }

    @GetMapping("/list")
    public ServerResponse<PageInfo> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "place", required = false) Integer place,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            HttpSession session) {
        if (!getRoleAdminFromSession(session))
            place = 2;//非管理员只能查询店面商品
        return productService.getProductByKeywordCategory(keyword, place, pageNum, pageSize);
    }

    @RequestMapping("/{id}/product")
    public ServerResponse getProductById(@PathVariable("id") Integer id){
        return productService.getProductById(id);
    }

    /**
     * 上传图片
     */
    @PostMapping("/upload")
    @Transactional
    public ServerResponse upload(Integer id, HttpSession session, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (getRoleAdminFromSession(session)) {
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = fileService.upload(file, path);
            String url = path + targetFileName;
            ProductRequest product = new ProductRequest();
            product.setId(id);
            product.setMainImage(url);
            return productService.update(product);
        } else {
            return ServerResponse.createByErrorMessage("无此操作权限");
        }

    }


}
