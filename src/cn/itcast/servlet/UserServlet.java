package cn.itcast.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.service.UserService;
import cn.itcast.vo.User;

/**
 * 和用户相关的Servlet
 * 先改继承BaseServlet
 * @author Administrator
 */
public class UserServlet extends BaseServlet {
	
	private static final long serialVersionUID = -8230838990948681763L;
	
	/**
	 * 注册用户的方法
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String registUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收数据
		// 封装数据
		// 处理数据
		// 显示数据
		
		// 接收数据
		Map<String, String []> map = request.getParameterMap();
		// 封装数据
		User user = new User();
		try {
			BeanUtils.populate(user, map);
			// 处理数据
			UserService us = new UserService();
			// 注册用户
			boolean flag = us.registUser(user);
			// 根据返回的结果，调用不同页面进行处理
			// 注册失败了
			if(flag == false){
				// 向request域中存入消息
				request.setAttribute("msg", "注册失败！！");
			}else{
				// 向request域中存入消息
				request.setAttribute("msg", "注册成功，请您激活！！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 在BaseServlet中完成了转发的功能
		return "/jsps/msg.jsp";
	}
	
	
	/**
	 * 邮件激活用户的方法
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String active(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 先获取code激活码77cec5d12a5840dd88dd6f9a7f0ad2c467c55972837e484d9851fad028169407
		String code = request.getParameter("code");
		// 处理数据
		UserService us = new UserService();
		// 通过激活码查询的用户
		User user = us.findUserByCode(code);
		// 如果用户为空，说明没有查到用户
		if(user == null){
			request.setAttribute("msg", "激活失败了");
		}else{
			// 修改用户的状态，状态默认值0，修改成1.
			user.setState(1);
			// 再调用UserService修改方法
			us.updateUser(user);
			// 设置信息
			request.setAttribute("msg", "激活成功了，请您登陆！！");
		}
		return "/jsps/msg.jsp";
	}
	
	
	/**
	 * 用户登陆的功能
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 获取数据
		 * 封装数据
		 * 处理数据
		 * 显示数据
		 */
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, map);
			UserService us = new UserService();
			// 登陆功能
			User existUser = us.login(user);
			// 如果登陆失败，给出用户提示
			if(existUser == null){
				request.setAttribute("msg", "用户名或者密码错误或者未激活！！");
				return "/jsps/msg.jsp";
			}else{
				// 如果登陆成功，把用户的信息保存到session中，转发到首页
				request.getSession().setAttribute("existUser", existUser);
				// 转发main.jsp的页面上
				return "/jsps/main.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * 用户退出的功能
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String exit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取session，直接销毁
		request.getSession().invalidate();
		return "/jsps/main.jsp";
	}
	
}









