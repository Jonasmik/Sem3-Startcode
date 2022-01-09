package dtos;

public class DogFactDTO {
    String[] facts;

    public DogFactDTO() {
    }

    public DogFactDTO(String[] facts) {
        this.facts = facts;
    }

    public String[] getFacts() {
        return facts;
    }

    public void setFacts(String[] facts) {
        this.facts = facts;
    }

    public String getFirstFact(){
        return this.facts[0];
    }
}
