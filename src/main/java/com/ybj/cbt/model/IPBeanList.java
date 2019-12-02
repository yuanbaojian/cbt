package com.ybj.cbt.model;

import java.util.LinkedList;
import java.util.List;

public class IPBeanList {


    public static List<IPBean> ipBeanList = new LinkedList<>();

    private static int count = 0;

    public static synchronized void add(IPBean bean) {
        ipBeanList.add(bean);
    }

    public static int getSize() {
        return ipBeanList.size();
    }


    public static synchronized void increase() {
        count++;
    }

    public static synchronized int getCount() {
        return count;
    }

}
