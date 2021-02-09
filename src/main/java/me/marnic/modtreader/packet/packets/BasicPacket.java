package me.marnic.modtreader.packet.packets;

import io.netty.buffer.ByteBuf;

/**
 * Copyright (c) 09.02.2021
 * Developed by MrMarnic
 * GitHub: https://github.com/MrMarnic
 */
public class BasicPacket {
    protected int id;

    public BasicPacket(int id) {
        this.id = id;
    }

    public BasicPacket() {
    }

    public BasicPacket decode(ByteBuf buf) {
        return this;
    }

    public void encode(ByteBuf buf) {

    }

    public int getId() {
        return id;
    }
}
