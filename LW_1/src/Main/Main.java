package Main;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parser stringParser = new Parser();
        FileWork userFile = new FileWork();
        ArrayList<String> Strings = new ArrayList<>();
        ArrayList<String> blockSizes = new ArrayList<>();
        ArrayList<ArrayList<String>> splitStrings = new ArrayList<ArrayList<String>>();
        String buff_string = "";
        String inputSeparator;
        String outputSeparator;
        String inputCSVPath;
        String outputFilePath;
        do {
            System.out.print("Enter input separator: ");
            inputSeparator = scanner.nextLine();
            if (inputSeparator.length()!=1){
                System.out.println("Enter no more than one character!");
            }
        }while (inputSeparator.length()!=1);
        System.out.print("Enter .csv file path: ");
        inputCSVPath = scanner.nextLine();
        File test = new File(inputCSVPath);
        if (!test.isFile()) {
            System.out.println("No such file exists! Exiting...");
            System.exit(0);
        }
        do {
            System.out.print("Enter output separator: ");
            outputSeparator = scanner.nextLine();
            if (outputSeparator.length()!=1){
                System.out.println("Enter no more than one character!");
            }
        }while (outputSeparator.length()!=1);
        System.out.print("Enter output file path: ");
        outputFilePath = scanner.nextLine();



        userFile.setInputPath(inputCSVPath);
        userFile.setOutputPath(outputFilePath);
        stringParser.setInputSeparator(inputSeparator.charAt(0));
        stringParser.setOutputSeparator(outputSeparator.charAt(0));
        Strings = userFile.readByLine();
        System.out.println("User file:");
        for (int i=0; i<Strings.size();i++){
            System.out.println(Strings.get(i));
        }
        splitStrings = stringParser.lightParseStrings(Strings);
        blockSizes = stringParser.blockSizes(splitStrings);
        userFile.writeByLine(blockSizes);
        System.out.println("Recording completed successfully");
    }
}
