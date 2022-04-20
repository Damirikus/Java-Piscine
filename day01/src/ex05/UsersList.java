package ex05;

public interface UsersList {

    public void add(User user);
    public User getById(int id);
    public User getByIndex(int index);
    public int size();
}
