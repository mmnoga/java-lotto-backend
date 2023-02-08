package pl.lotto.numberreceiver;

enum ValidationError {

    OUT_OF_RANGE("out of range number"),
    REPEATED("repeated number"),
    MORE_THAN_SIX_NUMBERS("more than six userNumbers"),
    LESS_THAN_SIX_NUMBERS("less than six userNumbers");

    final String message;

    ValidationError(String message) {
        this.message = message;
    }

}
