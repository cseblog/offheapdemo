package com.timemachine.disk;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class OffHeapDemo {
    private static final String BTCUSDT = "./BTCUSDT-1m.csv";

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        RandomAccessFile file = new RandomAccessFile(BTCUSDT, "rw");
        FileChannel channel = file.getChannel();

        // Read file into mapped buffer
        MappedByteBuffer mbb =
                channel.map(FileChannel.MapMode.READ_WRITE,
                        0,          // position
                        channel.size());

        System.out.println("Reading content and printing ... ");
        for (int i = 0; i < channel.size(); i++) {
            System.out.print((char) mbb.get());
        }

        channel.close();
        file.close();
        System.out.println("Total read and print time: " + (System.currentTimeMillis() - startTime));
    }
}
