package domain;

import com.google.maps.model.PlaceDetails;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by arthur on 23.04.17.
 */
public final class ResponseToPlaceMapper {

    private ResponseToPlaceMapper() {}

    public static Place mapResponseToPlace(final PlaceDetails details) {
        final Place place = new Place();
        place.setAddress(details.formattedAddress);
        place.setInternationalPhoneNumber(details.internationalPhoneNumber);
        place.setLocation(details.geometry.location);
        place.setName(details.name);
        place.setPhoneNumber(details.formattedPhoneNumber);
        place.setRating(details.rating);
        place.setTypes(Arrays.asList(details.types));
        place.setUrl(details.url.toString());
        place.setVicinity(details.vicinity);
        place.setPlaceGoogleId(details.placeId);
//        place.setSiteUrl(Optional.ofNullable(details.website).toString());
//        place.setOpeningHors(Arrays.asList(details.openingHours.periods));
        return place;
    }
}
