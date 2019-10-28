package ru.kibis.dataTypes.loop;

public class Fitness {
    public int calc(int ivan, int nik) {
        int month = 0;


        while (ivan  < nik ) {
            ivan = ivan * 3;
            nik = nik * 2;
            month++;
        }
        return month;
    }
}
