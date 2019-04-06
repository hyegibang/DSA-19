import java.util.ArrayList;
import java.util.List;

public class CoinsOnAClock {

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        List<char[]> result = new ArrayList<>();
        char[] answers = new char[hoursInDay];
        return coinsOnAClockHelper(result, answers, pennies, nickels, dimes, hoursInDay, 0, 0);
    }



    public static List<char[]> coinsOnAClockHelper(List <char[]> current, char[] answers, int pennies,
                                                   int nickels, int dimes, int hoursInDay, int idx, int count) {

        if (count == hoursInDay) {
            char[] temp = new char[answers.length];
            System.arraycopy(answers, 0, temp, 0, answers.length);
            current.add(temp);
            return current;
        }

        for (int i = 0; i < 3; i++) {
            int curr;
            if (answers[idx] != 'p' && answers[idx] != 'n' && answers[idx] != 'd') { //unused
                if (i == 0 && pennies > 0) {
                    answers[idx] = 'p';
                    pennies--;
                    curr = (idx + 1) % answers.length;

                    coinsOnAClockHelper(current, answers, pennies, nickels, dimes, hoursInDay, curr, count + 1);
                    answers[idx] = 0;
                    pennies++;
                }

                if (i == 1 && nickels > 0) {
                    answers[idx] = 'n';
                    nickels--;
                    curr = (idx + 5) % answers.length;

                    coinsOnAClockHelper(current, answers, pennies, nickels, dimes, hoursInDay, curr, count + 1);
                    answers[idx] = 0;
                    nickels++;
                }

                if (i == 2 && dimes > 0) {
                    answers[idx] = 'd';
                    dimes--;
                    curr = (idx + 10) % answers.length;

                    coinsOnAClockHelper(current, answers, pennies, nickels, dimes, hoursInDay, curr, count + 1);
                    answers[idx] = 0;
                    dimes++;
                }
            }
        }
        return current;
    }

}

