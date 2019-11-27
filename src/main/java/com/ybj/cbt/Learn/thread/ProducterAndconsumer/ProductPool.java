package com.ybj.cbt.Learn.thread.ProducterAndconsumer;

import java.util.LinkedList;
import java.util.List;

public class ProductPool {

    private List<Product> productList;

    private int maxSize=10;

    public ProductPool(int maxSize) {
        this.productList=new LinkedList<Product>();
        this.maxSize = maxSize;
    }

    public synchronized void push(Product product){
        if(productList.size()==maxSize){
            try {
//                判断是否能继续生产
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.productList.add(product);
//        通知其他人可以消费了
        this.notifyAll();


    }

    public synchronized Product pop(){
        if(productList.size()==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        取出一件商品
        Product product=productList.remove(0);
        this.notifyAll();

        return product;
    }
}
