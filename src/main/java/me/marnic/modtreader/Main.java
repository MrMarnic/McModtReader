package me.marnic.modtreader;

import io.netty.channel.ChannelHandlerContext;
import me.marnic.modtreader.packet.packets.BasicPacket;
import me.marnic.modtreader.packet.packets.PacketHandler;
import me.marnic.modtreader.packet.packets.PacketRegistry;
import me.marnic.modtreader.packet.packets.status.client.PongPacket;
import me.marnic.modtreader.packet.packets.status.client.ResponsePacket;
import me.marnic.modtreader.packet.packets.status.server.HandshakePacket;
import me.marnic.modtreader.packet.packets.status.server.PingPacket;
import me.marnic.modtreader.packet.packets.status.server.RequestPacket;

/**
 * Copyright (c) 09.02.2021
 * Developed by MrMarnic
 * GitHub: https://github.com/MrMarnic
 */
public class Main {

    public static void main(String[] args) {
        PacketRegistry.register(0x00, ResponsePacket.class,new PacketHandler<ResponsePacket>() {
            @Override
            public void onPacket(ResponsePacket packet, ChannelHandlerContext ctx) {
                System.out.println(packet.getJsonResponse());
                ctx.writeAndFlush(new PingPacket(System.currentTimeMillis()));
            }
        });
        PacketRegistry.register(0x01, PongPacket.class,new PacketHandler<PongPacket>() {
            @Override
            public void onPacket(PongPacket packet, ChannelHandlerContext ctx) {
                System.out.println(System.currentTimeMillis() - packet.getTime() + " ms");
                ctx.close();
            }
        });

        ModtClient client = new ModtClient("GommeHd.net",25565) {
            @Override
            public void onConnect(ChannelHandlerContext ctx) {
                try {
                    ctx.writeAndFlush(new HandshakePacket(754,"GommeHd.net",(short)25565,1)).sync();
                    ctx.writeAndFlush(new RequestPacket());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        client.start();
    }
}
