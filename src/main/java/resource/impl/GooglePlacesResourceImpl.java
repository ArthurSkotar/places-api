package resource.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import resource.GooglePlacesResource;
import util.PropertyReaderUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by arthur on 23.04.17.
 */
public class GooglePlacesResourceImpl implements GooglePlacesResource {

    private static final int RADIUS = 50000;
    private static final String LANGUAGE = "uk";
    private final GeoApiContext context;

    public GooglePlacesResourceImpl() {
        this.context = new GeoApiContext().setApiKey(PropertyReaderUtil.getInstance().getGoogleApiKey());
    }

    @Override
    public List<PlaceDetails> listPlacesByTypeAndLocation(final PlaceType type, final LatLng latLng, final int limit)
            throws InterruptedException, ApiException, IOException {
        final PlacesSearchResponse response = PlacesApi.radarSearchQuery(context, latLng, RADIUS)
                .location(latLng)
                .type(type)
                .language(LANGUAGE)
                .await();
        final List<PlacesSearchResult> results = Arrays.asList(response.results);
        return results.stream()
                .limit(limit)
                .map(result -> PlacesApi.placeDetails(context, result.placeId)
                .awaitIgnoreError())
                .collect(Collectors.toList());
    }
}
