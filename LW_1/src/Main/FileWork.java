package Main;
import java.io.*;
import java.util.ArrayList;

public class FileWork {
    private String inputPath;
    private String outputPath;

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getInputPath() {
        return inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public ArrayList readByLine() {
        ArrayList<String> result = new ArrayList<>();
        String buffer = "";

        try(BufferedReader reader = new BufferedReader (new FileReader(inputPath))){
            String temp;
            while((temp=reader.readLine())!=null){
                result.add(temp);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        return result;
    }

    public void writeByLine(ArrayList Strings) {
        try(FileWriter writer = new FileWriter(outputPath)){
            for (int i = 0; i < Strings.size(); i++) {
                writer.write(Strings.get(i).toString()+"\r");
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
