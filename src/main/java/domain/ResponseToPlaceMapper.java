package domain;

import com.google.maps.model.OpeningHours;
import com.google.maps.model.Photo;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PriceLevel;
import org.joda.time.LocalTime;

import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by arthur on 23.04.17.
 */
public final class ResponseToPlaceMapper {

    private ResponseToPlaceMapper() {}

    public static Place mapResponseToPlace(final PlaceDetails details) {
        final Place place = new Place();
        if (details != null) {
            place.setAddress(Optional.ofNullable(details.formattedAddress).orElse(""));
            place.setInternationalPhoneNumber(Optional.ofNullable(details.internationalPhoneNumber).orElse(""));
            place.setLocation(details.geometry.location);
            place.setName(details.name);
            place.setPhoneNumber(Optional.ofNullable(details.formattedPhoneNumber).orElse(""));
            place.setRating(details.rating);
            place.setTypes(Arrays.asList(details.types));
            place.setUrl(details.url.toString());
            place.setVicinity(details.vicinity);
            place.setPlaceGoogleId(details.placeId);
            place.setPhoto(Optional.ofNullable(details.photos).orElse(new Photo[]{new Photo()})[0].photoReference);
            place.setPriceLevel(Optional.ofNullable(details.priceLevel).orElse(PriceLevel.UNKNOWN).toString());
            try {
                place.setSiteUrl(Optional.ofNullable(details.website).orElse(new URL("")).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            place.setOpeningHours(Arrays.stream(
                    Optional.ofNullable(
                            Optional.ofNullable(details.openingHours)
                                    .orElse(new OpeningHours()).periods)
                            .orElse(new OpeningHours.Period[]{}))
                    .filter(op -> op.open != null)
                    .filter(op -> op.open.day != null)
                    .map(op -> op.open.day.toString() + " " + Optional.ofNullable(op.open.time).orElse(new LocalTime()) + " - " +
                            Optional.ofNullable(Optional.ofNullable(op.close).orElse(new OpeningHours.Period.OpenClose()).time)
                                    .orElse(new LocalTime()))
                    .map(Object::toString).collect(Collectors.toList()));
        }
        return place;
    }
}
