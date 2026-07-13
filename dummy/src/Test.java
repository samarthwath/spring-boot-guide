import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        String str = "aaabbaaaacccccbbbcc";
        Map<Character, Integer> charsMap = new HashMap<Character, Integer>();
        int characterCounter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (i < str.length() - 1 && str.charAt(i) == str.charAt(i + 1)) {
                characterCounter++;
            } else if (i < str.length() - 1 && str.charAt(i) != str.charAt(i + 1)) {
                if (charsMap.containsKey(str.charAt(i))) {
                    characterCounter++;
                    Integer countValue = charsMap.get(str.charAt(i));
                    if (characterCounter > countValue) {
                        charsMap.put(str.charAt(i), characterCounter);
                        characterCounter = 0;
                    }
                } else {
                    characterCounter++;
                    charsMap.put(str.charAt(i), characterCounter);
                    characterCounter = 0;
                }
            }
        }
        System.out.println(charsMap);
    }
}
