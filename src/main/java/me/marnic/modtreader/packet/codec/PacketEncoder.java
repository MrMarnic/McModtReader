package me.marnic.modtreader.packet.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import me.marnic.modtreader.packet.packets.BasicPacket;
import me.marnic.modtreader.util.ByteBufUtils;

/**
 * Copyright (c) 09.02.2021
 * Developed by MrMarnic
 * GitHub: https://github.com/MrMarnic
 */
public class PacketEncoder extends MessageToByteEncoder<BasicPacket> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, BasicPacket basicPacket, ByteBuf byteBuf) {
        ByteBuf packetData = Unpooled.buffer();
        basicPacket.encode(packetData);

        ByteBufUtils.writeVarInt(packetData.readableBytes(),byteBuf);
        byteBuf.writeBytes(packetData);
    }
}
