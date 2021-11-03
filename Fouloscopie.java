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

		var k = 2;
		while (true) {
			if (left(k) < index && index <= right(k)) {
				return k;
			}

			k++;
		}
	}

	public static Point coords(int index) {
		var k = findK(index);

		if (index <= 4) {
			switch (index) {
				case 1 : return new Point(1,1);
				case 2 : return new Point(1, 2);
				case 3 : return new Point(2, 1);
				case 4 : return new Point(2, 2);
			}
		}
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

	private static int lessThan2(Point coords) {
		switch (coords.x()) {
			case 1:
				switch (coords.y()) {
					case 1:
						return 1;
					case 2:
						return 2;
				}
			case 2:
				switch (coords.y()) {
					case 1:
						return 3;
					case 2:
						return 4;
				}
		}
		throw new UnsupportedOperationException("Never reached");
	}

	private static int findK(Point coords) {
		var k = 2;
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

		if (coords.y() == limit.y()) {
			return prevAcc + half + coords.x();
		} else {
			if (coords.x() == limit.x() - 1) {
				return prevAcc + coords.y();
			} else {
				return prevAcc + quarter + coords.y();
			}
		}
	}

	public static int index(Point coords) {
		if (coords.x() <= 2 && coords.y() <= 2) {
			return lessThan2(coords);
		}

		var k = findK(coords);

		return index(coords, kCoords(k-1), kCoords(k));
	}

	public static void main(String[] args) {
		//SpringApplication.run(FouloscopieApplication.class, args);

//		try {
//			URL url_flag = new URL("https://api-flag.fouloscopie.com/flag");
//			URL url_user = new URL("https://admin.fouloscopie.com/users/"); // Add user_id
//
//			String my_id = "90315f81-70ae-494f-b6ec-650631e28f3a";
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		System.out.println(IntStream.range(1, 1000).anyMatch(i -> index(coords(i)) != i));
	}
}