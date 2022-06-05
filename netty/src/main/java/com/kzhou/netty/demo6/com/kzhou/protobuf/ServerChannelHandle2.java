package com.kzhou.netty.demo6.com.kzhou.protobuf;

import com.kzhou.protobuf.AllData;
import com.kzhou.protobuf.DataInfo;
import com.kzhou.protobuf.DataInfo2;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description 类的作用藐视
 * @Author ZhouKaiDong
 * @Date 2022/3/11 10:35
 **/
public class ServerChannelHandle2 extends SimpleChannelInboundHandler<AllData.Message> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AllData.Message msg) throws Exception {
        if(AllData.Message.MessageType.STUDENT.equals(msg.getMessageType())){
            AllData.Student student = msg.getStudent();
            System.out.println(student.getName());
            System.out.println(student.getIdCard().getIdNumber());
            System.out.println(student.getType());
        }else if(AllData.Message.MessageType.PERSON.equals(msg.getMessageType())){
            AllData.Person person = msg.getPerson();
            System.out.println(person.getName());
            System.out.println(person.getEmail());
        }
    }
}
