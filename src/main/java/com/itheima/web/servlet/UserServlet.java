package com.itheima.web.servlet;

import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author tuuli
 * @time Created in 2022/11/13 17:09
 * @description
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    private UserService service = new UserServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //获取复选框数据
        String remember = request.getParameter("remember");

        User user = service.login(username, password);

        if (user != null){
            //登录成功
            if ("1".equals(remember)){ //勾选了“记住我”
                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);
                Cookie c_remember = new Cookie("remember", remember);

                c_username.setMaxAge(60*60*24*7);//存储7天
                c_password.setMaxAge(60*60*24*7);//存储7天
                c_remember.setMaxAge(60*60*24*7);//存储7天

                response.addCookie(c_username);
                response.addCookie(c_password);
                response.addCookie(c_remember);
            }
            response.sendRedirect("/brand-case/brand.html");
        }else {
            //登录失败
            request.setAttribute("login_msg","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);

        }
    }


    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //封装
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //获取用户输入的验证码
        String checkCode = request.getParameter("checkCode");
        //从session获取程序生成的验证码
        HttpSession session = request.getSession();
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");
        //对比验证码
        if (!checkCodeGen.equalsIgnoreCase(checkCode)){
            request.setAttribute("register_msg","验证码错误");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }

        boolean flag = service.register(user);

        if (flag){
            request.setAttribute("register_msg","注册成功，请登录");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }else {
            request.setAttribute("register_msg","用户名已存在");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
    }


}
