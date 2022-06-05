package com.kzhou.netty.demo3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description 类的作用藐视
 * @Author ZhouKaiDong
 * @Date 2022/3/14 21:10
 **/
public class ChatClientHandlerCustomer extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("收到消息：" + msg);
    }

}
