package ru.kibis.dataTypes.condition;

public class MultiMax {
    public int max(int first, int second, int third) {

        int q = first > second ? first : second;
        int result = q > third ? q : third;

        return result;
    }
}
