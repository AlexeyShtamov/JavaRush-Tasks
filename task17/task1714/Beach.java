package com.javarush.task.task17.task1714;

/* 
Comparable
*/

public class Beach implements Comparable<Beach>{
    private String name;      //название
    private float distance;   //расстояние
    private int quality;    //качество

    public Beach(String name, float distance, int quality) {
        this.name = name;
        this.distance = distance;
        this.quality = quality;
    }

    synchronized public String getName() {
        return name;
    }

    synchronized public void setName(String name) {
        this.name = name;
    }

    synchronized public float getDistance() {
        return distance;
    }

    synchronized public void setDistance(float distance) {
        this.distance = distance;
    }

    synchronized public int getQuality() {
        return quality;
    }

    synchronized public void setQuality(int quality) {
        this.quality = quality;
    }

    public static void main(String[] args) {
        Beach beach1 = new Beach("Ямайка", 123f, 100);
        Beach beach2 = new Beach("Смолино", 124f, 100);
        System.out.println(beach1.compareTo(beach2));
    }

    @Override
    synchronized public int compareTo(Beach o) {
        int winner = 0;
        if(this.quality != o.quality)
            winner += this.quality > o.quality ? 1 : -1;
        if(this.distance != o.distance)
            winner += this.distance < o.distance ? 1 : -1;
        return winner;
    }
}
