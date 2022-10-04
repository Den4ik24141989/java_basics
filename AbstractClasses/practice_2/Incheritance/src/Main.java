package Incheritance.src;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        company.setHireAll(10, "Менеджер");
        company.fire("Менеджер3");
        company.setHire("Топ-Менеджер");
        company.getListOfEmployee();

    }
}
