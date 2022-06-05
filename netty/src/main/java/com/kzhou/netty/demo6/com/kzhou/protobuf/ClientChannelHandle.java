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
public class ClientChannelHandle extends SimpleChannelInboundHandler<DataInfo.Student> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("周开冬")
                .setAge(26)
                .setAddress("苏州市")
                .setIdCard(DataInfo.Student.IdCard.newBuilder()
                        .setIdNumber("320923199511636327")
                        .setIdType("1").build())
                .setType(DataInfo.Student.PhoneType.WORK)
                .build();
        // VCS instead byte[] studentBytes = student.toByteArray();
        // ctx.writeAndFlush(student);

        DataInfo2.Person person = DataInfo2.Person.newBuilder()
                .setName("路白衣")
                .setEmail("zhoukaidong1126@163.com")
                .build();
        // ctx.writeAndFlush(person);
        AllData.Message message = AllData.Message.newBuilder()
                .setMessageType(AllData.Message.MessageType.STUDENT)
                .setStudent(AllData.Student.newBuilder()
                        .setName("周开冬")
                        .setAge(26)
                        .setAddress("苏州市")
                        .setIdCard(AllData.Student.IdCard.newBuilder()
                                .setIdNumber("320923199511636327")
                                .setIdType("1")
                                .build())
                        .setType(AllData.Student.PhoneType.WORK)
                        .build())
                .build();
        ctx.writeAndFlush(message);
    }
}
