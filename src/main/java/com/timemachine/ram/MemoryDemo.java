package com.timemachine.ram;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MemoryDemo {
    private static final String BTCUSDT_FILE_PATH = "./BTCUSDT-1m.csv";

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        RandomAccessFile file = new RandomAccessFile(BTCUSDT_FILE_PATH, "rw");
        FileChannel channel = file.getChannel();

        //Reading file into normal Memory buffer
        ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
        channel.read(buffer);
        buffer.flip();

        for (int i = 0; i < channel.size(); i++) {
            System.out.print((char) buffer.get());
        }

        channel.close();
        file.close();

        System.out.println("Total read and print time: " + (System.currentTimeMillis() - startTime));
    }
}
