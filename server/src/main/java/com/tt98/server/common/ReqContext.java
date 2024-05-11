package com.tt98.server.common;

public class ReqContext {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }

    public static void removeCurrentId(){
        threadLocal.remove();
    }
}
