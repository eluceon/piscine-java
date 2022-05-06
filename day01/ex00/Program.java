public class Program {
    public static void main(String[] args) {
        User john = new User("John", 1000);
        User mike = new User("Mike", 5000);
        User eluceon = new User("Eluceon", 7777);
        User dima = new User("Dima", 99999);

        System.out.println(john);
        System.out.println(mike);
        System.out.println(eluceon);
        System.out.println(dima);

        System.out.println();

        Transaction transaction1 = new Transaction(john, mike, Transaction.TransferCategory.INCOME , 300);
        Transaction transaction2 = new Transaction(mike, eluceon, Transaction.TransferCategory.OUTCOME , -3000);
        Transaction transaction3 = new Transaction(john, dima, Transaction.TransferCategory.INCOME , -10);
        Transaction transaction4 = new Transaction(dima, mike, Transaction.TransferCategory.OUTCOME , -300000);

        System.out.println(transaction1);
        System.out.println(transaction2);
        System.out.println(transaction3);
        System.out.println(transaction4);

        System.out.println();

        System.out.println(john);
        System.out.println(mike);
        System.out.println(eluceon);
        System.out.println(dima);
    }
}
