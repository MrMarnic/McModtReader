package me.marnic.modtreader.util;

import io.netty.buffer.ByteBuf;

/**
 * Copyright (c) 21.04.2020
 * Developed by MrMarnic
 * GitHub: https://github.com/MrMarnic
 */
public class ByteBufUtils {
    public static int readVarInt(ByteBuf buf) {
        int numRead = 0;
        int result = 0;
        byte read;
        do {
            read = buf.readByte();
            int value = (read & 0b01111111);
            result |= (value << (7 * numRead));

            numRead++;
            if (numRead > 5) {
                throw new RuntimeException("VarInt is too big");
            }
        } while ((read & 0b10000000) != 0);

        return result;
    }
    public static long readVarLong(ByteBuf buf) {
        int numRead = 0;
        long result = 0;
        byte read;
        do {
            read = buf.readByte();
            int value = (read & 0b01111111);
            result |= (value << (7 * numRead));

            numRead++;
            if (numRead > 10) {
                throw new RuntimeException("VarLong is too big");
            }
        } while ((read & 0b10000000) != 0);

        return result;
    }
    public static void writeVarInt(int value, ByteBuf buf) {
        do {
            byte temp = (byte)(value & 0b01111111);
            // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
            value >>>= 7;
            if (value != 0) {
                temp |= 0b10000000;
            }
            buf.writeByte(temp);
        } while (value != 0);
    }
    public static void writeVarLong(long value, ByteBuf buf) {
        do {
            byte temp = (byte)(value & 0b01111111);
            // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
            value >>>= 7;
            if (value != 0) {
                temp |= 0b10000000;
            }
            buf.writeByte(temp);
        } while (value != 0);
    }

    public static void writeString(String text, ByteBuf buf) {
        writeVarInt(text.length(),buf);
        buf.writeBytes(text.getBytes());
    }

    public static String readString(ByteBuf buf) {
        int len = readVarInt(buf);
        byte[] data = new byte[len];
        buf.readBytes(data);
        return new String(data);
    }
}
