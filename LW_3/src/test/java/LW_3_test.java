import org.junit.Test;

import java.util.function.Function;
import java.util.function.Predicate;
import java.io.*;
import java.util.ArrayList;
import java.lang.Iterable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LW_3_test {
    private ArrayList<Integer> intData = new ArrayList<>();
    private ArrayList<Float> floatData = new ArrayList<>();
    private ArrayList<String> strData = new ArrayList<>();
    private ArrayList<Boolean> boolData = new ArrayList<>();

    public LW_3_test() {
        Integer[] intTemp = {1, 2, 3, 4, 5};
        Float[] floatTemp = {3.14f, 1.23f, 0f, -2f, -53.44f};
        String[] strTemp = {"Lorem", "ipsum", "aaa", "aab", "aac"};
        Boolean[] boolTemp = {true, true, false, true, false};
        Collections.addAll(intData, intTemp);
        Collections.addAll(floatData, floatTemp);
        Collections.addAll(strData, strTemp);
        Collections.addAll(boolData, boolTemp);
    }

    @Test
    public void filterTest_int() {
        Predicate<Integer> condition = num -> num>3;
        Integer[] intTemp = {4, 5};
        ArrayList<Integer> exp = new ArrayList<>();
        Collections.addAll(exp, intTemp);
        assertEquals(LW_3.filter(intData, condition), exp);
    }

    @Test
    public void filterTest_float() {
        Predicate<Float> condition = num -> num>0;
        Float[] floatTemp = {3.14f, 1.23f};
        ArrayList<Float> exp = new ArrayList<>();
        Collections.addAll(exp, floatTemp);
        assertEquals(LW_3.filter(floatData, condition), exp);
    }

    @Test
    public void filterTest_str() {
        Predicate<String> condition = str -> str.length()>3;
        String[] strTemp = {"Lorem", "ipsum"};
        ArrayList<String> exp = new ArrayList<>();
        Collections.addAll(exp, strTemp);
        assertEquals(LW_3.filter(strData, condition), exp);
    }

    @Test
    public void filterTest_bool() {
        Predicate<Boolean> condition = bool -> !bool;
        Boolean[] boolTemp = {false, false};
        ArrayList<Boolean> exp = new ArrayList<>();
        Collections.addAll(exp, boolTemp);
        assertEquals(LW_3.filter(boolData, condition), exp);
    }

    @Test
    public void transformTest_int() {
        Function<Integer, Integer> func = num -> num*num;
        Integer[] intTemp = {1, 4, 9, 16, 25};
        ArrayList<Integer> exp = new ArrayList<>();
        Collections.addAll(exp, intTemp);
        assertEquals(LW_3.transform(intData, func), exp);
    }

    @Test
    public void transformTest_float() {
        Function<Float, Float> func = num -> num*100;
        Float[] floatTemp = {314f, 123f, 0f, -200f, -5344f};
        ArrayList<Float> exp = new ArrayList<>();
        Collections.addAll(exp, floatTemp);
        assertEquals(LW_3.transform(floatData, func), exp);
    }

    @Test
    public void transformTest_str() {
        Function<String, String> func = str -> str+"__test";
        String[] strTemp = {"Lorem__test", "ipsum__test", "aaa__test", "aab__test", "aac__test"};
        ArrayList<String> exp = new ArrayList<>();
        Collections.addAll(exp, strTemp);
        assertEquals(LW_3.transform(strData, func), exp);
    }

    @Test
    public void transformTest_bool() {
        Function<Boolean, Boolean> func = bool -> bool=!bool;
        Boolean[] boolTemp = {false, false, true, false, true};
        ArrayList<Boolean> exp = new ArrayList<>();
        Collections.addAll(exp, boolTemp);
        assertEquals(LW_3.transform(boolData, func), exp);
    }
}