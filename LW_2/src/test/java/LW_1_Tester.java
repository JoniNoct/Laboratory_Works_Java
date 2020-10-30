import Main.FileWork;
import Main.Parser;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import java.io.*;
import java.util.ArrayList;
import java.lang.Iterable;

import static org.junit.Assert.assertEquals;

public class LW_1_Tester {
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void testLightParseStrings() throws IOException {
        // init
        File initialFile = tempFolder.newFile("init.csv");
        File resultFile = tempFolder.newFile("result.txt");
        FileWork testFile = new FileWork();
        testFile.setInputPath(initialFile.getAbsolutePath());
        testFile.setOutputPath(resultFile.getAbsolutePath());
        Parser testParser = new Parser();
        testParser.setOutputSeparator('+');
        testParser.setInputSeparator(';');
        assertEquals(initialFile.getAbsolutePath(),testFile.getInputPath());
        assertEquals(resultFile.getAbsolutePath(),testFile.getOutputPath());
        assertEquals('+',testParser.getOutputSeparator());
        assertEquals(';',testParser.getInputSeparator());
        // write test .csv file
        ArrayList<String> initStr = new ArrayList<>();
        initStr.add("abc; 1 2\" 3 \";def");
        initStr.add("qwerty;\"test\";Fur");
        initStr.add("ious;1\"2\"3");
        testFile.writeByLine(initStr, true);
        // read test .csv file
        ArrayList<String> resultStr = new ArrayList<>();
        resultStr = testFile.readByLine();
        // test light parser
        assertEquals(" 1 2\" 3 \"",testParser.lightParseStrings(resultStr).get(0).get(1));
        assertEquals("3+9+3",testParser.blockSizes(testParser.lightParseStrings(resultStr)).get(0));
        testFile.writeByLine(testParser.blockSizes(testParser.lightParseStrings(resultStr)), false);
    }

    @Test
    public void testHardParseStrings() throws IOException {
        // init
        File initialFile = tempFolder.newFile("init.csv");
        File resultFile = tempFolder.newFile("result.txt");
        FileWork testFile = new FileWork();
        testFile.setInputPath(initialFile.getAbsolutePath());
        testFile.setOutputPath(resultFile.getAbsolutePath());
        Parser testParser = new Parser();
        testParser.setOutputSeparator('+');
        testParser.setInputSeparator(';');
        assertEquals(initialFile.getAbsolutePath(),testFile.getInputPath());
        assertEquals(resultFile.getAbsolutePath(),testFile.getOutputPath());
        assertEquals('+',testParser.getOutputSeparator());
        assertEquals(';',testParser.getInputSeparator());
        // write test .csv file
        ArrayList<String> initStr = new ArrayList<>();
        initStr.add("abc; 1 2\" 3 \";def");
        initStr.add("qwerty;\"test\";Fur");
        initStr.add("ious;1\"2\"3");
        testFile.writeByLine(initStr, true);
        // read test .csv file
        ArrayList<String> resultStr = new ArrayList<>();
        resultStr = testFile.readByLine();
        // test hard parser
        assertEquals(" 1 2 3 ",testParser.hardParseStrings(resultStr).get(0).get(1));
        assertEquals("3+7+3",testParser.blockSizes(testParser.hardParseStrings(resultStr)).get(0));
        testFile.writeByLine(testParser.blockSizes(testParser.hardParseStrings(resultStr)), false);
    }
}