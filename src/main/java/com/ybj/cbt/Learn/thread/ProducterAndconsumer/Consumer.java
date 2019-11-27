package com.ybj.cbt.Learn.thread.ProducterAndconsumer;

public class Consumer extends Thread {

    private ProductPool productPool;

    public Consumer(ProductPool productPool){
        this.productPool=productPool;
    }

    @Override
    public void run() {
        while(true){
            Product product=this.productPool.pop();
            System.out.println("消费了一件产品" + product.getName());
        }
    }
}
