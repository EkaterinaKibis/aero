package ru.kibis.dataTypes.loop;

public class Board {
    public static void paint(int width, int height) {
        for ( int i = 0; height >= i; i++) {
            for ( int g = 0; width >= g; g++) {

                int sum = i + g;
                if ((sum % 2) == 0) {
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        paint(3, 3);
        System.out.println();
        paint(4, 4);
    }
}
