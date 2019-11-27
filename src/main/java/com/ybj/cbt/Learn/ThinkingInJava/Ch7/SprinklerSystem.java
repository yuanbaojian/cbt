package com.ybj.cbt.Learn.ThinkingInJava.Ch7;

class WaterSource{
    private String s;

    /**
     * 构造器在实例化对象后，立刻进行
     */
    public WaterSource() {
        System.out.println("WaterSource");
        s="Constructed in WaterSource";
    }

    /**
     *  每个非基本数据类型都有一个 toString 方法
     * @return
     */
    @Override
    public String toString() {
        return s;
    }
}


public class SprinklerSystem {
    private String value1, value2, value3,value4;
    private WaterSource waterSource=new WaterSource();
    private int i;
    private Float f;

    @Override
    public String toString() {
        return "SprinklerSystem{" +
                "value1='" + value1 + '\'' +
                ", value2='" + value2 + '\'' +
                ", value3='" + value3 + '\'' +
                ", value4='" + value4 + '\'' +
                ", waterSource=" + waterSource +
                ", i=" + i +
                ", f=" + f +
                '}';
    }

  public static void main(String[] args) {
        SprinklerSystem sprinklerSystem=new SprinklerSystem();
        sprinklerSystem.toString();
        System.out.println(sprinklerSystem);
  }
}
