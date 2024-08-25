package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestdataBuild {
    public AddPlace generateAddPlacePayload(String name, String language, String address) {
        AddPlace place = new AddPlace();

        List<String> types = new ArrayList<>();
        types.add("shoe");
        types.add("shoe park");

        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);

        place.setAccuracy(50);
        place.setAddress(address);
        place.setLanguage(language);
        place.setPhone_number("(+91) 983 893 3937");
        place.setWebsite("www.google.com");
        place.setName(name);
        place.setTypes(types);
        place.setLocation(location);
        return place;
    }

    public String generateDeletePlacePayload(String place_id) {
        return "{\r\n    \"place_id\": \"" + place_id + "\"\r\n}";
    }
}
