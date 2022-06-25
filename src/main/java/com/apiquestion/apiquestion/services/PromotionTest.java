package com.apiquestion.apiquestion.services;

public class PromotionTest {
    public static void main(String args[]){
        int i = 5;
        var f = 5.5f;
        double d = 3.8;
        var c = 'a';
        if (i == f) c++;
        if (((int) (f + d)) == ((int) f + (int) d)) c += 2;
        System.out.println(c);
    }
}
