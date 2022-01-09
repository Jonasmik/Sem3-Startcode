package dtos;

public class CatFactDTO {
    String _id;
    String text;

    public CatFactDTO(String _id, String text) {
        this._id = _id;
        this.text = text;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
