package xyz.zhuoxuan.jinnuo.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import xyz.zhuoxuan.jinnuo.commom.ServerResponse;
import xyz.zhuoxuan.jinnuo.serivce.ex.ServiceException;

import java.util.List;

/**
 * 
 * 分页工具类
 */
public class PageUtil {
	
	private PageUtil() {
		throw new RuntimeException("new PageUtil instance error");
	}
	
	/**
	 * 设置分页属性
	 * 
	 * @param pageIndex:页码
	 * @param pageSize:一页大小
	 */
	public static void setPage(Integer pageIndex, Integer pageSize) {
		if (pageIndex == null || pageSize == null) {
			PageHelper.startPage(1, Integer.MAX_VALUE);
		} else {
			if (pageIndex <= 0 || pageSize <= 0) {
				ServiceException.throwException("分页信息错误");
			}
			PageHelper.startPage(pageIndex, pageSize);
		}
	}

	/**
	 * 设置分页信息对象
	 * @param <T> 泛型
	 * @param list:分页数据
	 * @return PageInfo:分页信息
	 */
	public static <T> PageInfo<T> getPageInfo(List<T> list) {
		return new PageInfo<T>(list);
	}

	/**
	 * 获取分页jsonResult对象
	 * @param <T> 泛型
	 * @param list:分页数据
	 * @return ServerResponse
	 */
	public static <T> ServerResponse<PageInfo<T>> getPageJsonResult(List<T> list) {
		return ServerResponse.createBySuccess(new PageInfo<T>(list));
	}
}
