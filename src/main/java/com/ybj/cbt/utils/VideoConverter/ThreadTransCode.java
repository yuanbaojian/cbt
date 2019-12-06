package com.ybj.cbt.utils.VideoConverter;

import org.junit.Test;

import java.util.Date;

public class ThreadTransCode implements Runnable {

    //原始文件
    private String videoPath;
    //目标文件
    private String targetPath;

    public ThreadTransCode(String videoPath, String targetPath) {
        this.videoPath = videoPath;
        this.targetPath = targetPath;
    }

    @Override
    public void run() {
        synchronized (this) {
            Date startDate = new Date();
            System.out.println("一个线程转码开始..............");
            System.out.println("开始时间 " + startDate.toString());
            ConvertVideo cv = new ConvertVideo(videoPath, targetPath);
            cv.process();
            Date endDate = new Date();
            System.out.println("一个线程转码结束");
            System.out.println("结束时间 " + endDate.toString());
        }
    }

    public static void main(String[] args) {
        Date startDate = new Date();
        System.out.println("开始时间 " + startDate.toString());
        String vidoePath = "C:\\Users\\baojian.yuan\\Desktop\\test\\bigAviFile.avi";
        String targetPath = "C:\\Users\\baojian.yuan\\Desktop\\test\\bigMp4File.mp4";
        ConvertVideo cv = new ConvertVideo(vidoePath, targetPath);
        cv.process();
        Date endDate = new Date();
        System.out.println("结束时间 " + endDate.toString());
        System.out.println("花费时间  " + (endDate.getTime() - startDate.getTime()) / 1000 + " 秒");
    }

    @Test
    public void test() {
        Date startDate = new Date();
        System.out.println("开始时间 " + startDate.toString());
        String vidoePath = "C:\\Users\\baojian.yuan\\Desktop\\test\\aviVideo1.avi";
        String targetPath = "C:\\Users\\baojian.yuan\\Desktop\\test\\mp4Video1.mp4";
        ConvertVideo cv = new ConvertVideo(videoPath, targetPath);
        cv.process();
        Date endDate = new Date();
        System.out.println("结束时间 " + endDate.toString());
        System.out.println("花费时间  " + (endDate.getTime() - startDate.getTime()) / 1000 + " 秒");
    }
}