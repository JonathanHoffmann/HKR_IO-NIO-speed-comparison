import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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

public class IObyteloaderSEQ {
    public static long IOload() throws IOException {
        //Setup
        FileInputStream fis = new FileInputStream(new File("src/files/RandomGeneratedFiles/Byte.file"));
        byte[] readbytes = null;
        ArrayList<Byte> a = new ArrayList<>();

        //Reading the bytes in the file
        long start = new Date().getTime();
        readbytes = fis.readAllBytes();
        long end = new Date().getTime();
        fis.close();

        //Output and result
        /*
        printresult(readbytes);
        System.out.println("Start Time:\t" + start);
        System.out.println("End Time:\t" + end);
        System.out.println("Difference:\t" + (end-start));
        */
        return end-start;
    }

    public static void printresult(byte[] a)
    {
        for (int i=0;i<a.length;i++)
        {
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
