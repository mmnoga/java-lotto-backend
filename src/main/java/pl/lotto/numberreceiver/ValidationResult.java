package pl.lotto.numberreceiver;

record ValidationResult(boolean isValid, String message) {

    public boolean isNotValid() {
        return !isValid;
    }

}
