package me.marnic.modtreader.packet.packets.status.server;

import io.netty.buffer.ByteBuf;
import me.marnic.modtreader.packet.packets.BasicPacket;
import me.marnic.modtreader.util.ByteBufUtils;

/**
 * Copyright (c) 09.02.2021
 * Developed by MrMarnic
 * GitHub: https://github.com/MrMarnic
 */
public class HandshakePacket extends BasicPacket {

    private int protocolVersion;
    private String address;
    private short port;
    private int nextState;

    public HandshakePacket(int protocolVersion, String address, short port, int nextState) {
        super(0x00);
        this.protocolVersion = protocolVersion;
        this.address = address;
        this.port = port;
        this.nextState = nextState;
    }

    @Override
    public void encode(ByteBuf buf) {
        ByteBufUtils.writeVarInt(this.id,buf);
        ByteBufUtils.writeVarInt(this.protocolVersion,buf);
        ByteBufUtils.writeString(this.address,buf);
        buf.writeShort(this.port);
        ByteBufUtils.writeVarInt(this.nextState,buf);
    }
}
