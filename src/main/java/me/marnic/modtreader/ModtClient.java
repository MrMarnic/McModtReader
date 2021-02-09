package me.marnic.modtreader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import me.marnic.modtreader.packet.codec.PacketDecoder;
import me.marnic.modtreader.packet.codec.PacketEncoder;
import me.marnic.modtreader.packet.codec.PacketPipelineHandler;
import me.marnic.modtreader.packet.packets.status.server.HandshakePacket;
import me.marnic.modtreader.packet.packets.status.server.RequestPacket;

/**
 * Copyright (c) 09.02.2021
 * Developed by MrMarnic
 * GitHub: https://github.com/MrMarnic
 */
public class ModtClient {
    private String host;
    private int port;
    private EventLoopGroup group;

    public ModtClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.group = new NioEventLoopGroup();
    }

    public void start() {
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(this.group);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE,true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) {
                    ChannelPipeline pipeline = channel.pipeline();

                    pipeline.addLast(new PacketDecoder(),new PacketEncoder(),new PacketPipelineHandler() {
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) {
                            onConnect(ctx);
                        }
                    });
                }
            });

            ChannelFuture future = bootstrap.connect(this.host,this.port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.group.shutdownGracefully();
        }
    }

    public void onConnect(ChannelHandlerContext ctx) {

    }
}
