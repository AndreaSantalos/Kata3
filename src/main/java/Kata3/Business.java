package Kata3;

import java.io.FileNotFoundException;

public class Business {
    public static void main(String[] args) throws FileNotFoundException {
        Employee a = new Employee("1","director","female");
        a.csvread();
        a.showemployeesgender();



    }
}
