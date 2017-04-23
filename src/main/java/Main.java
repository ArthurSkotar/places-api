import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import domain.ResponseToPlaceMapper;
import resource.GooglePlacesResource;
import resource.impl.GooglePlacesResourceImpl;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException, ApiException, IOException {
        final LatLng location = new LatLng(49.85, 24.0166666667);
        final GooglePlacesResource resource = new GooglePlacesResourceImpl();
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final String response = gson.toJson(
                ResponseToPlaceMapper.mapResponseToPlace(
                        resource.listPlacesByTypeAndLocation(PlaceType.MUSEUM, location, 1).get(0)
                )
        );
        System.out.println(response);
    }
}
