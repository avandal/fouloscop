import java.util.Objects;

public final class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        if (x <= 0 || y <= 0) {
            throw new IllegalArgumentException("Negative coords");
        }
        this.x = x;
        this.y = y;
    }

    public boolean inside(Point limit) {
        return x <= limit.x && y <= limit.y;
    }

    public int product() {
        return x * y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "[" + x + ',' + y + ']';
    }
}
