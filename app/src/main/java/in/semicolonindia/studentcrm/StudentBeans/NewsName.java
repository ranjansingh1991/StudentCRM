package in.semicolonindia.studentcrm.StudentBeans;

/**
 * Created by RANJAN SINGH on 11/30/2017.
 */

public class NewsName {
    private int sImages;
    private String sDate;
    private String sTime;
    private String sAdvertisement;
    private String sDes;

    public NewsName(int sImages, String sDate, String sTime, String sAdvertisement, String sDes) {
        this.sImages = sImages;
        this.sDate = sDate;
        this.sTime = sTime;
        this.sAdvertisement = sAdvertisement;
        this.sDes = sDes;
    }

    public int getsImages() {
        return sImages;
    }

    public String getsDate() {
        return sDate;
    }

    public String getsTime() {
        return sTime;
    }

    public String getsAdvertisement() {
        return sAdvertisement;
    }

    public String getsDes() {
        return sDes;
    }
}