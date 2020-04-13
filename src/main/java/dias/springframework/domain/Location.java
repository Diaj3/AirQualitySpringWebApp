package dias.springframework.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Location {

    private @Id @GeneratedValue Integer id;

    private Double aqi;
    private String name;
    private Double latitude ;
    private Double longitude;
    private Double o3;
    private Double no2;
    private Double p;
    private Double pm25;
    private Double pm10;
    private Double so2;
    private String timezone;
    private String time;

    public Location() {
        super();
    }

    public Location(Integer id, Double aqi, String name, Double latitude, Double longitude, Double o3, Double p, Double so2, Double pm10, Double pm25, String timezone, String time){
        this.id = id;
        this.aqi = aqi;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.o3 = o3;
        this.p = p;
        this.so2 = so2;
        this.pm10 = pm10;
        this.pm25 = pm25;
        this.timezone = timezone;
        this.time = time;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Double getAqi(){
        return aqi;
    }

    public void setAqi(Double aqi){
        this.aqi = aqi;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude){
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude){
        this.longitude = longitude;
    }

    public Double getO3 (){
        return o3;
    }

    public void setO3(Double o3) {
        this.o3 = o3;
    }

    public Double getNo2() {
        return no2;
    }

    public void setNo2(Double no2) {
        this.no2 = no2;
    }

    public Double getP() {
        return p;
    }

    public void setP(Double p) {
        this.p = p;
    }

    public Double getSo2() {
        return so2;
    }

    public void setSo2(Double so2) {
        this.so2 = so2;
    }

    public Double getPm25() {
        return pm25;
    }

    public void setPm25(Double pm25) {
        this.pm25 = pm25;
    }

    public Double getPm10() {
        return pm10;
    }

    public void setPm10(Double pm10) {this.pm10 = pm10;}

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Version
    private Integer version;

    private String productId;
    private String description;
    private String imageUrl;
    private BigDecimal price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
