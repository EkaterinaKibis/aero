package ru.kibis.dataTypes.loop;


public class Board {

    public static void paint(int width, int height) {
        for (int i = 0; height >= i; i++) {
            for (int g = 0; width >= g; g++) {
                int sum = i + g;
                String result = (sum % 2 == 0) ? "X" : " ";
                System.out.println(result);
            }
        }
    }

    public static void main(String[] args) {
        paint(3, 3);
        System.out.println();
        paint(4, 4);
    }
}
