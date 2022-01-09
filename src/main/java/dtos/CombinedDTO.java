package dtos;

public class CombinedDTO {
    private String catFact;
    private String dogFact;
    private String numberFact;
    private String randomFact;
    private String funFact;

    public CombinedDTO(CatFactDTO catFactDTO, DogFactDTO dogFactDTO, NumberFactDTO numberFactDTO, RandomFactDTO randomFactDTO, FunFactDTO funFactDTO) {
        this.catFact = catFactDTO.getText();
        this.dogFact = dogFactDTO.getFirstFact();
        this.numberFact = numberFactDTO.getText();
        this.randomFact = randomFactDTO.getText();
        this.funFact = funFactDTO.getFact();
    }

    public String getCatFact() {
        return catFact;
    }

    public void setCatFact(String catFact) {
        this.catFact = catFact;
    }

    public String getDogFact() {
        return dogFact;
    }

    public void setDogFact(String dogFact) {
        this.dogFact = dogFact;
    }

    public String getNumberFact() {
        return numberFact;
    }

    public void setNumberFact(String numberFact) {
        this.numberFact = numberFact;
    }

    public String getRandomFact() {
        return randomFact;
    }

    public void setRandomFact(String randomFact) {
        this.randomFact = randomFact;
    }

    public String getFunFact() {
        return funFact;
    }

    public void setFunFact(String funFact) {
        this.funFact = funFact;
    }
}
