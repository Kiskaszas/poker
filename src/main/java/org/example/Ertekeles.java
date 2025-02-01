package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Ertekeles {

    private final Random random = new Random();
    private HashMap<HashSet<String>, String> randomLapokMap = new HashMap<>();

    public String randomlapok(){
        LapokEnum[] laptipus = LapokEnum.values();

        while(randomLapokMap.values().size() != 5) {
            int randomNum = random.nextInt(14 - 2) + 2;
            randomLapokMap.putIfAbsent(
                    lapertekRandom(randomNum),
                    String.valueOf(laptipus[random.nextInt(laptipus.length)]));
        }
        List<Lap> lapok = new ArrayList<>();
        for (Map.Entry<HashSet<String>, String> entry : randomLapokMap.entrySet()){
            lapok.add(new Lap(entry.getKey().iterator().next(), entry.getValue()));
        }

        String eredmeny = ertekeles(lapok);
        return randomLapokMap +" "+ lapok + " " + eredmeny;
    }

    protected HashSet<String> lapertekRandom(int randomNum) {
        HashSet<String> lapertekSet = new HashSet<>(1);
        lapertekSet.add(String.valueOf(randomNum));
        return lapertekSet;
    }

    protected String ertekeles(List<Lap> lapok){
        if (isRoyalFlush(lapok)){
            return "Royal Flush";
        } else if (isStraightFlush(lapok)) {
            return "Straight Flush";
        } else if (isPairType(lapok, 4)) {
            return "Poker";
        } else if (isFullHouse(lapok)) {
            return "Full House";
        } else if (isFlush(lapok)) {
            return "Flush";
        } else if (isStraight(lapok)) {
            return "Straight";
        }else if (isTwoPair(lapok)){
            return "Two Pair";
        }
        else if (isPairType(lapok, 3)) {
            return "Drill";
        } else if (isPairType(lapok,2)){
            return "Pár";
        } else {
            Collections.sort(lapok, Comparator.comparing(o -> Integer.valueOf(o.getErtek())));
            System.out.println(lapok.get(lapok.size()-1));
            var highCard = lapok.get(lapok.size()-1).toString();
            highCard = transformCardIgNeed(highCard);
            return "High Card "+highCard;
        }
    }

    protected String transformCardIgNeed(String highCard) {
        String [] splitedHighCar = highCard.split(" ");
        switch (splitedHighCar[0]){
            case "11": return "J " + splitedHighCar[1];
            case "12": return "Q " + splitedHighCar[1];
            case "13": return "K " + splitedHighCar[1];
            case "14": return "A " + splitedHighCar[1];
        }
        return highCard;
    }

    protected boolean isTwoPair(List<Lap> lapok) {
        List<String> duplicates = lapok.stream()
                .map(lap -> lap.getErtek())
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return duplicates.size() == 2;
    }

    protected boolean isPairType(List<Lap> lapok, int pairNum){
        return lapok.stream()
                .map(lap -> lap.getErtek())
                .collect(Collectors.groupingBy(ert -> ert, Collectors.counting()))
                .values()
                .stream()
                .anyMatch(count -> count == pairNum);
    }

    protected boolean isFullHouse(List<Lap> lapok){
        return isPairType(lapok, 2) && isPairType(lapok, 3);
    }

    protected boolean isFlush(List<Lap> lapok){
        return lapok.stream()
                .map(lap -> lap.getTipus())
                .collect(Collectors.groupingBy(ert -> ert, Collectors.counting()))
                .values()
                .stream()
                .anyMatch(count -> count == 5);
    }

    protected boolean isStraight(List<Lap> lapok){
        for (int i = 0; i < lapok.size()-1; i++) {
            int aktLapErtek = Integer.parseInt(lapok.get(i).getErtek());
            int kovLapErtek = Integer.parseInt(lapok.get(i+1).getErtek());
            if (kovLapErtek - aktLapErtek != 1) {
                return false;
            }
        }
        return true;
    }

    protected boolean isStraightFlush(List<Lap> lapok){
        return isStraight(lapok) && isFlush(lapok);
    }

    protected boolean isRoyalFlush(List<Lap> lapok){
        int topCards = 0;
        for (int i = 0; i < lapok.size(); i++) {
            switch (lapok.get(i).getErtek()){
                case "10": topCards++; break;
                case "J": lapok.get(i).setErtek("11"); topCards++; break;
                case "Q": lapok.get(i).setErtek("12"); topCards++; break;
                case "K": lapok.get(i).setErtek("13"); topCards++; break;
                case "A": lapok.get(i).setErtek("14"); topCards++; break;
            }
        }
        if (isStraight(lapok) && isFlush(lapok) && topCards == 5) {
            return true;
        }
        return false;
    }
}
