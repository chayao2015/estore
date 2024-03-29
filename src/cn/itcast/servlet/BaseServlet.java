package cn.itcast.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * BaseServlet通用的Servlet
 * @author Administrator
 *
 */
public class BaseServlet extends HttpServlet{
	
	private static final long serialVersionUID = -7510809537032738047L;

	/**
	 * 服务器会调用该service方法
	 */
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// 设置编码
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		// 可以进行一些判断控制	
		// 对用户有一些要求：给我传过来取你要调用的方法  http://localhost/estore/demo?method=xxxUser
		// addUser方法的签名也有要求：addUser(HttpServletRequest request, HttpServletResponse response)
		// 参数必须是HttpServletRequest request,HttpServletResponse response
		
		// 获取method参数
		String methodName = req.getParameter("method");
		// 如果methodName忘了传入了
		if(methodName == null || methodName.trim().isEmpty()){
			throw new RuntimeException("亲，请您传入method参数！！");
		}
		// 通过反射的技术，来完成该事
		// 获取Class对象，获取method对象。
		Class clazz = this.getClass();
		// 通过clazz获取method对象
		Method method = null;
		try {
			method = clazz.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("亲，您传入的"+methodName+"不存在！！");
		} 
		
		try {
			// method肯定有值了，让method方法执行
			String result = (String) method.invoke(this, req,resp);
			// /jsps/msg.jsp
			
			if(result != null && !result.trim().isEmpty()){
				req.getRequestDispatcher(result).forward(req, resp);
			}
			
		} catch (Exception e) {
			System.out.println("程序内部错误");
			throw new RuntimeException(e);
		}
	}

}
