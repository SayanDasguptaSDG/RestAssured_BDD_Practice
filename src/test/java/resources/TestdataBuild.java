package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestdataBuild {
    public AddPlace addPlacePayload() {
        AddPlace place = new AddPlace();

        List<String> types = new ArrayList<>();
        types.add("shoe");
        types.add("shoe park");

        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);

        place.setAccuracy(50);
        place.setAddress("29, side layout, cohen 09");
        place.setLanguage("French-IN");
        place.setPhone_number("(+91) 983 893 3937");
        place.setWebsite("www.google.com");
        place.setName("House of Death");
        place.setTypes(types);
        place.setLocation(location);
        return place;
    }
}
