package pl.lotto.winningnumbergenerator;

public class NumberGeneratorConfiguration {

    public NumberGeneratorFacade numberGeneratorFacade(){
        NumberGenerator numberGenerator = new NumberGenerator();
        return new NumberGeneratorFacade(numberGenerator);
    }

}
