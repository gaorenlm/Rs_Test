package com.test;

import com.json.JacksonUtil;
import com.json.TreeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liming on 15/4/21.
 */
public class mainTest {
    public static void main(String[] args) {

        List a1 = new ArrayList();

//        TreeBean t1 = new TreeBean("1", "0", "一级目录", "true", null);
//        TreeBean t2 = new TreeBean("12", "1", "二级目录", "true", "true");
//        TreeBean t3 = new TreeBean("13", "12", "三级目录", "true", null);
//        TreeBean t4 = new TreeBean("21", "1", "二级目录", "true", "true");
//        TreeBean t5 = new TreeBean("22", "21", "三级目录", "true", null);
//        TreeBean t6 = new TreeBean("23", "21", "三级目录", "true", null);
//        a1.add(t1);
//        a1.add(t2);
//        a1.add(t3);
//        a1.add(t4);
//        a1.add(t5);
//        a1.add(t6);


        //JacksonUtil.toJson(a1);
        System.out.println(JacksonUtil.toJson(a1));
    }
}
