package me.marnic.modtreader.packet.packets.status.server;

import io.netty.buffer.ByteBuf;
import me.marnic.modtreader.packet.packets.BasicPacket;
import me.marnic.modtreader.util.ByteBufUtils;

/**
 * Copyright (c) 09.02.2021
 * Developed by MrMarnic
 * GitHub: https://github.com/MrMarnic
 */
public class PingPacket extends BasicPacket {
    private long time;

    public PingPacket(long time) {
        super(0x01);
        this.time = time;
    }

    @Override
    public void encode(ByteBuf buf) {
        ByteBufUtils.writeVarInt(getId(),buf);
        buf.writeLong(this.time);
    }
}
