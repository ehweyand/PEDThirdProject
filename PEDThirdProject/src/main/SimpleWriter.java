package main;



import java.io.*;

public class SimpleWriter
{
    private BufferedWriter bufferedWriter;

    public SimpleWriter(String filename)
    {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            System.err.println("Output file cannot be written to: " + filename);
        }
    }

    public void write(String line)
    {
        try {
            if (bufferedWriter != null) {
                bufferedWriter.write(line + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing file");
        }
    }

    public void close()
    {
        try {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch(IOException e) {
            System.err.println("Error closing file");
        }
        bufferedWriter = null;
    }
}
