package me.marnic.modtreader.packet.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import me.marnic.modtreader.packet.packets.BasicPacket;
import me.marnic.modtreader.packet.packets.PacketRegistry;
import me.marnic.modtreader.packet.packets.status.server.HandshakePacket;
import me.marnic.modtreader.packet.packets.status.server.RequestPacket;

/**
 * Copyright (c) 09.02.2021
 * Developed by MrMarnic
 * GitHub: https://github.com/MrMarnic
 */
public class PacketPipelineHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        PacketRegistry.handle((BasicPacket) msg,ctx);
    }
}
