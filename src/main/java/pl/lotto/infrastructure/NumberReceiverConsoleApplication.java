package pl.lotto.infrastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.NumberReceiverRepository;
import pl.lotto.numberreceiver.dto.TicketDto;

class NumberReceiverConsoleApplication {

    static int MIN_VALUE = 1;
    static int MAX_VALUE = 99;
    static int NUMBER_OF_DRAW = 6;
    static NumberReceiverRepository numberReceiverRepository = new InMemoryNumberReceiverConsoleApplicationImpl();

    public static void main(String[] args) throws IOException {
        final Clock date = new AdjustableClock(
                LocalDateTime.of(2023, 1, 5, 11, 5)
                        .toInstant(ZoneOffset.UTC),
                ZoneId.systemDefault());
        NumberReceiverFacade numberReceiverFacade =
                new NumberReceiverFacade(
                        date,
                        numberReceiverRepository);
        List<Integer> userNumbers = getNumbers();
        TicketDto ticket = numberReceiverFacade.inputNumbers(userNumbers);
        System.out.println(ticket);
    }

    private static List<Integer> getNumbers() throws IOException {
        List<Integer> userNumbers = new ArrayList<>();
        BufferedReader in;
        in = new BufferedReader((new InputStreamReader(System.in)));
        for (int i = 1; i < NUMBER_OF_DRAW + 1; i++) {
            while (true) {
                System.out.printf(
                        "Enter %d of %d number (range %d to %d): ",
                        i, NUMBER_OF_DRAW, MIN_VALUE, MAX_VALUE);
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
