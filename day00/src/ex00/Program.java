package ex00;

public class Program {
    public static void main(String[] args) {
        int a = 479598;
        int res = 0;
        for (int i = 0; i < 6; i++){
            int t = a % 10;
            res += t;
            a /= 10;
        }
        System.out.println(res);
    }
}
