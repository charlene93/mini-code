package cheny93.github.mini.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiniCodeTest {

    public static void main(String[] args) throws IOException {
        System.out.println("请输入0-99之间的数字：");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DigitsAlphabet digitsAlphabet = new DigitsAlphabet();
        String input = "";
        for (; ; ) {
            input = br.readLine();
            if (!input.matches("[0-9]{1,2}")) {
                System.out.println("输入不合法内容，请输入0-99之间的数字：");
            } else {
                break;
            }
        }
        if (input.length() == 2) {
            input = "0".equals(Character.toString(input.charAt(0))) ? Character.toString(input.charAt(1)) : input;
        }
        StringBuilder sb = new StringBuilder();
        String str = input.length() > 1 ? sb.append(input.charAt(0)).append(", ").append(input.charAt(1)).toString() : input;
        System.out.println("Input: arr[] = {" + str + "}");
        List<String> combinations = digitsAlphabet.getMultiLetters(input);
        System.out.println("Output: " + combinations.toString().replaceAll("[\\[|\\]]{1}", "").replaceAll("\\,", ""));
    }
}

class DigitsAlphabet {
    private Map<String, String> phoneNumMap;

    private void setLetters() {
        phoneNumMap = new HashMap<String, String>() {{
            put("0", "");
            put("1", "");
            put("2", "abc");
            put("3", "def");
            put("4", "ghi");
            put("5", "jkl");
            put("6", "mno");
            put("7", "pqrs");
            put("8", "tuv");
            put("9", "wxyz");
        }};
    }

    public List<String> getMultiLetters(String digits) {
        this.setLetters();
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.isEmpty() || digits.matches("1[0|1]{1}")) {
            return ans;
        }

        if (digits.matches("[2-9]{1}[0|1]{1}")) {
            digits = Character.toString(digits.charAt(0));
        }
        if (digits.matches("[0|1]{1}[2-9]{1}")) {
            digits = Character.toString(digits.charAt(1));
        }

        dfs(ans, digits, 0, new char[digits.length()]);
        return ans;
    }

    public void dfs(List<String> ans, String digits, int i, char[] chars) {
        if (i == digits.length()) {
            ans.add(new String(chars));
        } else {
            String letter = phoneNumMap.get(String.valueOf(digits.charAt(i)));
            for (int j = 0; j < letter.length(); j++) {
                chars[i] = letter.charAt(j);
                dfs(ans, digits, i + 1, chars);
            }
        }
    }
}