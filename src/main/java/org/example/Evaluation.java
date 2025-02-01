package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Evaluation {

    private final Random random = new Random();
    private HashMap<HashSet<String>, String> randomCarsMap = new HashMap<>();

    public String randomlapok(){
        CardsEnum[] carType = CardsEnum.values();

        while(randomCarsMap.values().size() != 5) {
            int randomNum = random.nextInt(14 - 2) + 2;
            randomCarsMap.putIfAbsent(
                    carValueRandom(randomNum),
                    String.valueOf(carType[random.nextInt(carType.length)]));
        }
        List<Card> cards = new ArrayList<>();
        for (Map.Entry<HashSet<String>, String> entry : randomCarsMap.entrySet()){
            cards.add(new Card(entry.getKey().iterator().next(), entry.getValue()));
        }

        String result = evaluation(cards);
        return randomCarsMap +" "+ cards + " " + result;
    }

    protected HashSet<String> carValueRandom(int randomNum) {
        HashSet<String> lapertekSet = new HashSet<>(1);
        lapertekSet.add(String.valueOf(randomNum));
        return lapertekSet;
    }

    protected String evaluation(List<Card> lapok){
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
            Collections.sort(lapok, Comparator.comparing(o -> Integer.valueOf(o.getValue())));
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

    protected boolean isTwoPair(List<Card> cards) {
        List<String> duplicates = cards.stream()
                .map(card -> card.getValue())
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return duplicates.size() == 2;
    }

    protected boolean isPairType(List<Card> cards, int pairNum){
        return cards.stream()
                .map(card -> card.getValue())
                .collect(Collectors.groupingBy(ert -> ert, Collectors.counting()))
                .values()
                .stream()
                .anyMatch(count -> count == pairNum);
    }

    protected boolean isFullHouse(List<Card> cards){
        return isPairType(cards, 2) && isPairType(cards, 3);
    }

    protected boolean isFlush(List<Card> lapok){
        return lapok.stream()
                .map(card -> card.getType())
                .collect(Collectors.groupingBy(ert -> ert, Collectors.counting()))
                .values()
                .stream()
                .anyMatch(count -> count == 5);
    }

    protected boolean isStraight(List<Card> cards){
        for (int i = 0; i < cards.size()-1; i++) {
            int aktLapErtek = Integer.parseInt(cards.get(i).getValue());
            int kovLapErtek = Integer.parseInt(cards.get(i+1).getValue());
            if (kovLapErtek - aktLapErtek != 1) {
                return false;
            }
        }
        return true;
    }

    protected boolean isStraightFlush(List<Card> cards){
        return isStraight(cards) && isFlush(cards);
    }

    protected boolean isRoyalFlush(List<Card> cards){
        int topCards = 0;
        for (int i = 0; i < cards.size(); i++) {
            switch (cards.get(i).getValue()){
                case "10": topCards++; break;
                case "J": cards.get(i).setValue("11"); topCards++; break;
                case "Q": cards.get(i).setValue("12"); topCards++; break;
                case "K": cards.get(i).setValue("13"); topCards++; break;
                case "A": cards.get(i).setValue("14"); topCards++; break;
            }
        }
        if (isStraight(cards) && isFlush(cards) && topCards == 5) {
            return true;
        }
        return false;
    }
}
