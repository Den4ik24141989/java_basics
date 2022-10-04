package Incheritance.src;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> employees = new ArrayList<>();
        for (int i = 1; i <=5; i++) {
            employees.add("Менеджер" + i);
        }
        for (String e : employees) {
            System.out.println(e);
        }

    }
}
