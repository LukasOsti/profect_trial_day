package profect.springbackend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class StationHandler {

    static String url =
            "http://localhost:10000";



    public static List<String> getStationsFromApi(){
        var list = new ArrayList<String>();

        try {
            var uri = URI.create(url + "/haltestellen");
            HttpClient client = HttpClient.newHttpClient();
            var request = HttpRequest
                    .newBuilder()
                    .uri(uri)
                    .header("accept", "application/json")
                    .GET()
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            var responseBody = response.body();
            JSONObject obj = new JSONObject(responseBody);
            JSONArray arr = obj.getJSONArray("names"); // notice that `"posts": [...]`
            for (int i = 0; i < arr.length(); i++)
            {
                String name = arr.getString(i);
                list.add(name);
            }


        } catch (Exception e){
            System.out.println(e);
        }

        return list.stream().sorted().toList();
    }

    public static List<String> getBusLinesFromApi(String station){
        var list = new ArrayList<String>();

        try {
            var uri = URI.create(url + "/abfahrten/" + station);
            HttpClient client = HttpClient.newHttpClient();
            var request = HttpRequest
                    .newBuilder()
                    .uri(uri)
                    .header("accept", "application/json")
                    .GET()
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            var responseBody = response.body();
            System.out.println(responseBody);
            var arr = new JSONArray(responseBody);// notice that `"posts": [...]`
            for (int i = 0; i < arr.length(); i++)
            {
                String name = arr.getJSONObject(i).getString("name");
                list.add(name);
          }

        } catch (Exception e){
            System.out.println(e);
        }

        return list.stream().sorted().toList();
    }

    public static List<String> getTimingsFromApi(String station, String busLine) {
        var list = new ArrayList<String>();

        try {
            var uri = URI.create(url + "/abfahrten/" + station);
            HttpClient client = HttpClient.newHttpClient();
            var request = HttpRequest
                    .newBuilder()
                    .uri(uri)
                    .header("accept", "application/json")
                    .GET()
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            var responseBody = response.body();
            System.out.println(responseBody);
            var arr = new JSONArray(responseBody);
            for (int i = 0; i < arr.length(); i++)
            {
                String name = arr.getJSONObject(i).getString("name");
                if (name.equals(busLine)){
                    var arrZeiten = arr.getJSONObject(i).getJSONArray("zeiten");
                    for (int j = 0; j < arrZeiten.length(); i++)
                    {
                        list.add(arrZeiten.getString(j));
                    }
                }
            }

        } catch (Exception e){
            System.out.println(e);
        }

        return list.stream().sorted().toList();

    }
}
