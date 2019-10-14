import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/*
 *
 * Author:
 * Jonathan Hoffmann
 * Seminar course Networkapplications
 * SpeedComparison of Java I/O and NIO
 *
 */

public class HUB {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        CreateRandomByteFile newFile = new CreateRandomByteFile();
        IObyteloaderSEQ iObyteloaderSEQ = new IObyteloaderSEQ();
        IObyteloaderRandomLoaded iObyteloaderRandomLoaded = new IObyteloaderRandomLoaded();
        IObyteloaderRandomNonLoaded iObyteloaderRandomNonLoaded = new IObyteloaderRandomNonLoaded();
        NIObyteloaderSEQ niObyteloaderSEQ = new NIObyteloaderSEQ();
        NIObyteloaderRandom niObyteloaderRandom = new NIObyteloaderRandom();


        //Select mode
        System.err.println("This program might degrade your harddrive or SSD, as it can make a lot of big files. Continue at your hardwares risk.");
        System.out.println("Please type 1 for sequential, 2 for random with loaded array or 3 for random without loaded array.");
        int mode = sc.nextInt();
        System.out.println("Which Filesize would you like to test?");
        int filesize = sc.nextInt();
        System.out.println("How many times would you like to run the simulation?");
        int iterations = sc.nextInt();
        int randomaccesses = 0;
        if (mode==2||mode==3)
        {
            System.out.println("How many random accesses do you want to perform per file?");
            randomaccesses=sc.nextInt();
        }

        //make Infofilename
        String filename="src/files/results/results_" + filesize +  "MB_" + iterations + "_Iterations";
        switch (mode) {
            case 1: filename+=".SEQ";
            break;
            case 2: filename+="_" + randomaccesses + "Random_Accesses.RAloaded";
            break;
            case 3: filename+="_" + randomaccesses + "Random_Accesses.RAunloaded";
            break;
        }
        PrintWriter pw = new PrintWriter(new File(filename));
        pw.println("Iteration\tI/O\t\tNIO");


        //Make random access indexes
        int[] randomaccessindexes = new int[randomaccesses];
        for (int i=0;i<randomaccesses;i++)
        {
            randomaccessindexes[i]=getRandomNumberInRange(0,filesize*1024*1024);
        }



        long[]IOs = new long[iterations];
        long[]NIOs = new long[iterations];
        long io=0;
        long nio=0;
        for (int i=1; i<=iterations;i++)
        {
            newFile.createFile(filesize);
            if (mode == 1) {
                io = iObyteloaderSEQ.IOload();
                nio = niObyteloaderSEQ.NIOload();
            }
            else if (mode ==2)
            {
                io=iObyteloaderRandomLoaded.IOloadrandomloaded(randomaccessindexes);
                nio=niObyteloaderRandom.NIOloadrandom(randomaccessindexes);
            }
            else if (mode ==3)
            {
                io=iObyteloaderRandomNonLoaded.IOloadrandomnonloaded(randomaccessindexes);
                nio=niObyteloaderRandom.NIOloadrandom(randomaccessindexes);
            }

            //filestuff
            pw.println(i + "\t\t\t" + io + "\t\t" + nio);
            IOs[i-1]=io;
            NIOs[i-1]=nio;
            System.out.println("Completed Iteration " + i + " out of " + iterations + ".");
        }

        //filestuff
        long IOmax=0;
        long IOmin=999999999;
        long NIOmax=0;
        long NIOmin=999999999;
        long IOavg=0;
        long NIOavg=0;
        for(int i =0;i<iterations;i++)
        {
            //minmax
            if(IOs[i]<IOmin)
                IOmin=IOs[i];
            if(IOs[i]>IOmax)
                IOmax=IOs[i];
            if(NIOs[i]<NIOmin)
                NIOmin=NIOs[i];
            if(NIOs[i]>NIOmax)
                NIOmax=NIOs[i];
            //average
            IOavg=IOavg+IOs[i];
            NIOavg+=NIOs[i];
        }
        IOavg=IOavg/iterations;
        NIOavg=NIOavg/iterations;
        pw.println("\nMax\t\t\t" + IOmax + "\t\t" + NIOmax);
        pw.println("Min\t\t\t" + IOmin + "\t\t" + NIOmin);
        pw.println("Average\t\t" + IOavg + "\t\t" + NIOavg);
        pw.println("-Deviation\t-" + (IOavg-IOmin) + "\t\t-" + (NIOavg-NIOmin));
        pw.println("+Deviation\t+" + (IOmax-IOavg) + "\t\t+" + (NIOmax-NIOavg));


        pw.close();
        System.out.println("Completed " + iterations + " iterations.");
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
