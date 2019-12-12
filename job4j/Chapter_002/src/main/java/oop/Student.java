package oop;

public class Student {  public void song (String lyrics){
    System.out.println("I can sing a song" + lyrics);
}
    public void music() {
        System.out.println("Tra tra tra");
    }

    public static void main(String[] args) {
        Student petya = new Student();
        String bee = " I believe you";
        petya.music();
        petya.music();
        petya.music();
        petya.song(bee);
        petya.song(bee);
        petya.song(bee);
    }

}