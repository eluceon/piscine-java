public class Program {
	public static void main(String[] args) {
		if (args.length > 0 && args[0].equals("--profile=dev")) {
            new Menu(true);
        } else {
			new Menu(false);
		}
    }
}
