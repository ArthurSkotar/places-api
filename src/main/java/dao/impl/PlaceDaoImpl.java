package dao.impl;

import dao.PlaceDao;
import domain.Place;
import org.bson.Document;
import util.MongoConnector;

/**
 * Created by arthur on 23.04.17.
 */
public class PlaceDaoImpl implements PlaceDao {

    @Override
    public void createPlace(Place place) {
        final Document document = new Document();
        document.append("address", place.getAddress());
        document.append("name", place.getName());
        document.append("location", place.getLocation().toString());
        document.append("rating", place.getRating());
        document.append("types", place.getTypes());
        document.append("vicinity", place.getVicinity());
        MongoConnector.getInstance().getPlacesCollection().insertOne(document);
    }
}
