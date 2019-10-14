import java.io.*;
import java.util.Random;

/*
 *
 * Author:
 * Jonathan Hoffmann
 * Seminar course Networkapplications
 * SpeedComparison of Java I/O and NIO
 *
 */

public class CreateRandomByteFile {

    public static void createFile (int filesize) throws IOException {




        Random random = new Random();
        byte[] array = new byte[filesize*1024*1024];
        random.nextBytes(array);
        FileOutputStream out = new FileOutputStream(new File("src/files/RandomGeneratedFiles/Byte.file"));

        out.write(array);
        out.close();
        createInfoFile(filesize);

        //printresult(array);

        //System.out.println("Done");
    }

    public static void printresult(byte[] a)
    {
        for (int i=0;i<a.length;i++)
        {
            System.out.println(i + "\t" + a[i]);
        }
    }
    public static void createInfoFile (int a) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File("src/files/RandomGeneratedFiles/bytesMB.info"));
        pw.println(a);
        pw.close();
    }
}
