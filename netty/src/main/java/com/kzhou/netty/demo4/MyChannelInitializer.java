package com.kzhou.netty.demo4;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/3/17 10:00
 **/
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //添加延时处理器。3s 5s 6s内channel没有可读，可写，可读可写，将会处罚事件到下一个处理器
        pipeline.addLast(new IdleStateHandler(3,5,6, TimeUnit.SECONDS));
        pipeline.addLast(new MyChannelHandle());
    }
}
