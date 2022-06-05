package com.kzhou.netty.demo1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/3/11 10:06
 **/
public class NettyDemoServer {
    /**
     * Netty作为网络框架，一般应用于RPC远程调用。由于是直接基于TCP进行的通讯，常用的应用场景
     * 1. Http服务器，频繁的密集的网络请求客户端服务端互调。
     * 2. WebSocket长连接服务器，优雅的前后端互动。
     * 3. Web应用容器。可以代替Tomcat作服务器，只是不是基于Servlet规范的。无法和SpringMVC进行集成。
     */
    public static void main(String[] args) throws InterruptedException {
        /**
         * 定于2个死循环线程组：
         * 一个用来处理TCP上的网络事件接收和分发，和Reactor Select相关
         * 另一个用来处理真正的网络事件，例如链接，断开，读，写等等的处理，和具体的SelectKey相关
         */
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        /**
         * 服务器启动类，集成了Group,Channel,option,attributes等
         * pipeline,channel,channelHandler 3者的关系
         */
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast("httpServerCodec",new HttpServerCodec());
                            pipeline.addLast("myChannelHandle1",new MyChannelHandle1());
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(8081).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
