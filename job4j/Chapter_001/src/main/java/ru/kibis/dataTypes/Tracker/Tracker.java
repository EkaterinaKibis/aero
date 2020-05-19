package ru.kibis.dataTypes.Tracker;

import java.util.Arrays;
import java.util.Random;

public class Tracker {
    /**
     * Ìàññèâ äëÿ õðàíåíèå çàÿâîê.
     */
    private final Item[] items = new Item[10];

    /**
     * Óêàçàòåëü ÿ÷åéêè äëÿ íîâîé çàÿâêè.
     */
    private int position = 0;

    /**
     * Ìåòîä ðåàëèçàóùèé äîáàâëåíèå çàÿâêè â õðàíèëèùå
     *
     * @param item íîâàÿ çàÿâêà
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Ìåòîä ãåíåðèðóåò óíèêàëüíûé êëþ÷ äëÿ çàÿâêè.
     * Òàê êàê ó çàÿâêè íåò óíèêàëüíîñòè ïîëåé, èìåíè è îïèñàíèå. Äëÿ èäåíòèôèêàöèè íàì íóæåí óíèêàëüíûé êëþ÷.
     *
     * @return Óíèêàëüíûé êëþ÷.
     */
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    public boolean replace(String name, Item item) {
        boolean result = false;
        for (int i = 0; i < items.length; i++) {
            if (items[i].getName().equals(name)) {
                this.items[i] = item;
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean delete(String name) {
        boolean result = false;
        for (int i = 0; i < items.length; i++) {
            if (items[i].getName().equals(name)) {
               // this.items[i] = null;
                System.arraycopy(items, i + 1, items, i, items.length - i - 1);
                this.items[items.length -1] = null;
                result = true;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Tracker tr = new Tracker();
        for(int i = 0; i < 10; i ++) {
            tr.add(new Item("Item " + i));
        }
        tr.replace("Item 5", new Item("Item 900"));
        for(Item item : tr.items) {
            System.out.println(item.getName());
        }
        tr.delete("Item 900");
        for(Item item : tr.items) {
            System.out.println(item.getName());
        }
    }
}
