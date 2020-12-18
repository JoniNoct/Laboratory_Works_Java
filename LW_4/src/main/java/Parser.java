import java.util.ArrayList;

public class Parser {
    private char inputSeparator;
    private char outputSeparator;
    private String inputCSVPath;
    private String outputFilePath;
    public int current_threads = 0;
    public int current_line = 0;
    public ArrayList<String> content = null;

    public Parser(String inSeparator, String outSeparator, String inCSVPath, String outFilePath) {
        this.inputSeparator = inSeparator.charAt(0);
        this.outputSeparator = outSeparator.charAt(0);
        this.inputCSVPath = inCSVPath;
        this.outputFilePath = outFilePath;
    }

//    public void setInputSeparator(char inputSeparator) {
//        this.inputSeparator = inputSeparator;
//    }
//
//    public void setOutputSeparator(char outputSeparator) {
//        this.outputSeparator = outputSeparator;
//    }
//
//    public char getInputSeparator() {
//        return inputSeparator;
//    }
//
//    public char getOutputSeparator() {
//        return outputSeparator;
//    }

    public ArrayList<String> lightParseStrings(String str) {
        ArrayList<String> result = new ArrayList<>();
        String bufferString = "";
        int startIndex = 0;
        int finishIndex = 0;
        boolean mirror = false;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == inputSeparator){
                result.add(bufferString+str.substring(startIndex,finishIndex));
                bufferString = "";
                finishIndex++;
                startIndex = finishIndex;
                if ((i==str.length()-1)&&(str.charAt(i) == inputSeparator)){
                    result.add("");
                }
            }
            else if ((i==str.length()-1)) {
                result.add(bufferString+str.substring(startIndex,finishIndex+1));
                bufferString = "";
            }
            else if ((str.charAt(i) == '\"') && (i==startIndex)){
                for (int k = startIndex+1; k < str.length(); k++) {
                    if (str.charAt(k) == '\"'){
                        mirror = true;
                        finishIndex=k;
                        break;
                    }
                }
                if (mirror){
                    bufferString += str.substring(startIndex+1,finishIndex);
                    mirror = false;
                    i = finishIndex;
                    finishIndex++;
                    startIndex = finishIndex;
                }
                else {
                    finishIndex++;
                }
            }
            else{
                finishIndex++;
            }
        }

        return result;
    }

    public String blockSizes(ArrayList<String> splitStrings){
        String result = "";
        for (int i = 0; i < splitStrings.size(); i++) {
            if (i!=(splitStrings.size()-1)){
                result += splitStrings.get(i).length();
                result += outputSeparator;
            }
            else
                result += splitStrings.get(i).length();
        }
        return result;
    }

    public void Thread_Parse(int thread_size) throws InterruptedException {
        FileWork file = new FileWork(this.inputCSVPath, this.outputFilePath);
        this.content = file.readByLine();

        while (current_line<content.size()) {
            System.out.println(current_threads);
            if (current_threads<thread_size) {
                LineThread thrd = new LineThread(this, current_line);
                Thread b = new Thread(thrd);
                b.start();
                current_threads++;
                current_line++;
            }
        }
        Thread.sleep(1000);

        file.writeByLine(this.content);
    }
}
