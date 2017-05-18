import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.mongodb.Block;
import dao.PlaceDao;
import dao.impl.PlaceDaoImpl;
import domain.Place;
import domain.ResponseToPlaceMapper;
import org.bson.Document;
import resource.GooglePlacesResource;
import resource.impl.GooglePlacesResourceImpl;
import util.MongoConnector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) throws InterruptedException, ApiException, IOException {
//        final LatLng location = new LatLng(49.85, 24.0166666667);
//        final GooglePlacesResource resource = new GooglePlacesResourceImpl();
//        final PlaceDao dao = new PlaceDaoImpl();
//        resource.listPlacesByTypeAndLocation(PlaceType.ART_GALLERY, location, 200).stream().map(ResponseToPlaceMapper::mapResponseToPlace)
//                .forEach(dao::createPlace);
        List<Place> places = new ArrayList<>();
        MongoConnector.getInstance().getPlacesCollection().find().map(document -> {
            Place place = new Place();
            place.setPriceLevel(String.valueOf(document.getString("priceLevel")));
            place.setName(document.getString("name"));
            place.setAddress(document.getString("address"));
            place.setInternationalPhoneNumber(document.getString("internationalPhoneNumber"));
            place.setLocation(new LatLng(Double.valueOf(document.getString("location").split(",")[0]), Double.valueOf(document.getString("location").split(",")[1])));
            place.setRating(document.getDouble("rating"));
            place.setTypes((List<String>) document.get("types"));
            place.setVicinity(document.getString("vicinity"));
            place.setPhoneNumber(document.getString("phoneNumber"));
            place.setSiteUrl(document.getString("siteUrl"));
            place.setPriceLevel(document.getString("priceLevel"));
            place.setOpeningHours((List<String>) document.get("openingHours"));
            place.setInternationalPhoneNumber(document.getString("internationalPhoneNumber"));
            return place;
        }).forEach((Block<? super Place>) places::add);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(places));
    }
}
