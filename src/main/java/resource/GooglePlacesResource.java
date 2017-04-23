package resource;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlaceType;

import java.io.IOException;
import java.util.List;

/**
 * Created by arthur on 23.04.17.
 */
public interface GooglePlacesResource {

    List<PlaceDetails> listPlacesByTypeAndLocation(final PlaceType type, final LatLng latLng, final int limit)
            throws InterruptedException, ApiException, IOException;
}
