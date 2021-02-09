package me.marnic.modtreader.packet.packets.status.client;

import io.netty.buffer.ByteBuf;
import me.marnic.modtreader.packet.packets.BasicPacket;
import me.marnic.modtreader.util.ByteBufUtils;

/**
 * Copyright (c) 09.02.2021
 * Developed by MrMarnic
 * GitHub: https://github.com/MrMarnic
 */
public class ResponsePacket extends BasicPacket {
    private String jsonResponse;

    public ResponsePacket() {
        this.id = 0x00;
    }

    @Override
    public BasicPacket decode(ByteBuf buf) {
        this.jsonResponse = ByteBufUtils.readString(buf);
        return this;
    }

    public String getJsonResponse() {
        return jsonResponse;
    }
}
