import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Helper {
    public static void main(String[] args) {
        String base1 = shuffleString("ACGT");
        String base2 = shuffleString("TGAC");

        List<Integer> randomIndexArray1 = getRandomIndexArray();
        List<Integer> randomIndexArray2 = getRandomIndexArray();

        System.out.println(base1);
        for (int elem: randomIndexArray1) {
            System.out.println(elem);
        }

        System.out.println(base2);
        for (int elem: randomIndexArray2) {
            System.out.println(elem);
        }
    }

    private static String shuffleString(String s) {
        StringBuilder ans = new StringBuilder();
        List<Character> charList = new ArrayList<>();
        for (char c: s.toCharArray()) {
            charList.add(c);
        }
        Collections.shuffle(charList);
        charList.forEach(ans::append);
        return ans.toString();
    }

    public static List<Integer> getRandomIndexArray() {
        Random random = new Random();
        int count = random.nextInt(8) + 1;
        int i = 1;
        List<Integer> randomIndexList = new ArrayList<>();
        while (randomIndexList.size() != count) {
            int num = random.nextInt(i++ * 2) + 1;
            if (!randomIndexList.contains(num))
                randomIndexList.add(num);
        }
        return randomIndexList;
    }
}
