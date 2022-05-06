public interface UserList {
    boolean add(User user);

    void add(int index, User user);

    void clear();

    boolean contains(User user);

    User get(int index);

    User getById(Integer userId);

    int indexOf(User user);

    int lastIndexOf(User user);

    User remove(int index);

    boolean remove(User user);

    User set(int index, User user);

    boolean isEmpty();

    int size();
}