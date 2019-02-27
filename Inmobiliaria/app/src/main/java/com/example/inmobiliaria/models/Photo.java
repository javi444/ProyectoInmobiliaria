package com.example.inmobiliaria.models;

public class Photo {

    private String id;
    private Inmueble propertyId;
    private String imgurLink;

    public Photo() {
    }

    public Photo(String id, Inmueble propertyId, String imgurLink) {
        this.id = id;
        this.propertyId = propertyId;
        this.imgurLink = imgurLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Inmueble getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Inmueble propertyId) {
        this.propertyId = propertyId;
    }

    public String getImgurLink() {
        return imgurLink;
    }

    public void setImgurLink(String imgurLink) {
        this.imgurLink = imgurLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (id != null ? !id.equals(photo.id) : photo.id != null) return false;
        if (propertyId != null ? !propertyId.equals(photo.propertyId) : photo.propertyId != null)
            return false;
        return imgurLink != null ? imgurLink.equals(photo.imgurLink) : photo.imgurLink == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (propertyId != null ? propertyId.hashCode() : 0);
        result = 31 * result + (imgurLink != null ? imgurLink.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", propertyId=" + propertyId +
                ", imgurLink='" + imgurLink + '\'' +
                '}';
    }
}
