package com.company;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileCreator extends CocktailDB {
    public void downloadPhoto() {
        if(isURLValid()) {
            BufferedInputStream in;
            FileOutputStream fout;
            try {
                in = new BufferedInputStream(new URL(getPhotoDownloadingURL()).openStream());
                fout = new FileOutputStream(getCocktailName()+".jpg");
                byte data[] = new byte[1024];
                int count;
                while ((count = in.read(data)) != -1) {
                    fout.write(data, 0, count);
                }
                fout.close();
                in.close();
            } catch (IOException e) {
                System.out.println("couldn't download an image");
            }
        }
    }
    public void writeINI() {
        if(isURLValid()) {
            File file = new File("cocktailInfo.ini");
            try {
                FileWriter fileWriter = new FileWriter(file,true);
                String regex = "\"strIngridient1\":\".+?\"";
                String regex2 = "\"strIngridient2\":\".+?\"";
                String regex3 = "\"strIngridient3\":\".+?\"";
                String regex4 = "\"strIngridient4\":\".+?\"";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(getJson());
                ArrayList<String> list = new ArrayList<>();
                list.add(getJson().substring(matcher.start(), matcher.end()));
                fileWriter.write(list.get(0));
                pattern = Pattern.compile(regex2);
                matcher = pattern.matcher(getJson());
                list.add(getJson().substring(matcher.start(), matcher.end()));
                fileWriter.write(list.get(1));
                pattern = Pattern.compile(regex3);
                matcher = pattern.matcher(getJson());
                list.add(getJson().substring(matcher.start(), matcher.end()));
                fileWriter.write(list.get(2));
                pattern = Pattern.compile(regex4);
                matcher = pattern.matcher(getJson());
                list.add(getJson().substring(matcher.start(), matcher.end()));
                fileWriter.write(list.get(3));
            } catch (IOException e) {
                System.out.println("problems with writing ingridients");;
            }
        }
    }
}
