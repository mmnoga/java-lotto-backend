package pl.lotto.numberreceiver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class NumberReceiver implements NumberProvider{

    @Override
    public List<Integer> getNumbers() throws IOException {
        List<Integer> userNumbers = new ArrayList<>();
        BufferedReader in;
        in = new BufferedReader((new InputStreamReader(System.in)));
        for (int i = 1; i < NUMBER_OF_DRAW + 1; i++) {
            while (true) {
                System.out.print(
                        String.format(
                                "Enter %d of %d number (range %d to %d): ",
                                i, NUMBER_OF_DRAW, MIN_VALUE, MAX_VALUE));
                try {
                    int numberFromUser = Integer.parseInt(in.readLine());
                    userNumbers.add(numberFromUser);
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Not a number. Try again.");
                }
            }
        }
        return userNumbers;
    }

}
