package dtos;

public class NumberFactDTO {
    String text;
    String number;

    public NumberFactDTO(String text, String number) {
        this.text = text;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
