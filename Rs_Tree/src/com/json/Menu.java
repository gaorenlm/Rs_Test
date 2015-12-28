package com.json;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liming on 15/4/21.
 */
@WebServlet(name = "Menu", urlPatterns = {"/m"})
public class Menu extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestDate(request, response);

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestDate(request, response);
    }


    private void requestDate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getServletContext().getRequestDispatcher("/index2.jsp").forward(request,response);
    }


}
