import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.Scanner;

/*
 *
 * Author:
 * Jonathan Hoffmann
 * Seminar course Networkapplications
 * SpeedComparison of Java I/O and NIO
 *
 */

public class NIObyteloaderRandom {
    public static long NIOloadrandom(int load[]) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("src/files/RandomGeneratedFiles/Byte.file", "rw");
        FileChannel inChannel = aFile.getChannel();


        long start = new Date().getTime();
        for (int i=0;i<load.length;i++)
        {
            aFile.seek(load[i]);
            System.out.println("NIO loaded byte " + aFile.readByte() + " at index " + load[i] + ".");
        }
        long end = new Date().getTime();
        aFile.close();

        return end-start;
    }

    public static int readInfoFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/files/RandomGeneratedFiles/bytesMB.info"));
        int temp = sc.nextInt();
        sc.close();
        return temp;
    }

    public static void printresult(byte[] a)
    {
        for (int i=0;i<a.length;i++)
        {
            System.out.println(i + "\t" + a[i]);
        }
    }
}
