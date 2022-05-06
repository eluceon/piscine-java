public class Program {
	private static final String FLAG = "--count=";
	private static int count;
    public static void main(String[] args) throws InterruptedException {
		if (args.length != 1 || !args[0].startsWith(FLAG)) {
			System.out.println("Usage: Program --count=NUMBER_OF_RUNS");
			System.exit(0);
		}
		try {
			count = Integer.parseInt(args[0].substring(FLAG.length()));
		} catch (NumberFormatException exc) {
			System.err.println(exc.getMessage());
			System.exit(1);
		}
        Printer printer = new Printer();
        Thread threadHen = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; ++i) {
                    printer.printHen();
                }
            }
        });
        Thread threadEgg = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; ++i) {
                    printer.printEgg();
                }
            }
        });
        threadHen.start();
        threadEgg.start();
    }
}
