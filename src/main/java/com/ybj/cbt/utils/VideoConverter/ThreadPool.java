package com.ybj.cbt.utils.VideoConverter;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    public static ExecutorService exec = Executors.newFixedThreadPool(3);

    public static synchronized void trans(String videoPath, String targetPath) {
        ThreadTransCode trans = new ThreadTransCode(videoPath, targetPath);
        exec.execute(trans);
    }

    public static void main(String[] args) {
        try {
            String vidoePath = "C:\\Users\\baojian.yuan\\Desktop\\test\\aviVideo1.avi";
            String targetPath = "C:\\Users\\baojian.yuan\\Desktop\\test\\mp4Video1.mp4";
            ThreadPool.trans(vidoePath, targetPath);
            String vidoePath1 = "C:\\Users\\baojian.yuan\\Desktop\\test\\aviVideo2.avi";
            String targetPath1 = "C:\\Users\\baojian.yuan\\Desktop\\test\\mp4Video2.mp4";
            ThreadPool.trans(vidoePath1, targetPath1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConvert() {
        try {
            String vidoePath = "C:\\Users\\baojian.yuan\\Desktop\\test\\aviVideo.avi";
            String targetPath = "C:\\Users\\baojian.yuan\\Desktop\\test\\mp4Video.mp4";
            ThreadPool.trans(vidoePath, targetPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}