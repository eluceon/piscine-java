public class Program {
	public static void main(String[] args) {
		int nbr;
		int	res;

		nbr = 479598;
		res = nbr % 10;
		nbr /= 10;
		res += nbr % 10;
		nbr /= 10;
		res += nbr % 10;
		nbr /= 10;
		res += nbr % 10;
		nbr /= 10;
		res += nbr % 10;
		nbr /= 10;
		res += nbr % 10;
		System.out.println(res);
	}
}
