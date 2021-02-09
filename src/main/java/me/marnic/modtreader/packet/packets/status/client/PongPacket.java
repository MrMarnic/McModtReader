package me.marnic.modtreader.packet.packets.status.client;

import io.netty.buffer.ByteBuf;
import me.marnic.modtreader.packet.packets.BasicPacket;

/**
 * Copyright (c) 09.02.2021
 * Developed by MrMarnic
 * GitHub: https://github.com/MrMarnic
 */
public class PongPacket extends BasicPacket {
    private long time;

    public PongPacket() {
        super(0x01);
    }

    @Override
    public BasicPacket decode(ByteBuf buf) {
        this.time = buf.readLong();
        return this;
    }

    public long getTime() {
        return time;
    }
}
