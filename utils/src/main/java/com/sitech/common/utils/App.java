package com.sitech.common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

/**
 * Hello world!
 */
public class App {
    private static Logger log = LogManager.getLogger(App.class);

    private static final Marker QUERY_MARKER = MarkerManager.getMarker("SQL");

    public static void main(String[] args) {
        log.info("sdv");
        System.out.println("sdsddd");


        try {
            throw new Exception("aewfwefwefwefwefwefe");
        }catch (Exception e){
            log.error("异常",e);
        }
        log.debug("vvvvvvv");
    }
}
