import java.util.Arrays;
import java.util.HashMap;

public class Yatzy {

    protected int[] dice;

    public Yatzy(int[] numbers) {
        dice = numbers;
    }

    public static int chance(int[] valuesDices) {

        return Arrays.stream(valuesDices).reduce(Integer::sum).orElse(0);

    }

    public static int yatzy(int[] dice) {

        int firstDiceValue = dice[0];

        for (int i = 1; i < dice.length; i++) {
            if (firstDiceValue != dice[i]) return 0;
        }

        return 50;

    }

    public static int sumEqualNumbers(int[] numbers, int numberToCheck) {
        int sum = 0;
        for (int number : numbers) {
            sum += number == numberToCheck ? number : 0;
        }

        return sum;
    }

    public static int ones(int[] numbers) {
        return sumEqualNumbers(numbers, 1);
    }

    public static int twos(int[] numbers) {

        return sumEqualNumbers(numbers, 2);
    }

    public static int threes(int[] numbers) {

        return sumEqualNumbers(numbers, 3);
    }

    public int fours() {

        return sumEqualNumbers(dice, 4);
    }

    public int fives() {

        return sumEqualNumbers(dice, 5);
    }

    public int sixes() {
        return sumEqualNumbers(dice, 6);
    }

    public static HashMap<Integer, Integer> counterMatchingValuesDices(int[] dices) {

        HashMap<Integer, Integer> mapValuesDices = new HashMap<>();
        for (int valueDice = 1; valueDice <= 6; valueDice++) {
            mapValuesDices.put(valueDice, 0);
        }

        for (int value : dices) {
            mapValuesDices.put(value, mapValuesDices.get(value) + 1);
        }

        return mapValuesDices;

    }

    public static int scoreValueOfAKind(int[] dices, int amount) {
        HashMap<Integer, Integer> counterMatchingDices = counterMatchingValuesDices(dices);

        for (int valueDice = 6; valueDice >= 1; valueDice--) {
            int valueMatchingDice = counterMatchingDices.get(valueDice);
            if (valueMatchingDice >= amount) return valueDice * amount;
        }
        return 0;
    }

    public static int score_pair(int[] dices) {

        return scoreValueOfAKind(dices, 2);
    }

    public static int score_two_pair(int[] dices) {

        int pairs = 0;
        int score = 0;
        HashMap<Integer, Integer> counterMatchingDices = counterMatchingValuesDices(dices);

        for (int valueDice = 6; valueDice >= 1; valueDice--) {
            int valueMatchingDice = counterMatchingDices.get(valueDice);
            if (valueMatchingDice >= 2) {
                pairs++;
                score += valueDice;
            }

        }
        return pairs == 2 ? score * 2 : 0;

    }

    public static int three_of_a_kind(int[] dices) {

        return scoreValueOfAKind(dices, 3);
    }

    public static int four_of_a_kind(int[] dices) {

        return scoreValueOfAKind(dices, 4);
    }


    public static int smallStraight(int[] dices) {

        int counterSmallStraight = 1;
        for (int valueDice : dices) {
            if (!(counterSmallStraight == valueDice)) return 0;

            counterSmallStraight++;
        }

        return 15;

    }

    public static int largeStraight(int[] dices) {

        int counterLargeStraight = 6;

        for (int i = dices.length - 1; i >= 0; i--) {

            if (!(counterLargeStraight == dices[i])) return 0;

            counterLargeStraight--;
        }

        return 20;

    }

    public static int fullHouse(int[] dices) {
        HashMap<Integer, Integer> counterMatchingDices = counterMatchingValuesDices(dices);

        boolean hasTwoOfAKind = false;
        boolean hasThreeOfAKind = false;

        for (int valueDice = 1; valueDice <= 6; valueDice++) {
            if (counterMatchingDices.get(valueDice) == 2) {
                hasTwoOfAKind = true;
            }
            if (counterMatchingDices.get(valueDice) == 3) {
                hasThreeOfAKind = true;
            }
        }

        if (hasTwoOfAKind && hasThreeOfAKind) {
            return chance(dices);
        }

        return 0;
    }
}



