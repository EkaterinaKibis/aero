package ru.kibis.dataTypes.condition;

public class SqMax {
    public static int max(int first, int second, int third, int forth) {
        int result = forth;
        if ((first > second) & (first > third) & (first > forth)) {
            result = first;
        }

        if ((second > third) || (second > forth)) {
            result = second;}
        if (third > forth) {
            result = third;
        }

        return result;
    }
}

