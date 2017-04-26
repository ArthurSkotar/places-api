import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import dao.PlaceDao;
import dao.impl.PlaceDaoImpl;
import domain.ResponseToPlaceMapper;
import resource.GooglePlacesResource;
import resource.impl.GooglePlacesResourceImpl;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException, ApiException, IOException {
        final LatLng location = new LatLng(49.85, 24.0166666667);
        final GooglePlacesResource resource = new GooglePlacesResourceImpl();
        final PlaceDao dao = new PlaceDaoImpl();
        resource.listPlacesByTypeAndLocation(PlaceType.MUSEUM, location, 20).stream().map(ResponseToPlaceMapper::mapResponseToPlace)
                .forEach(dao::createPlace);
    }
}
