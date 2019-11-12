package Printers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FilePrinter implements Printer {
    @Override
    public void print (String message) {
        try {
            PrintWriter printWriter = new PrintWriter("Twój wydtruk.txt");
                printWriter.println(message);
                printWriter.close();


        } catch (FileNotFoundException e) {
            System.out.println("Nie udało się utworzyć pliku!");
            e.printStackTrace();
        }

    }
}
