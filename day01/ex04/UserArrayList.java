public class UserArrayList implements UserList {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    
    private int size;
    private User[] array;

    public UserArrayList() {
        size = 0;
        array = new User[DEFAULT_ARRAY_SIZE];
    }

    public boolean add(User user) {
        if (size >= array.length) {
            User[] biggerArray = new User[size * 2];
            for(int i = 0; i < size; ++i) {
                biggerArray[i] = array[i];
            }
            array = biggerArray;
        }
        array[size] = user;
        ++size;
        return true;
    }

    public void add(int index, User user) {
        if (index >= 0 && index < size) {
            if (size >= array.length) {
                User[] biggerArray = new User[size * 2];

                for(int i = 0; i < size; ++i) {
                    biggerArray[i] = array[i];
                }

                array = biggerArray;
            }

            for(int i = size; i > index; --i) {
                array[i] = array[i - 1];
            }

            array[index] = user;
            ++size;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void clear() {
        size = 0;
    }

    public boolean contains(User user) {
        return indexOf(user) != -1;
    }

    public User get(int index) {
        if (index >= 0 && index < size) {
            return array[index];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public User getById(Integer userId) {
        for(int i = 0; i < size; ++i) {
            if (array[i].getUserId().equals(userId)) {
                return array[i];
            }
        }

        throw new UserNotFoundException("User not found!");
    }

    public int indexOf(User target) {
        for(int i = 0; i < size; ++i) {
            if (equals(target, array[i])) {
                return i;
            }
        }
        return -1;
    }

    private boolean equals(User target, User user) {
        if (target == null) {
            return user == null;
        } else {
            return target.equals(user);
        }
    }

    public int lastIndexOf(User user) {
        for(int i = size - 1; i >= 0; --i) {
            if (equals(user, array[i])) {
                return i;
            }
        }

        return -1;
    }

    public User remove(int index) {
        User removedUser = get(index);

        for(int i = index + 1; i < size; ++i) {
            array[i - 1] = array[i];
        }

        --size;
        return removedUser;
    }

    public boolean remove(User user) {
        int index = indexOf(user);
        if (index == -1) {
            return false;
        } else {
            remove(index);
            return true;
        }
    }

    public User set(int index, User user) {
        User oldUser = get(index);
        array[index] = user;
        return oldUser;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
