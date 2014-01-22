package client;

import java.io.BufferedReader;

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
            return file;
        } catch (Exception e) {
            System.out.println("file raeding error");
        }
        return null;

    }
}
