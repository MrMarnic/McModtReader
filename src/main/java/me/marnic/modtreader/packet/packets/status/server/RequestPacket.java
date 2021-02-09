package me.marnic.modtreader.packet.packets.status.server;

import io.netty.buffer.ByteBuf;
import me.marnic.modtreader.packet.packets.BasicPacket;
import me.marnic.modtreader.util.ByteBufUtils;

/**
 * Copyright (c) 09.02.2021
 * Developed by MrMarnic
 * GitHub: https://github.com/MrMarnic
 */
public class RequestPacket extends BasicPacket {
    public RequestPacket() {
        super(0x00);
    }

    @Override
    public void encode(ByteBuf buf) {
        ByteBufUtils.writeVarInt(getId(),buf);
    }
}
