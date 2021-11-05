import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Fouloscopie {

	// == ======== ==
	// = UTILITAIRE =
	// == ======== ==

	private static Point kCoords(int k) {
		return new Point(2 * k, k + 1);
	}

	private static int left(int k) {
		return 2 * (k-1) * k;
	}

	private static int right(int k) {
		return 2 * k * (k + 1);
	}

	private static boolean pointBetween(Point coords, Point previous, Point limit) {
		return !coords.inside(previous) && coords.inside(limit);
	}

	// == ==== ==
	// = COORDS =
	// == ==== ==

	private static int findK(int index) {
		if (index <= 0) throw new IllegalArgumentException("> 0");

		var k = 1;
		while (true) {
			if (left(k) < index && index <= right(k)) {
				return k;
			}

			k++;
		}
	}

	public static Point coords(int index) {
		var k = findK(index);

		var l = left(k);
		var r = right(k);

		var diff = r - l;
		int half = r - diff / 2;
		int quarter = l + diff / 4;

		if (index <= half) {
			var col = index % k;
			var x = index <= quarter ? 2 * k - 1 : 2 * k;
			var y = col == 0 ? k : col;
			return new Point(x,y);
		} else {
			return new Point(index - half, k + 1);
		}
	}

	// == === ==
	// = INDEX =
	// == === ==

	private static int findK(Point coords) {
		var k = 1;
		var limit = kCoords(k-1);
		Point previous;

		while (true) {
			previous = limit;
			limit = kCoords(k);
			if (pointBetween(coords, previous, limit)) {
				return k;
			}
			k++;
		}
	}

	private static int index(Point coords, Point previous, Point limit) {
		var prevAcc = previous.product();
		var limitAcc = limit.product();
		var diff = limitAcc - prevAcc;
		var half = diff / 2;
		var quarter = half / 2;

		return coords.y() == limit.y()
				? prevAcc + half + coords.x()
			: coords.x() == limit.x() - 1
				? prevAcc + coords.y()
				: prevAcc + quarter + coords.y();
	}

	public static int index(Point coords) {
		var k = findK(coords);
		return index(coords, kCoords(k-1), kCoords(k));
	}

	public static void main(String[] args) {
		IntStream.range(1, 100)
				.boxed()
				.map(Fouloscopie::coords)
				.peek(System.out::println)
				.map(Fouloscopie::index)
				.forEach(System.out::println);
	}
}