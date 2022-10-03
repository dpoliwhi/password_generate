import java.util.Scanner;

public class Password {
    final static int SYMBOL_TYPES = 4;
    final static String CHAR_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    final static String CHAR_UPPERCASE = CHAR_LOWERCASE.toUpperCase();
    final static String DIGIT = "0123456789";
    final static String OTHER_SYMBOL = "_*-";

    public static int getPasswordLength() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите длину пароля (число больше 0)");
        int length = input.nextInt();
        while (length < 8) {
            if (length < 0) return getPasswordLength();
            System.out.println("Пароль с " + length + " количеством символов небезопасен");
            System.out.println("Введите длину пароля еще раз");
            length = input.nextInt();
        }
        return length;
    }

    /*
    method generate number of characters of each type of symbols required for the password:
    - Uppercase characters
    - Lowercase characters
    - Digits
    - Other symbols (_*-)
    Return array with the number of each character
     */
    public static int[] findNumberOfEachSymbol(int length) {
        int[] result = new int[SYMBOL_TYPES];
        int min = 1, max = length - (SYMBOL_TYPES - 1);
        for (int i = 0; i < SYMBOL_TYPES - 1; i++) {
            result[i] = (int) (Math.random() * max) + min;
            max = max - result[i];
            max++;
        }
        result[result.length - 1] = max;
        return result;
    }

    public static String generatePassword(int[] numberOfTypes) {
        String password = "";
        // generate uppercase characters
        int count = numberOfTypes[0];
        while (count-- > 0) {
            int randomSymbol = (int) (Math.random() * CHAR_UPPERCASE.length());
            password += CHAR_UPPERCASE.charAt(randomSymbol);
        }
        // generate lowercase characters
        count = numberOfTypes[1];
        while (count-- > 0) {
            int randomSymbol = (int) (Math.random() * CHAR_LOWERCASE.length());
            password += CHAR_LOWERCASE.charAt(randomSymbol);
        }
        // generate digits
        count = numberOfTypes[2];
        while (count-- > 0) {
            int randomSymbol = (int) (Math.random() * DIGIT.length());
            password += DIGIT.charAt(randomSymbol);
        }
        // generate other symbols
        count = numberOfTypes[3];
        while (count-- > 0) {
            int randomSymbol = (int) (Math.random() * OTHER_SYMBOL.length());
            password += OTHER_SYMBOL.charAt(randomSymbol);
        }
        return password;
    }

    /*
    method shuffle characters of password
     */
    public static String shuffle(String pswrd) {
        char[] pswrdArr = pswrd.toCharArray();
        for (int i = 1; i < pswrdArr.length; i++) {
            int j = (int) (Math.random() * i);
            char temp = pswrdArr[i];
            pswrdArr[i] = pswrdArr[j];
            pswrdArr[j] = temp;
        }
        return String.valueOf(pswrdArr);
    }

    public static void startGenerateAlgo() {
        int l = getPasswordLength();
        int[] types = findNumberOfEachSymbol(l);
        String password = generatePassword(types);
        System.out.print("Ваш пароль: ");
        System.out.println(shuffle(password));
    }
}