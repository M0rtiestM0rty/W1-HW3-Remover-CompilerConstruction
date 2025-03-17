/*
Create a Java program that converts the file contents of a syntactically correct Java source code class into a
compilable stream of characters containing no comments.  The program shall read the original Java source code
file from pre-written java file and save your ‘cleansed’ Java file to another Java file.  The original and the
modified Java source code files should both be able to produce the same byte code file.
As a stretch goal, prepare a working Pascal source file with comments, submit to your newly created Java
program, remove the comments, save the ‘cleansed’ Pascal file to a different file. Make sure the cleansed
one continues to work.
*/

import java.io.*;
import java.util.regex.*;

public class CommentRemover
{

    private static String readFile(String filePath) throws IOException
    {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                content.append(line).append("\n");
            }// end while loo[
        }// end try
        return content.toString();
    }

    private static String removeComments(String code)
    {
        String singleLineComment = "//.*";
        String multiLineComment = "/\\*.*?\\*/";
        Pattern pattern = Pattern.compile(singleLineComment + "|" + multiLineComment, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(code);
        return matcher.replaceAll("");
    }// end private string method

    private static void writeFile(String filePath, String content) throws IOException
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath)))
        {
            bw.write(content);
        }// end try
    }// end private writeFile
    public static void main(String[] args)
    {
        if (args.length < 2)
        {
            System.out.println("Usage: java CommentRemover <input file> <output file>");
            return;
        }// end if

        String inputFile = args[0];
        String outputFile = args[1];

        try
        {
            String content = readFile(inputFile);
            String cleansedContent = removeComments(content);
            writeFile(outputFile, cleansedContent);
            System.out.println("File processed successfully: " + outputFile);
        }// end try
        catch (IOException e)
        {
            System.out.println("Error processing file: " + e.getMessage());
        }// end catch
    }// end main

}// end class
