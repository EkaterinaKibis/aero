package ru.kibis.dataTypes.array;

public class Check {
    public boolean mono(boolean[] data) {
        boolean result = false;
        int k = 1;
        for (int i = 0; i < data.length; i++) {
            k = data[i] ? ++k : --k;
            if (k > 0) {
                result = true;
            }
        }
        return result;
    }
}
