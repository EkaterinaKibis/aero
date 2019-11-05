package ru.kibis.dataTypes.condition;

public class EndWith {
    public static boolean endsWith(char[] word, char[] post) {
        boolean result = true;
        int counter = word.length - 1;
        for (int i = post.length - 1; i >= 0; i--) {
            if (post[i] != word[counter]) {
                result = false;
                break;
            }
            counter--;
        }
        return result;
    }
}
