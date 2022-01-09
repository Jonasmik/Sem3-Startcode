package dtos;

public class FunFactDTO {
    String id;
    String fact;

    public FunFactDTO(String id, String fact) {
        this.id = id;
        this.fact = fact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }
}
