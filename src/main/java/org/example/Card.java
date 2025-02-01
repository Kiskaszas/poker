package org.example;

import lombok.Data;

@Data
public class Card implements Comparable<Card>{

    private String value;
    private String type;
    public Card(String value, String type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public int compareTo(Card masik) {
        return Integer.compare(Integer.parseInt(this.value), Integer.parseInt(masik.value));
    }

    @Override
    public String toString() {
        return getValue() + " " + getType();
    }
}
