package main;



import java.io.*;

public class SimpleReader {//poderia implementar AutoClosable para usar dentro de um try, e os recursos liberados automaticamente
    private BufferedReader bufferedReader;

    public SimpleReader(String filename) {
        try {
            FileReader fileReader = new FileReader(filename);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + filename);
        }
    }

    public String readLine() {
        try {
            if (bufferedReader != null) {
                return bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading file");
        }
        return null;
    }

    public void close() {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch(IOException e) {
            System.err.println("Error closing file");
        }
        bufferedReader = null;
    }
}
