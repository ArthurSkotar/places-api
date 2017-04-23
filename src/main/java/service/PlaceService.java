package service;

import com.google.maps.model.PlaceType;
import domain.Place;

import java.util.List;

/**
 * Created by arthur on 23.04.17.
 */
public interface PlaceService {

    List<Place> listPlacesByType(final PlaceType type);
    void savePlacesInDb(final List<Place> places);

}
