package com.json;

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
@WebServlet(name = "TestServlet", urlPatterns = {"/a"})
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestDate(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestDate(request, response);
    }


    private void requestDate(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id=request.getParameter("id");

        System.out.println(id);

        List a1 = new ArrayList();

        //if(id==null){
            TreeBean t1 = new TreeBean("1", "0", "一级目录", "true", "true","0");
            a1.add(t1);
       // }else{
            //if(id.equals("1")){
                TreeBean t2 = new TreeBean("12", "1", "二级目录", "true", "true","1");
                TreeBean t4 = new TreeBean("21", "1", "二级目录", "true", "true","1");
                a1.add(t2); a1.add(t4);
            //}
            //if(id.equals("12")){
                TreeBean t3 = new TreeBean("13", "12", "三级目录", "true", null,"2");
                a1.add(t3);
            //}
            //if(id.equals("21")){
                TreeBean t5 = new TreeBean("22", "21", "三级目录", "true", null,"3");
                TreeBean t6 = new TreeBean("23", "21", "三级目录", "true", null,"4");
                a1.add(t5);
                a1.add(t6);

                a1.add(new TreeBean("23", "21", "5级目录", "true", null,"5"));
                a1.add(new TreeBean("23", "21", "6级目录", "true", null,"6"));
                a1.add(new TreeBean("23", "21", "7级目录", "true", null,"7"));
                a1.add(new TreeBean("23", "21", "8级目录", "true", null,"8"));
                a1.add(new TreeBean("23", "21", "9级目录", "true", null,"9"));
                a1.add(new TreeBean("23", "21", "10级目录", "true", null,"10"));
                a1.add(new TreeBean("23", "21", "11级目录", "true", null,"11"));


            //}
       // }
        //JacksonUtil.toJson(a1);
        //System.out.println(JacksonUtil.toJson(a1));
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.getWriter().write(JacksonUtil.toJson(a1));
    }




}
