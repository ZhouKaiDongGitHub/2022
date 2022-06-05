package com.kzhou.springcloud;

import java.util.LinkedList;
import java.util.Random;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/3/31 14:21
 **/
public class RollingWindow {
    /**
     * 滑动窗口限流实现，假如某服务最多只能每秒钟处理100请求，我们设置一个1s钟的滑动时间窗口，
     * 窗口钟有10个格子，每个格子100ms，每100ms移动一次，每次移动都需要记录当前服务器请求的次数
     */
    static Long counter = 0L; // 服务访问次数，可以放到redis实现分布式系统的访问计数
    static LinkedList<Long> slots = new LinkedList<>(); // 使用LinkedList记录滑动窗口的10个格子

    public static void main(String[] args) throws Exception{
        new Thread(() -> {
            try {
                doCheck();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        while (true){
            // 判断限流标记 TODO
            counter ++;
            Thread.sleep(new Random().nextInt(20));
        }
    }
    private static void doCheck() throws InterruptedException {
        while (true){
            slots.addLast(counter);
            if(slots.size() > 10){
                slots.removeFirst();
            }
            // 比较最后一个值和第一个值，相差100以上就限流
            if((slots.peekLast() - slots.peekFirst()) > 100){
                System.out.println("限流了...." + (slots.peekLast() - slots.peekFirst()));
                // 修改限流标记为true TODO
            }else {
                // 修改限流标记为false  TODO
                System.out.println("不限流....");
            }
            Thread.sleep(100);
        }
    }

}
