package com.ybj.cbt.Exception;


import javax.swing.plaf.PanelUI;

/***
 * @Description  自建异常类
 * @return
 * @author baojian.yuan
 * @date 2019/10/20
 */
class MyException extends Exception {

    public MyException() {
    }

    public MyException(String message) {
        //把message传给Exception, Exception再传给Throwable
        super(message);
    }

}

public class FullConstructors{

    public static void f() throws MyException {
        System.out.println("Throwing MyException from f()");
//        指定抛出MyException（）异常
        throw new MyException();
    }
    public static void g() throws MyException {
        System.out.println("Throwing MyException from g()");
//指定抛出的异常，并且添加报错信息
        throw new MyException("Originated in g()");
    }
    public static void main(String[] args) {
        try {
            f();
        } catch(MyException e) {
            e.printStackTrace(System.out);
        }
        try {
            g();
        } catch(MyException e) {
            e.printStackTrace(System.out);
        }
    }


}
