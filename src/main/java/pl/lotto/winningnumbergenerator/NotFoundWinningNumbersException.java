package pl.lotto.winningnumbergenerator;

public class NotFoundWinningNumbersException extends RuntimeException {
    public NotFoundWinningNumbersException(String message) {
        super(message);
    }
}
