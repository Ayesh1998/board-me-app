package com.sliit.mad.boardme;

public class Properties {

    private String Title,Rooms,Bathrooms,Price,Images,ownerID;

    public Properties(){

    }

    public Properties(String title, String rooms, String bathrooms, String price, String images,String ownerIDs) {
        Title = title;
        Rooms = rooms;
        Bathrooms = bathrooms;
        Price = price;
        Images=images;
        ownerID = ownerIDs;
    }

    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
    }

    public String getOwnerid() {
        return ownerID;
    }

    public void setOwnerid(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getRooms() {
        return Rooms;
    }

    public void setRooms(String rooms) {
        Rooms = rooms;
    }

    public String getBathrooms() {
        return Bathrooms;
    }

    public void setBathrooms(String bathrooms) {
        Bathrooms = bathrooms;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
