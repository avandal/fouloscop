public class Fouloscopie {
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

	private static int left(int k) {
		return 2 * (k-1) * k;
	}

	private static int right(int k) {
		return 2 * k * (k + 1);
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

	public static int index(Point coords) {
		System.out.println(coords);

		return 0;
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

		for (int i = 1; i <= 60; i++) {
			System.out.println("[" + i + "] - " + coords(i));
		}

		var index = 11;
		System.out.println(index(coords(index)) + " - expected: " + index);
	}
}