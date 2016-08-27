
package br.com.cortez.desafio.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Photos implements Serializable {

    @SerializedName("thumbnailHd")
    private String thumbnailHd;
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("detailHd")
    private String detailHd;
    @SerializedName("detail")
    private String detail;

    public String getThumbnailHd() {
        return thumbnailHd;
    }

    public void setThumbnailHd(String thumbnailHd) {
        this.thumbnailHd = thumbnailHd;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDetailHd() {
        return detailHd;
    }

    public void setDetailHd(String detailHd) {
        this.detailHd = detailHd;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photos)) return false;

        Photos photos = (Photos) o;

        if (getThumbnailHd() != null ? !getThumbnailHd().equals(photos.getThumbnailHd()) : photos.getThumbnailHd() != null)
            return false;
        if (getThumbnail() != null ? !getThumbnail().equals(photos.getThumbnail()) : photos.getThumbnail() != null)
            return false;
        if (getDetailHd() != null ? !getDetailHd().equals(photos.getDetailHd()) : photos.getDetailHd() != null)
            return false;
        return getDetail() != null ? getDetail().equals(photos.getDetail()) : photos.getDetail() == null;

    }

    @Override
    public int hashCode() {
        int result = getThumbnailHd() != null ? getThumbnailHd().hashCode() : 0;
        result = 31 * result + (getThumbnail() != null ? getThumbnail().hashCode() : 0);
        result = 31 * result + (getDetailHd() != null ? getDetailHd().hashCode() : 0);
        result = 31 * result + (getDetail() != null ? getDetail().hashCode() : 0);
        return result;
    }
}
