import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class LW_4_Test {
    @Rule
    public TemporaryFolder initFolder = new TemporaryFolder();

    @Rule
    public TemporaryFolder resultFolder = new TemporaryFolder();

    @Test
    public void testThread_Parse() throws IOException, InterruptedException {
        // init
        File initFile = initFolder.newFile("test.txt");
        File resultFile = resultFolder.newFile("result.txt");
        String inputSeparator = ";", inputCSVPath = initFile.getAbsolutePath(), outputSeparator = "-", outputFilePath = resultFile.getAbsolutePath();
        int thread_size = 32;
        File[] folder = initFolder.getRoot().listFiles();
        // write test .txt file
        FileWork testWork = new FileWork(outputFilePath, inputCSVPath);
        ArrayList<String> initStr = new ArrayList<>();
        initStr.add("abc; 1 2\" 3 \";def");
        initStr.add("qwerty;\"test\";Fur");
        initStr.add("ious;1\"2\"3");
        testWork.writeByLine(initStr);
        // test light parser
        for(File file: folder){
            Parser stringParser = new Parser(inputSeparator, outputSeparator, file.getAbsolutePath(), outputFilePath);
            stringParser.Thread_Parse(thread_size);
        }
        testWork.setInputPath(outputFilePath);
        ArrayList<String> resultStr = testWork.readByLine();
        assertEquals("3-9-3",resultStr.get(0));
        assertEquals("6-4-3",resultStr.get(1));
    }

    @Test
    public void testLPSandBS() throws IOException, InterruptedException {
        // init
        File initFile = initFolder.newFile("test.txt");
        File resultFile = resultFolder.newFile("result.txt");
        String inputSeparator = ";", inputCSVPath = initFile.getAbsolutePath(), outputSeparator = "+", outputFilePath = resultFile.getAbsolutePath();
        int thread_size = 8;
        File[] folder = initFolder.getRoot().listFiles();
        // write test .txt file
        FileWork testWork = new FileWork(inputCSVPath, outputFilePath);
        ArrayList<String> initStr = new ArrayList<>();
        initStr.add("abc; 1 2\" 3 \";def");
        initStr.add("qwerty;\"test\";Fur");
        initStr.add("ious;1\"2\"3");
        testWork.writeByLine(initStr);
        // test light parser
        testWork.setInputPath(outputFilePath);
        ArrayList<String> resultStr = testWork.readByLine();
        Parser stringParser = new Parser(inputSeparator, outputSeparator, inputCSVPath, outputFilePath);
        ArrayList<String> AL_Str = new ArrayList<>();
        AL_Str.add("abc");
        AL_Str.add(" 1 2\" 3 \"");
        AL_Str.add("def");
        assertEquals(AL_Str, stringParser.lightParseStrings(resultStr.get(0)));
        assertEquals("4+5",stringParser.blockSizes(stringParser.lightParseStrings(resultStr.get(2))));
    }
}
