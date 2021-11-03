public class Fouloscopie {
	private static int findK(int index) {
		if (index <= 0) throw new IllegalArgumentException("> 0");
		if (index <= 4) throw new UnsupportedOperationException("Not implemented yet");

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

	private static String coords(int index) {
		var k = findK(index);
		var l = left(k);
		var r = right(k);

		var diff = r - l;
		int half = r - diff / 2;
		int quarter = half / 2;

		if (index <= half) {
			return (index < quarter ? 2 * k - 1 : 2 * k) + "," + (half - index);
		} else {
			return (index - half) + "," + (k + 1);
		}
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

		for (int i = 5; i <= 24; i++) {
			System.out.println("[" + i + "] - " + coords(i));
		}
	}
}