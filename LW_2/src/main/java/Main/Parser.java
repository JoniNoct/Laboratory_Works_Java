package Main;

import java.util.ArrayList;

public class Parser {
    private char inputSeparator;
    private char outputSeparator;

    public void setInputSeparator(char inputSeparator) {
        this.inputSeparator = inputSeparator;
    }

    public void setOutputSeparator(char outputSeparator) {
        this.outputSeparator = outputSeparator;
    }

    public char getInputSeparator() {
        return inputSeparator;
    }

    public char getOutputSeparator() {
        return outputSeparator;
    }

    public ArrayList<ArrayList<String>> lightParseStrings(ArrayList<String> str) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> bufferList = new ArrayList<>();
        String bufferString = "";
        int startIndex = 0;
        int finishIndex = 0;
        boolean mirror = false;

        for (int i = 0; i < str.size(); i++) {
            for (int j = 0; j < str.get(i).length(); j++) {
                if (str.get(i).charAt(j) == inputSeparator){
                    bufferList.add(bufferString+str.get(i).substring(startIndex,finishIndex));
                    bufferString = "";
                    finishIndex++;
                    startIndex = finishIndex;
                    if ((j==str.get(i).length()-1)&&(str.get(i).charAt(j) == inputSeparator)){
                        bufferList.add("");
                    }
                }
                else if ((j==str.get(i).length()-1)){
                    bufferList.add(bufferString+str.get(i).substring(startIndex,finishIndex+1));
                    bufferString = "";
                }
                else if ((str.get(i).charAt(j) == '\"') && (j==startIndex)){
                    for (int k = startIndex+1; k < str.get(i).length(); k++) {
                        if (str.get(i).charAt(k) == '\"'){
                            mirror = true;
                            finishIndex=k;
                            break;
                        }
                    }
                    if (mirror){
                        bufferString += str.get(i).substring(startIndex+1,finishIndex);
                        mirror = false;
                        j = finishIndex;
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
            startIndex = 0;
            finishIndex = 0;
            result.add((ArrayList<String>) bufferList.clone());
            bufferList.clear();
        }

        return result;
    }

    public ArrayList<ArrayList<String>> hardParseStrings(ArrayList<String> str) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> bufferList = new ArrayList<>();
        String bufferString = "";
        int startIndex = 0;
        int finishIndex = 0;
        boolean mirror = false;

        for (int i = 0; i < str.size(); i++) {
            for (int j = 0; j < str.get(i).length(); j++) {
                if (str.get(i).charAt(j) == inputSeparator){
                    bufferList.add(bufferString+str.get(i).substring(startIndex,finishIndex));
                    bufferString = "";
                    finishIndex++;
                    startIndex = finishIndex;
                    if ((j==str.get(i).length()-1)&&(str.get(i).charAt(j) == inputSeparator)){
                        bufferList.add("");
                    }
                }
                else if ((j==str.get(i).length()-1)){
                    bufferList.add(bufferString+str.get(i).substring(startIndex,finishIndex+1));
                    bufferString = "";
                }
                else if (str.get(i).charAt(j) == '\"'){
                    bufferString += str.get(i).substring(startIndex,finishIndex);
                    startIndex = finishIndex+1;
                    for (int k = startIndex; k < str.get(i).length(); k++) {
                        if (str.get(i).charAt(k) == '\"'){
                            mirror = true;
                            finishIndex = k;
                            break;
                        }
                    }
                    if (mirror){
                        bufferString += str.get(i).substring(startIndex,finishIndex);
                        mirror = false;
                        j = finishIndex;
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
            startIndex = 0;
            finishIndex = 0;
            result.add((ArrayList<String>) bufferList.clone());
            bufferList.clear();
        }

        return result;
    }

    public ArrayList<String> blockSizes(ArrayList<ArrayList<String>> splitStrings){
        ArrayList<String> result = new ArrayList<>();
        String bufferString = "";
        for (int i = 0; i < splitStrings.size(); i++) {
            for (int j = 0; j < splitStrings.get(i).size(); j++) {
                if (j!=(splitStrings.get(i).size()-1)){
                    bufferString += splitStrings.get(i).get(j).length();
                    bufferString += outputSeparator;
                }
                else {
                    bufferString += splitStrings.get(i).get(j).length();
                }
            }
            result.add(bufferString);
            bufferString = "";
        }

        return result;
    }
}
