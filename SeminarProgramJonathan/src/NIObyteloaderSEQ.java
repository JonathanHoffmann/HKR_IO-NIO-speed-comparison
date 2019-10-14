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

public class NIObyteloaderSEQ {
    public static long NIOload() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("src/files/RandomGeneratedFiles/Byte.file", "rw");
        FileChannel inChannel = aFile.getChannel();
        int buffersize = 48;
        int filesize = readInfoFile();
        byte[] array = new byte[filesize*1024*1024];

        //create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(buffersize);

        int counter=0;
        int bytesRead = inChannel.read(buf); //read into buffer.
        long start = new Date().getTime();
        while (bytesRead != -1) {
            buf.flip();  //make buffer ready for read
            while(buf.hasRemaining()){
                array[counter] = buf.get();
                counter++;
                //System.out.print((char) buf.get()); // read 1 byte at a time
            }

            buf.clear(); //make buffer ready for writing
            bytesRead = inChannel.read(buf);
        }
        long end = new Date().getTime();
        aFile.close();

        //Output and result
        /*
        printresult(array);
        System.out.println("Start Time:\t" + start);
        System.out.println("End Time:\t" + end);
        System.out.println("Difference:\t" + (end-start));
        */
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
