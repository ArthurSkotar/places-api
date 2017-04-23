package service.impl;

import com.google.maps.model.PlaceType;
import dao.PlaceDao;
import dao.impl.PlaceDaoImpl;
import domain.Place;
import service.PlaceService;

import java.util.List;

/**
 * Created by arthur on 23.04.17.
 */
public class PlaceServiceImpl implements PlaceService {

    private  PlaceDao placeDao = new PlaceDaoImpl();

    @Override
    public List<Place> listPlacesByType(final PlaceType type) {
        return null;
    }

    @Override
    public void savePlacesInDb(List<Place> places) {

    }


}
