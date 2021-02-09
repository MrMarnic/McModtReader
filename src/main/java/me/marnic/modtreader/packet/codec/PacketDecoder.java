package me.marnic.modtreader.packet.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import me.marnic.modtreader.packet.packets.BasicPacket;
import me.marnic.modtreader.packet.packets.PacketRegistry;
import me.marnic.modtreader.util.ByteBufUtils;

import java.util.List;

/**
 * Copyright (c) 09.02.2021
 * Developed by MrMarnic
 * GitHub: https://github.com/MrMarnic
 */
public class PacketDecoder extends ByteToMessageDecoder {

    private ByteBuf buffer;
    private int len;
    private int currentSize;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if(len == 0) {
            len = ByteBufUtils.readVarInt(byteBuf);
            if(buffer != null) {
                buffer.clear();
            }
            buffer = Unpooled.buffer();
            buffer.writeBytes(byteBuf);
            currentSize = buffer.readableBytes();
            if(currentSize >= len) {
                BasicPacket packet = PacketRegistry.decode(buffer);
                list.add(packet);
                len = 0;
                currentSize = 0;
            }
        } else if(currentSize < len) {
            currentSize = currentSize + byteBuf.readableBytes();
            buffer.writeBytes(byteBuf);

            if(currentSize >= len) {
                BasicPacket packet = PacketRegistry.decode(buffer);
                list.add(packet);
                len = 0;
                currentSize = 0;
            }
        }
    }
}
