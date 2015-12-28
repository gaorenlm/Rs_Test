package com.kevin.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

/**
 *
 * Created by liming on 15/5/12.
 */
@WebServlet("/KevinServlet")
public class KevinServlet extends HttpServlet {

//    public Logger log=LoggerFactory.getLogger(KevinServlet.class);
    private static Logger log = LogManager.getLogger(KevinServlet.class);
    private static final Marker QUERY_MARKER = MarkerManager.getMarker("SQL");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


      List list=  new ArrayList();

        list.add("llllllllhgjkhgjhgjkkj框架和国际化个健康化工llllllllllllll");
        list.add("a");

        log.debug(QUERY_MARKER, "SELECT * FROM {}", "TB_table");

        System.out.printf(String.valueOf(postanget(list).size()));
    }


    private List postanget(List aa) {

            log.entry(aa,aa);

        return log.exit(aa);
    }
}
