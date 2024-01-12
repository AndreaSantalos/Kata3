package Kata3;

import java.util.HashMap;

public interface GenderCounter {
    HashMap<String,Integer> totalgenderemployees = new HashMap<>();
    int femalecount();
    int malecount();
    void totalcount();
}