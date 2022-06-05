package com.kzhou.netty.demo1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * @Description 类的作用藐视
 * @Author ZhouKaiDong
 * @Date 2022/3/11 10:35
 **/
public class MyChannelHandle1 extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * ChannelHandle1是处理Channel管道中字节进出的处理器。用pipeline进行串联起来。
     * ChannelInboundHandler和ChannelOutboundHandler是最上层的接口。处理进出字节或文本信息。
     *  常见解码器 ByteToMessageDecoder ReplayingDecoder MessageToMessageDecoder
     *  常见编码器 MessageToByteEncoder MessageToMessageEncoder
     *  常见编解码器 ByteToMessageCodec  MessageToMessageCodec
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        if(httpObject instanceof HttpRequest){
            HttpRequest httpRequest = (HttpRequest)httpObject;
            HttpMethod method = httpRequest.method();
            // System.out.println("请求方法>>>" + method.name());
            URI uri = new URI(httpRequest.uri());
            if("/favicon.ico".equals(uri.getPath())){
                return;
            }
            ByteBuf content = Unpooled.copiedBuffer("Hello World", StandardCharsets.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
            // write()只是放到了缓冲区, writeAndFlush()返回回去
            // channelHandlerContext.write(response);
            channelHandlerContext.writeAndFlush(response);
            /**
             * Http协议,可以主动关闭,否则就需要TCP的最长等待时间进行被动关闭
             */
            channelHandlerContext.channel().close();
        }
    }

    /**
     *
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("事件监听:channelRead>>>" + msg);
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("事件监听:channelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("事件监听:channelUnregistered");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("事件监听:channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("事件监听:channelInactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("事件监听:channelReadComplete");
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("事件监听:userEventTriggered");
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("事件监听:channelWritabilityChanged");
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("事件监听:exceptionCaught");
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("事件监听:handlerAdded");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("事件监听:handlerRemoved");
        super.handlerRemoved(ctx);
    }
}
