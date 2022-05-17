package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CocktailDB {
    private String url;
    private String json;
    private String cocktailName;

    public void setCocktailName(String cocktailName) {
        this.cocktailName = cocktailName;
    }

    public String getCocktailName() {
        return cocktailName;
    }

    public String getJson() {
        return json;
    }

    public void setCocktailURL(String cocktailName) {
        url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=" + cocktailName;
    }

    public String getCocktailURL() {
        return url;
    }

    public boolean isURLValid(){
        try {
            URL url = new URL(getCocktailURL());
        } catch (MalformedURLException e) {
            System.out.println("invalid cocktail name");;
        }
        return false;//не успел доделать валидность названия коктейля поэтому false
    }
    public String getPhotoDownloadingURL() {
        if(isURLValid()) {
            ArrayList<String> list = new ArrayList<>();
            URL url = null;
            try {
                url = new URL(getCocktailURL());
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
                String line;
                while ((line = in.readLine()) != null) {
                    json = line;
                }
                String regex = "\"strDrinkThumb\":\".+?\"";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(getJson());
                while (matcher.find()) {
                    list.add(json.substring(matcher.start(), matcher.end()));
                }
            } catch (MalformedURLException e) {
                System.out.println("invalid url");
            } catch (IOException e) {
                System.out.println("unexpected exception,try again");
            }
            return list.get(0).substring(17, list.get(0).length() - 1);
        }
        return "";
    }
}
