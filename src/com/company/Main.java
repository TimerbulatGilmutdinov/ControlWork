package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CocktailDB cocktailDB = new CocktailDB();
        FileCreator fileCreator = new FileCreator();
        Scanner sc = new Scanner(System.in);
        System.out.println("Input a cocktail name");
        String input = sc.nextLine();
        cocktailDB.setCocktailName(input);
        cocktailDB.setCocktailURL(input);
        System.out.println(cocktailDB.getPhotoDownloadingURL());
        fileCreator.downloadPhoto();
        fileCreator.writeINI();
        cocktailDB.isURLValid();
    }
}
