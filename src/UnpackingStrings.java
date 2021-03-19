import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author Bashirova Milyausha
 */
public class UnpackingStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите строку:");
        String str = sc.nextLine();

        boolean validityStr = strValidity(str);
        if (validityStr == true) {
            int number = 1;
            while (number == 1) {
                if (str.contains("[") || str.contains("]"))
                    str = newStr(str);
                else break;
            }
            System.out.println(str);
        } else
            System.out.println("Строка невалидна");

    }

    private static boolean strValidity(String str) {

        boolean letter = Pattern.matches(".*\\p{InCyrillic}.*", str);
        boolean signs = Pattern.matches(".*[,|!?(){}>/&^:;$#@~№<>/\\.].*", str);
        if (letter == true && signs == true)
            return false;

        int leftBkt = 0;
        int rightBkt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '[') {
                if (!Character.isDigit(str.charAt(i - 1))) {
                    return false;
                }
                leftBkt++;
            }
            if (str.charAt(i) == ']') {
                rightBkt++;
            }
        }
        if (rightBkt != leftBkt || rightBkt == 0 && leftBkt == 0)
            return false;
        return true;

    }

    public static String newStr(String str) {
        String newStr = "";
        int indStart = 0;
        int indEnd = 0;
        int number = 1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '[') {
                indStart = i;
                if (i != 0)
                    number = Character.getNumericValue(str.charAt(i - 1));
            }
            if (str.charAt(i) == ']') {
                indEnd = i;
                break;
            }
        }
        newStr = str.substring(indStart + 1, indEnd);
        String addStr = newStr;
        while (number != 1) {
            newStr += addStr;
            number--;
        }
        String newValue = str.substring(0, indStart - 1) + newStr + str.substring(indEnd + 1);
        return newValue;
    }

}
