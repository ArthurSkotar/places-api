import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException, ApiException, IOException {
        final GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyDHSISC371SwDeDT-tjN7p9pmoEa5x5T3M");
        LatLng location = new LatLng(49.85, 24.0166666667);
        PlacesSearchResponse response = PlacesApi.radarSearchQuery(context, location, 50000)
                .location(location)
                .type(PlaceType.MUSEUM)
                .language("uk")
                .await();
        List<PlacesSearchResult> results = Arrays.asList(response.results);
        results.stream().limit(10).map(result -> PlacesApi.placeDetails(context, result.placeId)
                .awaitIgnoreError())
                .map(r -> {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    return gson.toJson(r);
                })
                .forEach(System.out::println);
    }
}
