package org.algo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class SequenceAlignmentMaven {
    public static final int[][] MISMATCH_COST =
            {
                    {0, 110, 48, 94},
                    {110, 0, 118, 48},
                    {48, 118, 0, 110},
                    {94, 48, 110, 0}
            };
    public static final int GAP_PENALTY = 30;

    public static Map<Character, Integer> hm = new HashMap<>();

    public static String BASE_PATH;
    private final static Logger LOGGER = Logger.getLogger(SequenceAlignmentMaven.class.getName());

    public static boolean isCustomEnabled;
    public static boolean isSpaceOptimizationEnabled;
    public static boolean isPrinting2DMatrixEnabled;
    public static boolean isDivideAndConquerEnabled;
    public static boolean isLoggingEnabled;

    public static int NWScore = 0;
    public static StringBuilder outputData = new StringBuilder();

    static class Pair {
        String a;
        String b;

        Pair(String a, String b) {
            this.a = a;
            this.b = b;
        }

        public Pair add(Pair pair) {
            return new Pair(this.a + pair.a, this.b + pair.b);
        }

        @Override
        public String toString() {
            String[] aPart = (a.length() > 50)
                    ? new String[]{a.substring(0, 50), a.substring(a.length() - 50)}
                    : new String[]{a};

            String[] bPart = (b.length() > 50)
                    ? new String[]{b.substring(0, 50), b.substring(b.length() - 50)}
                    : new String[]{b};

            if (aPart.length == 2 && bPart.length == 2) {
                String ans = String.format("%s %s\n%s %s", aPart[0], aPart[1], bPart[0], bPart[1]);
                outputData.append(ans).append("\n");
                return ans;
            } else {
                String ans = String.format("%s\n%s", aPart[0], bPart[0]);
                outputData.append(ans).append("\n");
                return ans;
            }
        }
    }

    static class Input {
        String firstString;
        String secondString;
        List<Integer> indexes1;
        List<Integer> indexes2;

        public Input(String firstString, String secondString, List<Integer> indexes1, List<Integer> indexes2) {
            this.firstString = firstString;
            this.secondString = secondString;
            this.indexes1 = indexes1;
            this.indexes2 = indexes2;
        }
    }

    public static void InitializeLogger() {
        if (isLoggingEnabled)
            LOGGER.setLevel(Level.INFO);
        else
            LOGGER.setLevel(Level.OFF);
    }

    public static void main(String[] args) {
        long memBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        List<String> argsList = Arrays.asList(args);
        System.out.println(argsList);
        SetFlags(argsList);
        InitializeLogger();
        MapCytokynesToIndices();
        Execute(argsList);
        long memAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        // System.out.printf("Before Memory: [%d]\tAfter Memory: [%d]%n", memBefore, memAfter);
        // System.out.printf("Memory required for code execution: %d KB%n", (memAfter - memBefore) / 1024);
        double memoryRequired = (memAfter - memBefore) / 1_000.0;
        System.out.printf("%f%n", memoryRequired);
        outputData.append(memoryRequired).append("\n");
        writeOutputToFile("output.txt");
    }

    public static void Execute(List<String> argsList) {
        Pair inputStrings;
        if (isCustomEnabled) {
            LOGGER.log(Level.INFO, "Custom strings provided, skipping input creation from file");
            String a = argsList.get(argsList.indexOf("-firstString") + 1);
            String b = argsList.get(argsList.indexOf("-secondString") + 1);
            inputStrings = new Pair(a, b);
        } else {
            BASE_PATH = argsList.get(argsList.indexOf("-basePath") + 1);
            final String FILENAME = argsList.get(argsList.indexOf("-filename") + 1);
            LOGGER.log(Level.INFO, String.format("Generating input strings from file [%s] present at location [%s]", FILENAME, BASE_PATH));
            inputStrings = GenerateInputStringsFromFiles(FILENAME);
        }

        Pair alignment;
        Instant start;
        Instant end;
        if (isSpaceOptimizationEnabled && isDivideAndConquerEnabled) {
            System.out.println("Executing Divide & Conquer + Dynamic Programming Algorithm");
            start = Instant.now();
            alignment = DivideAndConquerSequenceAlignment(inputStrings.a, inputStrings.b);
            end = Instant.now();
        } else {
            System.out.println("Executing Dynamic Programming (Needleman Wunsch) Algorithm");
            start = Instant.now();
            alignment = NeedlemanWunsch(inputStrings.a, inputStrings.b);
            end = Instant.now();
        }
        System.out.println(alignment);
        double timeTaken = Duration.between(start, end).toNanosPart() / 1_000_000_000.0;
        System.out.printf("%d%n", NWScore);
        System.out.printf("%f%n", timeTaken);
        outputData.append(NWScore).append("\n").append(timeTaken).append("\n");
    }

    public static void SetFlags(List<String> argsList) {
        if (argsList.size() == 0) {
            LOGGER.log(Level.SEVERE, "No arguments passed. Terminating code...");
            System.exit(1);
        }
        isCustomEnabled = argsList.get(argsList.indexOf("-isCustomEnabled") + 1).equalsIgnoreCase("true");
        LOGGER.log(Level.INFO, "isCustomEnabled is " + isCustomEnabled);

        isSpaceOptimizationEnabled = argsList.get(argsList.indexOf("-isSpaceOptimizationEnabled") + 1).equalsIgnoreCase("true");
        LOGGER.log(Level.INFO, "isSpaceOptimizationEnabled is " + isSpaceOptimizationEnabled);

        isPrinting2DMatrixEnabled = argsList.get(argsList.indexOf("-isPrinting2DMatrixEnabled") + 1).equalsIgnoreCase("true");
        LOGGER.log(Level.INFO, "isPrinting2DMatrixEnabled is " + isPrinting2DMatrixEnabled);

        isDivideAndConquerEnabled = argsList.get(argsList.indexOf("-isDivideAndConquerEnabled") + 1).equalsIgnoreCase("true");
        LOGGER.log(Level.INFO, "isDivideAndConquerEnabled is " + isDivideAndConquerEnabled);

        isLoggingEnabled = argsList.get(argsList.indexOf("-isLoggingEnabled") + 1).equalsIgnoreCase("true");
        LOGGER.log(Level.INFO, "isLoggingEnabled is " + isLoggingEnabled);

        logLimitationsAndExit();
    }

    private static void logLimitationsAndExit() {
        if (isDivideAndConquerEnabled && !isSpaceOptimizationEnabled) {
            LOGGER.log(Level.SEVERE, "Divide and Conquer + DP can only run with Space Optimized enabled. Please set -isSpaceOptimizationEnabled = true or -isDivideAndConquerEnabled = false");
            System.exit(1);
        }

        if (isPrinting2DMatrixEnabled && isSpaceOptimizationEnabled) {
            LOGGER.log(Level.SEVERE, "2D DP Matrix cannot be printed if Space Optimization is enabled. Please set -isPrinting2DMatrixEnabled = false");
            System.exit(1);
        }
    }

    public static Pair GenerateInputStringsFromFiles(String filename) {
        List<String> data = fetchDataFromFile(filename);
        Input input = fetchInputComponents(data);
        String a = fetchInputStrings(input.firstString, input.indexes1);
        String b = fetchInputStrings(input.secondString, input.indexes2);
        return new Pair(a, b);
    }

    private static String fetchInputStrings(String base, List<Integer> indexes) {
        int lengthSupposedToBe = (int) (Math.pow(2, indexes.size())) * base.length();
        StringBuilder sb = null;
        for (int index : indexes) {
            sb = new StringBuilder(base);
            base = sb.insert(index + 1, sb.toString().toCharArray(), 0, sb.toString().length()).toString();
        }
        assert sb != null;
        int generatedStringLength = sb.toString().length();
        try {
            if (generatedStringLength == lengthSupposedToBe) return sb.toString();
        } catch (Exception exception) {
            LOGGER.log(Level.SEVERE, "Actual and calculated length of the string do not match");
            System.exit(1);
        }
        return null;
    }

    private static Input fetchInputComponents(List<String> data) {
        int indexToSplit = 0;
        for (String d: data) {
            if (Pattern.matches("([ACGT])\\w+", d) && data.indexOf(d) != 0) {
                indexToSplit = data.indexOf(d);
            }
        }
        String firstBaseString = data.get(0);
        String secondBaseString = data.get(indexToSplit);
        List<Integer> indexes1 = new ArrayList<>();
        List<Integer> indexes2 = new ArrayList<>();
        for (int i = 1; i < indexToSplit; i++) {
            indexes1.add(Integer.parseInt(data.get(i)));
        }
        for (int i = indexToSplit + 1; i < data.size(); i++) {
            indexes2.add(Integer.parseInt(data.get(i)));
        }
        return new Input(firstBaseString, secondBaseString, indexes1, indexes2);
    }

    private static List<String> fetchDataFromFile(String filename) {
        Path path = Paths.get(BASE_PATH, filename);
        List<String> data = null;
        try {
            data = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static void writeOutputToFile(String filename) {
        try {
            Path path = Paths.get(BASE_PATH, filename);
            Files.write(path, outputData.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void MapCytokynesToIndices() {
        hm.put('A', 0);
        hm.put('C', 1);
        hm.put('G', 2);
        hm.put('T', 3);
    }

    public static Pair DivideAndConquerSequenceAlignment(String a, String b) {
        LOGGER.log(Level.INFO, String.format("Recursing for: [%s, %s]", a, b));
        StringBuilder a1 = new StringBuilder();
        StringBuilder b1 = new StringBuilder();
        Pair ans = null;
        if (a.length() == 0) {
            for (int i = 0; i < b.length(); i++) {
                a1.append("_");
                b1.append(b.charAt(i));
                ans = new Pair(a1.toString(), b1.toString());
            }
        } else if (b.length() == 0) {
            for (int i = 0; i < a.length(); i++) {
                a1.append(a.charAt(i));
                b1.append("_");
                ans = new Pair(a1.toString(), b1.toString());
            }
        } else if (a.length() == 1 || b.length() == 1) {
            ans = NeedlemanWunsch(a, b);
        } else {
            int alen = a.length();
            int amid = a.length() / 2;
            int blen = b.length();

            List<Integer> scoreL = NWScore(a.substring(0, amid), b);
            String aRev = new StringBuilder(a.substring(amid + 1, alen)).reverse().toString();
            String bRev = new StringBuilder(b).reverse().toString();
            List<Integer> scoreR = NWScore(aRev, bRev);
            Collections.reverse(scoreR);
            int bmid = getMin(scoreL, scoreR);

            Pair p1 = DivideAndConquerSequenceAlignment(a.substring(0, amid), b.substring(0, bmid));
            Pair p2 = DivideAndConquerSequenceAlignment(a.substring(amid, alen), b.substring(bmid, blen));
            ans = p1.add(p2);
        }
        return ans;
    }

    private static int getMin(List<Integer> a, List<Integer> b) {
        int min = Integer.MAX_VALUE;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            int sum = a.get(i) + b.get(i);
            ans.add(sum);
            min = Math.min(min, sum);
        }
        return ans.indexOf(min);
    }

    private static List<Integer> NWScore(String a, String b) {
        // creating 2 arrays - evenEdits and oddEdits that will store my currentArray and previousArray
        int[] evenEdits = new int[b.length() + 1];
        int[] oddEdits = new int[b.length() + 1];

        // Initializing the evenEdits array because we will always start from index 0 which is even
        for (int j = 0; j < b.length() + 1; j++) {
            evenEdits[j] = j * GAP_PENALTY;
        }

        // Initialize 2 arrays, currentEdits and previousEdits which will store our DP calculations
        int[] currentEdits;
        int[] previousEdits;

        // check which (from oddEdits and evenEdits) is the currentEdit and which one is the other
        // we traverse the while loop till big.length() because now we need to traverse the bigger string.
        // refer to the dp[][] array in Solution1 to draw parallels
        for (int i = 1; i < a.length() + 1; i++) {
            if (i % 2 == 0) {
                currentEdits = evenEdits;
                previousEdits = oddEdits;
            } else {
                currentEdits = oddEdits;
                previousEdits = evenEdits;
            }

            // this is same as initializing the dp[][] array's first column
            currentEdits[0] = i * GAP_PENALTY;

            for (int j = 1; j < b.length() + 1; j++) {
                int left = currentEdits[j - 1] + GAP_PENALTY;
                int top = previousEdits[j] + GAP_PENALTY;
                int diagonal = previousEdits[j - 1] + getMismatchCost(a.charAt(i - 1), b.charAt(j - 1));
                currentEdits[j] = Math.min(diagonal, Math.min(left, top));
            }
        }
        // depending on the length of the bigger string (which spans across the rows in the dp[][] arrray)
        // the answer might be stored in either the evenEdits[] or the oddEdits[] array
        List<Integer> evenList = new ArrayList<>();
        List<Integer> oddList = new ArrayList<>();
        for (int elem : evenEdits) {
            evenList.add(elem);
        }

        for (int elem : oddEdits) {
            oddList.add(elem);
        }

        return (a.length() % 2 == 0) ? evenList : oddList;
    }

    private static int getMismatchCost(char c1, char c2) {
        int i = hm.get(c1);
        int j = hm.get(c2);
        // System.out.println(String.format("Checking mismatch for[%s, %s]. Mismatch value found: %d", c1, c2, MISMATCH_COST[i][j]));
        return MISMATCH_COST[i][j];
    }

    public static Pair NeedlemanWunsch(String a, String b) {
        int m = a.length();
        int n = b.length();
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0) {
                    dp[i][j] = j * GAP_PENALTY;
                } else if (j == 0) {
                    dp[i][j] = i * GAP_PENALTY;
                } else {
                    int top = dp[i - 1][j] + GAP_PENALTY;
                    int left = dp[i][j - 1] + GAP_PENALTY;
                    int diagonal = dp[i - 1][j - 1] + getMismatchCost(a.charAt(i - 1), b.charAt(j - 1));
                    dp[i][j] = Math.min(diagonal, Math.min(top, left));
                }
            }
        }
        NWScore = dp[m][n];
        if (isPrinting2DMatrixEnabled) {
            LOGGER.log(Level.INFO, "Printing DP Matrix enabled. Printing...");
            print2DMatrix(dp);
        }
        return printAlignment(a, b, dp);
    }

    private static Pair printAlignment(String a, String b, int[][] dp) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        int i = a.length();
        int j = b.length();
        while (i != 0 && j != 0) {
            int left = dp[i][j - 1] + GAP_PENALTY;
            int top = dp[i - 1][j] + GAP_PENALTY;
            int diagonal = dp[i - 1][j - 1] + getMismatchCost(a.charAt(i - 1), b.charAt(j - 1));
            if (dp[i][j] == diagonal) {
                sb1.append(a.charAt(i - 1));
                sb2.append(b.charAt(j - 1));
                i--;
                j--;
            } else if (dp[i][j] == left) {
                sb1.append("_");
                sb2.append(b.charAt(j - 1));
                j--;
            } else if (dp[i][j] == top) {
                sb1.append(a.charAt(i - 1));
                sb2.append("_");
                i--;
            }
        }

        while (i > 0) {
            // append character of the string for which the index is changing to the corresponding stringbuilder
            // in this case, i is changing, hence we append sb1 with string a's character at i
            sb1.append(a.charAt(i - 1));
            sb2.append("_");
            i--;
        }

        while (j > 0) {
            sb1.append("_");
            // append character of the string for which the index is changing to the corresponding stringbuilder
            // in this case, j is changing, hence we append sb2 with string b's character at j
            sb2.append(b.charAt(j - 1));
            j--;
        }
        String s1 = sb1.reverse().toString();
        String s2 = sb2.reverse().toString();
        return new Pair(s1, s2);
    }

    private static void print2DMatrix(int[][] dp) {
        System.out.println("DP Matrix: ");
        for (int[] ints : dp) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(ints[j] + "\t");
            }
            System.out.println();
        }
    }

    public static boolean isNWScoreEqual(Pair pair1, Pair pair2) {
        int score1 = calculateScore(pair1);
        int score2 = calculateScore(pair2);
        return score1 == score2;
    }

    public static int calculateScore(Pair pair) {
        String a = pair.a;
        String b = pair.b;
        int score = 0;
        for (int i = 0; i < a.length(); i++) {
            char c1 = a.charAt(i);
            char c2 = b.charAt(i);
            if (c1 == '_' || c2 == '_') {
                score += GAP_PENALTY;
            } else {
                score += getMismatchCost(a.charAt(i), b.charAt(i));
            }
        }
        return score;
    }
}
