package org.algo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SequenceAlignmentMavenTests {

    public SequenceAlignmentMaven.Pair generateRandomStrings() {
        String base1 = "ACTG";
        String base2 = "TACG";
        String a = SequenceAlignmentMaven.fetchInputStrings(base1, getRandomIndexArray());
        String b = SequenceAlignmentMaven.fetchInputStrings(base2, getRandomIndexArray());
        System.out.println(a + "\t" + b);
        return new SequenceAlignmentMaven.Pair(a, b);
    }

    private List<Integer> getRandomIndexArray() {
        Random random = new Random();
        int count = random.nextInt(10) + 1;

        List<Integer> randomIndexList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            randomIndexList.add(random.nextInt(3) + 1);
        }
        System.out.println(randomIndexList + "\t" + count);
        return randomIndexList;
    }

    @Test
    void sampleTest0() {
        SequenceAlignmentMaven.Pair pair = generateRandomStrings();
        String a = pair.a;
        String b = pair.b;
        SequenceAlignmentMaven.MapCytokynesToIndices();
        SequenceAlignmentMaven.Pair p1 = SequenceAlignmentMaven.DivideAndConquerSequenceAlignment(a, b);
        SequenceAlignmentMaven.Pair p2 = SequenceAlignmentMaven.NeedlemanWunsch(a, b);
        System.out.println(p1 + "\n" + p2);
        Assertions.assertEquals(SequenceAlignmentMaven.calculateScore(p1), SequenceAlignmentMaven.calculateScore(p2));
    }

    @Test
    void sampleTest1() {
        SequenceAlignmentMaven.Pair pair = generateRandomStrings();
        String a = pair.a;
        String b = pair.b;
        SequenceAlignmentMaven.MapCytokynesToIndices();
        SequenceAlignmentMaven.Pair p1 = SequenceAlignmentMaven.DivideAndConquerSequenceAlignment(a, b);
        SequenceAlignmentMaven.Pair p2 = SequenceAlignmentMaven.NeedlemanWunsch(a, b);
        System.out.println(p1 + "\n" + p2);
        Assertions.assertEquals(SequenceAlignmentMaven.calculateScore(p1), SequenceAlignmentMaven.calculateScore(p2));
    }

    @Test
    void sampleTest2() {
        SequenceAlignmentMaven.Pair pair = generateRandomStrings();
        String a = pair.a;
        String b = pair.b;
        SequenceAlignmentMaven.MapCytokynesToIndices();
        SequenceAlignmentMaven.Pair p1 = SequenceAlignmentMaven.DivideAndConquerSequenceAlignment(a, b);
        SequenceAlignmentMaven.Pair p2 = SequenceAlignmentMaven.NeedlemanWunsch(a, b);
        System.out.println(p1 + "\n" + p2);
        Assertions.assertEquals(SequenceAlignmentMaven.calculateScore(p1), SequenceAlignmentMaven.calculateScore(p2));
    }

    @Test
    void sampleTest3() {
        SequenceAlignmentMaven.Pair pair = generateRandomStrings();
        String a = pair.a;
        String b = pair.b;
        SequenceAlignmentMaven.MapCytokynesToIndices();
        SequenceAlignmentMaven.Pair p1 = SequenceAlignmentMaven.DivideAndConquerSequenceAlignment(a, b);
        SequenceAlignmentMaven.Pair p2 = SequenceAlignmentMaven.NeedlemanWunsch(a, b);
        System.out.println(p1 + "\n" + p2);
        Assertions.assertEquals(SequenceAlignmentMaven.calculateScore(p1), SequenceAlignmentMaven.calculateScore(p2));
    }

    @Test
    void sampleTest4() {
        SequenceAlignmentMaven.Pair pair = generateRandomStrings();
        String a = pair.a;
        String b = pair.b;
        SequenceAlignmentMaven.MapCytokynesToIndices();
        SequenceAlignmentMaven.Pair p1 = SequenceAlignmentMaven.DivideAndConquerSequenceAlignment(a, b);
        SequenceAlignmentMaven.Pair p2 = SequenceAlignmentMaven.NeedlemanWunsch(a, b);
        System.out.println(p1 + "\n" + p2);
        Assertions.assertEquals(SequenceAlignmentMaven.calculateScore(p1), SequenceAlignmentMaven.calculateScore(p2));
    }

    @Test
    void sampleTest5() {
        SequenceAlignmentMaven.Pair pair = generateRandomStrings();
        String a = pair.a;
        String b = pair.b;
        SequenceAlignmentMaven.MapCytokynesToIndices();
        SequenceAlignmentMaven.Pair p1 = SequenceAlignmentMaven.DivideAndConquerSequenceAlignment(a, b);
        SequenceAlignmentMaven.Pair p2 = SequenceAlignmentMaven.NeedlemanWunsch(a, b);
        System.out.println(p1 + "\n" + p2);
        Assertions.assertEquals(SequenceAlignmentMaven.calculateScore(p1), SequenceAlignmentMaven.calculateScore(p2));
    }

    @Test
    void sampleTest6() {
        SequenceAlignmentMaven.Pair pair = generateRandomStrings();
        String a = pair.a;
        String b = pair.b;
        SequenceAlignmentMaven.MapCytokynesToIndices();
        SequenceAlignmentMaven.Pair p1 = SequenceAlignmentMaven.DivideAndConquerSequenceAlignment(a, b);
        SequenceAlignmentMaven.Pair p2 = SequenceAlignmentMaven.NeedlemanWunsch(a, b);
        System.out.println(p1 + "\n" + p2);
        Assertions.assertEquals(SequenceAlignmentMaven.calculateScore(p1), SequenceAlignmentMaven.calculateScore(p2));
    }

    @Test
    void sampleTest7() {
        SequenceAlignmentMaven.Pair pair = generateRandomStrings();
        String a = pair.a;
        String b = pair.b;
        SequenceAlignmentMaven.MapCytokynesToIndices();
        SequenceAlignmentMaven.Pair p1 = SequenceAlignmentMaven.DivideAndConquerSequenceAlignment(a, b);
        SequenceAlignmentMaven.Pair p2 = SequenceAlignmentMaven.NeedlemanWunsch(a, b);
        System.out.println(p1 + "\n" + p2);
        Assertions.assertEquals(SequenceAlignmentMaven.calculateScore(p1), SequenceAlignmentMaven.calculateScore(p2));
    }

    @Test
    void sampleTest8() {
        SequenceAlignmentMaven.Pair pair = generateRandomStrings();
        String a = pair.a;
        String b = pair.b;
        SequenceAlignmentMaven.MapCytokynesToIndices();
        SequenceAlignmentMaven.Pair p1 = SequenceAlignmentMaven.DivideAndConquerSequenceAlignment(a, b);
        SequenceAlignmentMaven.Pair p2 = SequenceAlignmentMaven.NeedlemanWunsch(a, b);
        System.out.println(p1 + "\n" + p2);
        Assertions.assertEquals(SequenceAlignmentMaven.calculateScore(p1), SequenceAlignmentMaven.calculateScore(p2));
    }

    @Test
    void sampleTest9() {
        SequenceAlignmentMaven.Pair pair = generateRandomStrings();
        String a = pair.a;
        String b = pair.b;
        SequenceAlignmentMaven.MapCytokynesToIndices();
        SequenceAlignmentMaven.Pair p1 = SequenceAlignmentMaven.DivideAndConquerSequenceAlignment(a, b);
        SequenceAlignmentMaven.Pair p2 = SequenceAlignmentMaven.NeedlemanWunsch(a, b);
        System.out.println(p1 + "\n" + p2);
        Assertions.assertEquals(SequenceAlignmentMaven.calculateScore(p1), SequenceAlignmentMaven.calculateScore(p2));
    }

    @Test
    void sampleTest10() {
        SequenceAlignmentMaven.Pair pair = generateRandomStrings();
        String a = pair.a;
        String b = pair.b;
        SequenceAlignmentMaven.MapCytokynesToIndices();
        SequenceAlignmentMaven.Pair p1 = SequenceAlignmentMaven.DivideAndConquerSequenceAlignment(a, b);
        SequenceAlignmentMaven.Pair p2 = SequenceAlignmentMaven.NeedlemanWunsch(a, b);
        System.out.println(p1 + "\n" + p2);
        Assertions.assertEquals(SequenceAlignmentMaven.calculateScore(p1), SequenceAlignmentMaven.calculateScore(p2));
    }

    @Test
    void sampleTest11() {
        SequenceAlignmentMaven.Pair pair = generateRandomStrings();
        String a = pair.a;
        String b = pair.b;
        SequenceAlignmentMaven.MapCytokynesToIndices();
        SequenceAlignmentMaven.Pair p1 = SequenceAlignmentMaven.DivideAndConquerSequenceAlignment(a, b);
        SequenceAlignmentMaven.Pair p2 = SequenceAlignmentMaven.NeedlemanWunsch(a, b);
        System.out.println(p1 + "\n" + p2);
        Assertions.assertEquals(SequenceAlignmentMaven.calculateScore(p1), SequenceAlignmentMaven.calculateScore(p2));
    }

    @Test
    void sampleTest12() {
        SequenceAlignmentMaven.Pair pair = generateRandomStrings();
        String a = pair.a;
        String b = pair.b;
        SequenceAlignmentMaven.MapCytokynesToIndices();
        SequenceAlignmentMaven.Pair p1 = SequenceAlignmentMaven.DivideAndConquerSequenceAlignment(a, b);
        SequenceAlignmentMaven.Pair p2 = SequenceAlignmentMaven.NeedlemanWunsch(a, b);
        System.out.println(p1 + "\n" + p2);
        Assertions.assertEquals(SequenceAlignmentMaven.calculateScore(p1), SequenceAlignmentMaven.calculateScore(p2));
    }

    @Test
    void sampleTest13() {
        SequenceAlignmentMaven.Pair pair = generateRandomStrings();
        String a = pair.a;
        String b = pair.b;
        SequenceAlignmentMaven.MapCytokynesToIndices();
        SequenceAlignmentMaven.Pair p1 = SequenceAlignmentMaven.DivideAndConquerSequenceAlignment(a, b);
        SequenceAlignmentMaven.Pair p2 = SequenceAlignmentMaven.NeedlemanWunsch(a, b);
        System.out.println(p1 + "\n" + p2);
        Assertions.assertEquals(SequenceAlignmentMaven.calculateScore(p1), SequenceAlignmentMaven.calculateScore(p2));
    }

    @Test
    void sampleTest14() {
        SequenceAlignmentMaven.Pair pair = generateRandomStrings();
        String a = pair.a;
        String b = pair.b;
        SequenceAlignmentMaven.MapCytokynesToIndices();
        SequenceAlignmentMaven.Pair p1 = SequenceAlignmentMaven.DivideAndConquerSequenceAlignment(a, b);
        SequenceAlignmentMaven.Pair p2 = SequenceAlignmentMaven.NeedlemanWunsch(a, b);
        System.out.println(p1 + "\n" + p2);
        Assertions.assertEquals(SequenceAlignmentMaven.calculateScore(p1), SequenceAlignmentMaven.calculateScore(p2));
    }

    @Test
    void sampleTest15() {
        SequenceAlignmentMaven.Pair pair = generateRandomStrings();
        String a = pair.a;
        String b = pair.b;
        SequenceAlignmentMaven.MapCytokynesToIndices();
        SequenceAlignmentMaven.Pair p1 = SequenceAlignmentMaven.DivideAndConquerSequenceAlignment(a, b);
        SequenceAlignmentMaven.Pair p2 = SequenceAlignmentMaven.NeedlemanWunsch(a, b);
        System.out.println(p1 + "\n" + p2);
        Assertions.assertEquals(SequenceAlignmentMaven.calculateScore(p1), SequenceAlignmentMaven.calculateScore(p2));
    }

    @Test
    void sampleTest16() {
        SequenceAlignmentMaven.Pair pair = generateRandomStrings();
        String a = pair.a;
        String b = pair.b;
        SequenceAlignmentMaven.MapCytokynesToIndices();
        SequenceAlignmentMaven.Pair p1 = SequenceAlignmentMaven.DivideAndConquerSequenceAlignment(a, b);
        SequenceAlignmentMaven.Pair p2 = SequenceAlignmentMaven.NeedlemanWunsch(a, b);
        System.out.println(p1 + "\n" + p2);
        Assertions.assertEquals(SequenceAlignmentMaven.calculateScore(p1), SequenceAlignmentMaven.calculateScore(p2));
    }

    @Test
    void sampleTest17() {
        SequenceAlignmentMaven.Pair pair = generateRandomStrings();
        String a = pair.a;
        String b = pair.b;
        SequenceAlignmentMaven.MapCytokynesToIndices();
        SequenceAlignmentMaven.Pair p1 = SequenceAlignmentMaven.DivideAndConquerSequenceAlignment(a, b);
        SequenceAlignmentMaven.Pair p2 = SequenceAlignmentMaven.NeedlemanWunsch(a, b);
        System.out.println(p1 + "\n" + p2);
        Assertions.assertEquals(SequenceAlignmentMaven.calculateScore(p1), SequenceAlignmentMaven.calculateScore(p2));
    }

    @Test
    void sampleTest18() {
        SequenceAlignmentMaven.Pair pair = generateRandomStrings();
        String a = pair.a;
        String b = pair.b;
        SequenceAlignmentMaven.MapCytokynesToIndices();
        SequenceAlignmentMaven.Pair p1 = SequenceAlignmentMaven.DivideAndConquerSequenceAlignment(a, b);
        SequenceAlignmentMaven.Pair p2 = SequenceAlignmentMaven.NeedlemanWunsch(a, b);
        System.out.println(p1 + "\n" + p2);
        Assertions.assertEquals(SequenceAlignmentMaven.calculateScore(p1), SequenceAlignmentMaven.calculateScore(p2));
    }

    @Test
    void sampleTest19() {
        SequenceAlignmentMaven.Pair pair = generateRandomStrings();
        String a = pair.a;
        String b = pair.b;
        SequenceAlignmentMaven.MapCytokynesToIndices();
        SequenceAlignmentMaven.Pair p1 = SequenceAlignmentMaven.DivideAndConquerSequenceAlignment(a, b);
        SequenceAlignmentMaven.Pair p2 = SequenceAlignmentMaven.NeedlemanWunsch(a, b);
        System.out.println(p1 + "\n" + p2);
        Assertions.assertEquals(SequenceAlignmentMaven.calculateScore(p1), SequenceAlignmentMaven.calculateScore(p2));
    }

    @Test
    void sampleTest20() {
        SequenceAlignmentMaven.Pair pair = generateRandomStrings();
        String a = pair.a;
        String b = pair.b;
        SequenceAlignmentMaven.MapCytokynesToIndices();
        SequenceAlignmentMaven.Pair p1 = SequenceAlignmentMaven.DivideAndConquerSequenceAlignment(a, b);
        SequenceAlignmentMaven.Pair p2 = SequenceAlignmentMaven.NeedlemanWunsch(a, b);
        System.out.println(p1 + "\n" + p2);
        Assertions.assertEquals(SequenceAlignmentMaven.calculateScore(p1), SequenceAlignmentMaven.calculateScore(p2));
    }
}
