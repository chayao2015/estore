package cn.itcast.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试
 * @author Administrator
 */
public class ServletDemo extends BaseServlet {
	
	private static final long serialVersionUID = 2947357007498494328L;
	
	public String addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("addUser...");
		
		// 想做重定向
		// response.sendRedirect("");
		
		return "ss";
	}
	
	public void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("updateUser...");
	}
	public void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("deleteUser...");
	}
}
