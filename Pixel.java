import java.util.Objects;

public class Pixel {
    private String id;
    private String author;
    private String hexColor;
    private int index;

    public Pixel() {
    }

    public Pixel(String id, String author, String hexColor, int index) {
        this.id = id;
        this.author = author;
        this.hexColor = hexColor;
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pixel pixel = (Pixel) o;
        return index == pixel.index && Objects.equals(id, pixel.id) && Objects.equals(author, pixel.author) && Objects.equals(hexColor, pixel.hexColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, hexColor, index);
    }

    @Override
    public String toString() {
        return "Pixel{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", hexColor='" + hexColor + '\'' +
                ", index=" + index +
                '}';
    }
}
