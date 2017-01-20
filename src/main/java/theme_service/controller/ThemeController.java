package theme_service.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author      József Balogh
 * @version     1.0
 */
public class ThemeController {
    /**
     * <p>The method checks the current date, by the month, decides the actual season, selects two links,
     * one is a bootswatch ( a free to use online-imported bootstrap theme), another is a picture link.
     * In the end puts them into a hashmap. </p>
     * @param request
     * @param response
     * @return Returns a Json file, containing the 2 links, using gsonbuilder.
     */
    public String getTheme(Request request, Response response) {
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int actual = 3; // 11, 0, 1 --> Winter:0;   2, 3, 4 --> Spring:1;   5, 6, 7 --> Summer:2;   8, 9, 10 --> Autumn:3
        if ( month == 11 || month == 0 || month == 1 ){
            actual = 0;
        }
        else if (month>=2 && month<=4){
            actual = 1;
        }
        else if (month>=5 && month<=7){
            actual = 2;
        }
        else {
            actual = 3;
        }

        List<String> links = new ArrayList<>();
        links.add("https://bootswatch.com/superhero/bootstrap.min.css");
        links.add("https://bootswatch.com/lumen/bootstrap.min.css");
        links.add("https://bootswatch.com/cerulean/bootstrap.min.css");
        links.add("https://bootswatch.com/united/bootstrap.min.css");

        List<String> backgrounds = new ArrayList<>();
        backgrounds.add("https://goo.gl/6Gtx5N");
        backgrounds.add("https://goo.gl/t7RhQK");
        backgrounds.add("https://goo.gl/NXhvph");
        backgrounds.add("https://goo.gl/NpcYaY");

        HashMap result = new HashMap();
        result.put("stylesheet", links.get(actual));
        result.put("background-image", backgrounds.get(actual));

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(result);
    }
}

