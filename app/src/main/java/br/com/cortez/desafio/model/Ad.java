package br.com.cortez.desafio.model;


import com.google.gson.annotations.SerializedName;

public class Ad {

    @SerializedName("title")
    private String title;
    @SerializedName("price")
    private long price;
    @SerializedName("zipcode")
    private String zipcode;
    @SerializedName("seller")
    private String seller;
    @SerializedName("photos")
    private Photos photos;
    @SerializedName("date")
    private String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public String getFormattedPrice() {
        int leftPart = (int) (this.price / 100);
        int rightPart = (int) (this.price % 100);



        return "R$ " + leftPart +"," + String.format("%02d", rightPart);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ad)) return false;

        Ad ad = (Ad) o;

        if (getPrice() != ad.getPrice()) return false;
        if (getTitle() != null ? !getTitle().equals(ad.getTitle()) : ad.getTitle() != null)
            return false;
        if (getZipcode() != null ? !getZipcode().equals(ad.getZipcode()) : ad.getZipcode() != null)
            return false;
        if (getSeller() != null ? !getSeller().equals(ad.getSeller()) : ad.getSeller() != null)
            return false;
        if (getPhotos() != null ? !getPhotos().equals(ad.getPhotos()) : ad.getPhotos() != null)
            return false;
        return getDate() != null ? getDate().equals(ad.getDate()) : ad.getDate() == null;

    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + (int) (getPrice() ^ (getPrice() >>> 32));
        result = 31 * result + (getZipcode() != null ? getZipcode().hashCode() : 0);
        result = 31 * result + (getSeller() != null ? getSeller().hashCode() : 0);
        result = 31 * result + (getPhotos() != null ? getPhotos().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        return result;
    }
}
