public class Program {
    public Program() {
    }

    public static void main(String[] args) {
        UserArrayList userList = new UserArrayList();
        System.out.println("Number of users: " + userList.size());
        System.out.println("Is user list empty? - " + userList.isEmpty());

        for(int i = 0; i < 10; ++i) {
            userList.add(new User("user_".concat(String.valueOf(i)), (i - 2) * 50));
        }

        System.out.println("Number of users: " + userList.size());
        System.out.println("Is user list empty? - " + userList.isEmpty());

        try {
            userList.add(-1, new User("Invalid_idx", 450));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index");
        }

        try {
            userList.add(userList.size(), new User("Invalid_idx", 600));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index");
        }

        try {
            userList.add(5, new User("NewUser_5", 5));
            userList.add(10, new User("NewUser_10", 1010));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index");
        }

        System.out.println("\nNumber of users: " + userList.size());
        System.out.println("Users by Index: ");

        for(int i = 0; i < userList.size(); ++i) {
            System.out.println(userList.get(i));
        }

        System.out.println("\nUsers by id: ");

        for(int i = 0; i < 16; ++i) {
            try {
                System.out.println(userList.getById(i));
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        System.out.println("\nNumber of users: " + userList.size());
        System.out.println("Delete some user by index:");

        for(int i = userList.size(); i >= 5; --i) {
            try {
                userList.remove(i);
            } catch (Exception e) {
                System.out.println("Invalid index");
            }
        }

        System.out.println("\nNumber of users: " + userList.size());

        for(int i = 0; i < userList.size(); ++i) {
            System.out.println(userList.get(i));
        }

        System.out.println("\nNumber of users: " + userList.size());
        System.out.println("Delete some user by user:");

        for(int i = 3; i >= 1; --i) {
            try {
                userList.remove(userList.get(i));
            } catch (Exception e) {
                System.out.println("Invalid index");
            }
        }

        System.out.println("\nNumber of index: " + userList.size());

        for(int i = 0; i < userList.size(); ++i) {
            System.out.println(userList.get(i));
        }

        System.out.println("\nNumber of users: " + userList.size());
        System.out.println("Get by ID: ");

        for(int i = 1; i < 6; ++i) {
            try {
                System.out.println(userList.getById(i));
            } catch (UserNotFoundException e) {
                System.out.println(e);
            }
        }

        System.out.println("\nClear all users:");
        userList.clear();
        System.out.println("Number of users: " + userList.size());
        System.out.println("Users by Index: ");

        for(int i = 0; i < userList.size(); ++i) {
            System.out.println(userList.get(i));
        }

    }
}
