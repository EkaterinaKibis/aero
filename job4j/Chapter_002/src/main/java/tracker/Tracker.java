package tracker;
import java.util.Arrays;
import java.util.Random;

public class Tracker {
    /**
     * Ìàññèâ äëÿ õðàíåíèå çàÿâîê.
     */
    private final Item[] items = new Item[100];

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
    public Item[] findAll(){
        return  Arrays.copyOf(items, this.position -1);

    }

    public boolean replace(String id, Item item) {
        boolean result = false;
        for (int i = 0; i < items.length; i++) {
            if (items[i].getId().equals(id)) {
                this.items[i] = item;
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < items.length; i++) {
            if (items[i].getId().equals(id)) {
                this.items[i] = null;
                System.arraycopy(items, i, items, i + 1, items.length - 1);
                this.items[items.length - 1] = null;
                result = true;
                this.position--;
                break;
            }
        }
        return result;
    }

    Item[] findByName(String key) {
        Item[] s = new Item[this.position];
        int counter = 0;
        for (int i = 0; i < this.position; i++) {
            if (items[i].getName().equals(key)) {
                s[counter] = items[i];
                counter++;
            }
        }
        System.arraycopy(s, counter, s, counter + 1, this.position);
        return Arrays.copyOf(s, counter);
    }

    public Item findById(String id) {
        Item item = null;
        for (int i = 0; i < this.position; i++) {
            if (items[i].getId().equals(id)) {
                item = this.items[i];
                break;
            }
        }
        return item;
    }
}