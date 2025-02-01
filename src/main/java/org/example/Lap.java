package org.example;

public class Lap implements Comparable<Lap>{

    private String ertek;
    private String tipus;
    public Lap(String ertek, String tipus) {
        this.ertek = ertek;
        this.tipus = tipus;
    }

    public String getErtek() {
        return ertek;
    }

    public void setErtek(String ertek) {
        this.ertek = ertek;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    @Override
    public int compareTo(Lap masik) {
        return Integer.compare(Integer.parseInt(this.ertek), Integer.parseInt(masik.ertek));
    }

    @Override
    public String toString() {
        return getErtek() + " " + getTipus();
    }
}
