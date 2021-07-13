package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;



@WebServlet("/gbc")
public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("controller");
		request.setCharacterEncoding("UTF-8");
		
		//파라미터 action!
		String action = request.getParameter("action");
		System.out.println(action);
		
		if("addlist".equals(action)) {
		//listing
			System.out.println("[listing]");
			
			//List
			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> guestList = guestbookDao.getList();
			
			//Data attibute
			request.setAttribute("gList", guestList);
			
			WebUtil.forward(request, response, "WEB-INF/addList.jsp");
			
		} else if("add".equals(action)) {
		//add
			System.out.println("add");
			
			//parameter가져오기
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			//값넣기
			GuestbookVo guestbookVo = new GuestbookVo(name, password, content);
			
			//db 반영
			GuestbookDao guestbookDao = new GuestbookDao();
			guestbookDao.insert(guestbookVo);
			
			WebUtil.redirect(request, response, "/guestbook2/gbc?action=addList");
			
		}else if("delete".equals(action)) {
		//delete
			System.out.println("delete");
			
			//parameter 호출
			int idNum = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			
			//값넣기 
			GuestbookVo guestbookVo = new GuestbookVo(idNum, password);
			
			//db반영
			GuestbookDao guestbookDao = new GuestbookDao();
			guestbookDao.delete(guestbookVo);
			
			WebUtil.redirect(request, response, "/guestbook2/gbc?action=addList");
			
		}else if("dform".equals(action)) {
		//deleteForm
			System.out.println("[dform]");
			
			//parameter 호출
			int idNum = Integer.parseInt(request.getParameter("no"));
			
			//Data attribute
			request.setAttribute("no", idNum);
			
		
			WebUtil.forward(request, response, "WEB-INF/deleteForm.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
