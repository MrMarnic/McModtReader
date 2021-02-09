package me.marnic.modtreader.packet.packets;

import io.netty.channel.ChannelHandlerContext;

/**
 * Copyright (c) 09.02.2021
 * Developed by MrMarnic
 * GitHub: https://github.com/MrMarnic
 */
public class PacketHandler<T extends BasicPacket> {
    public void onPacket(T packet, ChannelHandlerContext ctx) {

    }
}
