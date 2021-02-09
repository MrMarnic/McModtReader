package me.marnic.modtreader.packet.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import me.marnic.modtreader.util.ByteBufUtils;

import java.util.HashMap;

/**
 * Copyright (c) 09.02.2021
 * Developed by MrMarnic
 * GitHub: https://github.com/MrMarnic
 */
public class PacketRegistry {
    private static final HashMap<Integer,Class<? extends BasicPacket>> PACKETS = new HashMap<>();
    private static final HashMap<Integer,PacketHandler> PACKET_HANDLERS = new HashMap<>();

    public static <T extends BasicPacket> void register(int id,Class<T> packetClass,PacketHandler<T> handler) {
        PACKETS.put(id,packetClass);
        PACKET_HANDLERS.put(id,handler);
    }

    public static BasicPacket decode(ByteBuf buf) throws IllegalAccessException, InstantiationException {
        int id = ByteBufUtils.readVarInt(buf);
        return PACKETS.get(id).newInstance().decode(buf);
    }

    public static <T extends BasicPacket> void handle(T packet, ChannelHandlerContext ctx) {
        if(PACKET_HANDLERS.containsKey(packet.getId())) {
            PACKET_HANDLERS.get(packet.getId()).onPacket(packet,ctx);
        }
    }
}
