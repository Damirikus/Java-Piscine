package ex04;

public class UsersArrayList implements UsersList {

    private User[] users;
    private int size;

    public UsersArrayList() {
        size = 0;
        users = new User[10];
    }

    private void increaseCapacity(){
        User[] temp = new User[users.length + (users.length / 2)];
        for (int i = 0; i < size; i ++){
            temp[i] = users[i];
        }
        users = temp;
    }

    @Override
    public void add(User user) {
        if (size == users.length){
            increaseCapacity();
        }
        users[size] = user;
        size++;
    }

    @Override
    public User getById(int id) {
        for (int i = 0; i < size; i++){
            if (users[i].getId() == id){
                return users[i];
            }
        }
        throw new UserNotFoundException("User not found!");
    }

    @Override
    public User getByIndex(int index) {
        return users[index];
    }

    @Override
    public int size() {
        return size;
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message){
            super(message);
        }
    }

}

