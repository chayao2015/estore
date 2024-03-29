package cn.itcast.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.service.BookService;
import cn.itcast.service.CategoryService;
import cn.itcast.utils.MyUUIDUtil;
import cn.itcast.vo.Book;
import cn.itcast.vo.Category;

/**
 * 分类的控制器
 * @author Administrator
 *
 */
public class CategoryServlet extends BaseServlet {

	private static final long serialVersionUID = 7523447216890672217L;

	/**
	 * 查询所有的分类的信息，转发到left.jsp
	 * @param request
	 * @param response
	 * @return
	 */
	public String findAll(HttpServletRequest request,HttpServletResponse response){
		// 调用业务层的代码
		CategoryService cs = new CategoryService();
		// 查询所有的分类
		List<Category> cList = cs.findAll();
		// 存入到request域中，转发到 /jsps/left.jsp
		request.setAttribute("cList", cList);
		return "/jsps/left.jsp";
	}
	
	/**
	 * 后台的查询所有的分类代码
	 * @param request
	 * @param response
	 * @return
	 */
	public String findAllAdmin(HttpServletRequest request,HttpServletResponse response){
		CategoryService cs = new CategoryService();
		// 查询所有的分类
		List<Category> cList = cs.findAll();
		// 存入到request域中，转发到 /jsps/left.jsp
		request.setAttribute("cList", cList);
		
		return "/adminjsps/admin/category/list.jsp";
	}
	
	/**
	 * 添加分类
	 * @param request
	 * @param response
	 * @return
	 */
	public String addCategoryAdmin(HttpServletRequest request,HttpServletResponse response){
		// 获取数据
		String cname = request.getParameter("cname");
		
		Category c = new Category();
		c.setCid(MyUUIDUtil.getUUID());
		c.setCname(cname);
		
		CategoryService cs = new CategoryService();
		// 保存数据
		cs.save(c);
		
		// 直接返回到
		return findAllAdmin(request,response);
	}
	
	/**
	 * 初始化到修改页面
	 * @param request
	 * @param response
	 * @return
	 */
	public String initUpdateAdmin(HttpServletRequest request,HttpServletResponse response){
		// 接收分类的id
		String cid = request.getParameter("cid");
		// 去数据库查询
		CategoryService cs = new CategoryService();
		// 通过分类的的主键
		Category category = cs.findByCid(cid);
		
		request.setAttribute("category", category);
		
		return "/adminjsps/admin/category/mod.jsp";
	}
	
	/**
	 * 修改的代码
	 * @param request
	 * @param response
	 * @return
	 */
	public String updateAdmin(HttpServletRequest request,HttpServletResponse response){
		// 接收参数
		String cid = request.getParameter("cid");
		String cname = request.getParameter("cname");
		
		Category c = new Category();
		c.setCid(cid);
		c.setCname(cname);
		
		CategoryService cs = new CategoryService();
		cs.update(c);
		
		return findAllAdmin(request,response);
	}
	
	/**
	 * 删除分类
	 * @param request
	 * @param response
	 * @return
	 */
	public String deleteAdmin(HttpServletRequest request,HttpServletResponse response){
		// 如果分类下包含图书的信息，不能删除
		// 通过分类，来查询该分类下是否包含图书
		// 接收分类的cid
		String cid = request.getParameter("cid");
		// 通过分类的id，去查询该分类下是否包含图书
		BookService bs = new BookService();
		// 查询的该分类下图书的isdel为0的集合
		// 
		List<Book> bookList = bs.findByCid(cid);
		// 说明分类包含图书
		if(bookList != null && bookList.size() > 0){
			// 提示用户
			request.setAttribute("msg","亲，该分类下包含图书，不能删除");
		}else{
			// 分类下没有图书可以直接删除
			CategoryService cs = new CategoryService();
			
			// 修改book的cid=null  where cid=cid and isdel = 1
			bs.updateByCid(cid);
			
			// 删除分类
			cs.delete(cid);
			// 删除成功
		}
		return findAllAdmin(request,response);
	}
	
}









