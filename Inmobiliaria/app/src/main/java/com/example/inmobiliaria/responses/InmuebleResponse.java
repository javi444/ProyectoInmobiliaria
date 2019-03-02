package com.example.inmobiliaria.responses;

import com.example.inmobiliaria.models.Category;

import java.util.ArrayList;
import java.util.List;

public class InmuebleResponse {

    private String id;
    private UserResponse ownerId;
    private String title;
    private String description;
    private double price;
    private int rooms;
    private double size;
    private Category categoryId;
    private String address;
    private String zipcode;
    private String city;
    private String province;
    private String loc;
    private List<String> favs = new ArrayList<>();
    private List<String> photos = new ArrayList<>();

    public InmuebleResponse() {
    }

    public InmuebleResponse(String id, UserResponse ownerId, String title, String description, double price, int rooms, double size, Category categoryId, String address, String zipcode, String city, String province, String loc, List<String> favs, List<String> photos) {
        this.id = id;
        this.ownerId = ownerId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.rooms = rooms;
        this.size = size;
        this.categoryId = categoryId;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.province = province;
        this.loc = loc;
        this.favs = favs;
        this.photos = photos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserResponse getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UserResponse ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public List<String> getFavs() {
        return favs;
    }

    public void setFavs(List<String> favs) {
        this.favs = favs;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InmuebleResponse that = (InmuebleResponse) o;

        if (Double.compare(that.price, price) != 0) return false;
        if (rooms != that.rooms) return false;
        if (Double.compare(that.size, size) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (ownerId != null ? !ownerId.equals(that.ownerId) : that.ownerId != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null)
            return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (zipcode != null ? !zipcode.equals(that.zipcode) : that.zipcode != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (province != null ? !province.equals(that.province) : that.province != null)
            return false;
        if (loc != null ? !loc.equals(that.loc) : that.loc != null) return false;
        if (favs != null ? !favs.equals(that.favs) : that.favs != null) return false;
        return photos != null ? photos.equals(that.photos) : that.photos == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ownerId != null ? ownerId.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + rooms;
        temp = Double.doubleToLongBits(size);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (zipcode != null ? zipcode.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (loc != null ? loc.hashCode() : 0);
        result = 31 * result + (favs != null ? favs.hashCode() : 0);
        result = 31 * result + (photos != null ? photos.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InmuebleResponse{" +
                "id='" + id + '\'' +
                ", ownerId=" + ownerId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", rooms=" + rooms +
                ", size=" + size +
                ", categoryId=" + categoryId +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", loc='" + loc + '\'' +
                ", favs=" + favs +
                ", photos=" + photos +
                '}';
    }
}
