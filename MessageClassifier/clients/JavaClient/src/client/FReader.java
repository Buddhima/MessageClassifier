package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Shakya
 */
public class FReader {

    public String readLine(BufferedReader bufferedReader) throws Exception {

        String line;
        int c;
        line = bufferedReader.readLine();
        c = bufferedReader.read();
        return line;
    }

    public String readFile(BufferedReader bufferedReader) {
        try {
            String file;
            String line;
            line = bufferedReader.readLine();
            file = line + "\n";
            while (line != null) {
                line = bufferedReader.readLine();
                if (line != null) {
                    file = file + line + "\n";
                }
            }
//            System.out.println(file);
//            System.out.println("Total length of the file = " + file.length());
            return file;
        } catch (Exception e) {
            System.out.println("file raeding error");
        }
        return null;

    }
}
