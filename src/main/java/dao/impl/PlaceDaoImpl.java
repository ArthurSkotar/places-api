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
        document.append("phto", place.getPhoto());
        document.append("phoneNUmber", place.getPhoneNumber());
        document.append("openingHours", place.getOpeningHours());
        document.append("siteUrl", place.getSiteUrl());
        document.append("priceLevel", place.getPriceLevel());
        document.append("internationalPhoneNumber", place.getInternationalPhoneNumber());
        MongoConnector.getInstance().getPlacesCollection().insertOne(document);
    }
}
