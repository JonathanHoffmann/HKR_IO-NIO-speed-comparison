import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 *
 * Author:
 * Jonathan Hoffmann
 * Seminar course Networkapplications
 * SpeedComparison of Java I/O and NIO
 *
 */

public class IObyteloaderRandomNonLoaded {
    public static long IOloadrandomnonloaded(int[] load) throws IOException {
        //Reading the bytes in the file
        long start = new Date().getTime();
        for (int i = 0; i < load.length; i++) {
            FileInputStream fis = new FileInputStream(new File("src/files/RandomGeneratedFiles/Byte.file"));


            for (int j = 0; j < load[i]; j++) {
                fis.read();
            }
            System.out.println("I/O loaded byte " + fis.read() + " at index " + load[i] + ".");
            fis.close();
        }
        long end = new Date().getTime();

        //Output and result
        /*
        printresult(readbytes);
        System.out.println("Start Time:\t" + start);
        System.out.println("End Time:\t" + end);
        System.out.println("Difference:\t" + (end-start));
        */
        return end - start;
    }

    public static void printresult(byte[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(i + "\t" + a[i]);
        }
    }

    public static int readInfoFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/files/RandomGeneratedFiles/bytesMB.info"));
        int temp = sc.nextInt();
        sc.close();
        return temp;
    }
}
