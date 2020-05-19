package ru.kibis.dataTypes.Matches;

import java.util.Scanner;

public class Matches {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean run = true;
        int s = 11;
        while (run) {
            System.out.println("Игрок 1 тяните спички");
            int select = Integer.valueOf(input.nextLine());
            if (select > 3) {
                System.out.println("Введите значение от 1-3");
            } else {
                s = s - select;
                System.out.println("На столе спичек" + s);
            }
            System.out.println("Игрок 2 тяните спички");
            int select2 = Integer.valueOf(input.nextLine());
            if (select2 > 3) {
                System.out.println("Введите значение от 1-3");
            } else {
                s = s - select2;
                System.out.println("На столе спичек" + s);
            }


            if (s == 0) {
                System.out.println("Игра завершена.");
                run = false;
            }

        }
    }
}