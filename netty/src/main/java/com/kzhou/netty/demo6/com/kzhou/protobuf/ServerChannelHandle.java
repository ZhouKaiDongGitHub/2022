package com.kzhou.netty.demo6.com.kzhou.protobuf;

import com.kzhou.protobuf.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description 类的作用藐视
 * @Author ZhouKaiDong
 * @Date 2022/3/11 10:35
 **/
public class ServerChannelHandle extends SimpleChannelInboundHandler<DataInfo.Student> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student student) throws Exception {
        // DataInfo.Student student1 = DataInfo.Student.parseFrom(msg);
        System.out.println(student.getName());
        System.out.println(student.getIdCard().getIdNumber());
        System.out.println(student.getType());
    }
}
