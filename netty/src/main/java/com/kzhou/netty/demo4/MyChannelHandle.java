package com.kzhou.netty.demo4;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/3/17 11:13
 **/
public class MyChannelHandle extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent)evt;
            String eventType = "";
            switch (idleStateEvent.state()){
                case READER_IDLE:
                    eventType = "读延迟";
                    break;
                case WRITER_IDLE:
                    eventType = "写延迟";
                    break;
                case ALL_IDLE:
                    eventType = "读写延迟";
                    break;
            }
            System.out.println(eventType);
        }
    }
}
