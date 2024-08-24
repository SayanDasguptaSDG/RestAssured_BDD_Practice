package resources;

public enum Resources {
    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/delete/json");
    private final String resource;

    Resources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
