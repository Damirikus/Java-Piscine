package ex02;

public class Program {
    public static void main(String[] args) {

        UsersArrayList list = new UsersArrayList();

        User user1 = new User("Bob", 1000);
        User user2 = new User("Jack", 700);
        User user3 = new User("Bomj", 100);

        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user1);

        System.out.println("Size is - " + list.size());
        System.out.println(list.getByIndex(1));
        System.out.println(list.getById(3));

        list.add(user1);
        list.add(user1);
        list.add(user1);
        list.add(user1);
        list.add(user1);
        list.add(user1);


        System.out.println("Size is - " + list.size());
        System.out.println(list.getByIndex(9));
        System.out.println(list.getById(3));

        list.add(user1);
        list.add(user1);
        list.add(user1);

        System.out.println("Size is - " + list.size());
        System.out.println(list.getByIndex(13));
        System.out.println(list.getById(5));

    }
}
