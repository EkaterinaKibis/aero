package ru.kibis.dataTypes.condition;

public class Triangle {
    public static boolean exist(double ab, double ac, double bc) {
        boolean q = false;
        if (((ab + ac) > bc) & ((ab + bc) > ac) && ((ac + bc) > ab)) {
            q = true;
        }

        return q;

    }
}

