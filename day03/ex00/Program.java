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
			System.exit(-1);
		}
        Thread threadHen = new Thread(new Hen());
        Thread threadEgg = new Thread(new Egg());
        
		threadHen.start();
        threadEgg.start();
		threadHen.join();
		threadEgg.join();
        printName("Human");
    }

    public static void printName(String name) {
        for (int i = 0; i < count; ++i) {
            System.out.println(name);
        }
    }

    private static class Hen implements Runnable {
        @Override
        public void run() {
            printName("Hen");
        }
    }

	private static class Egg implements Runnable {
       @Override
       public void run() {
           printName("Egg");
       }
   }
}
