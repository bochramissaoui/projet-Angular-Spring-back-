package markers.marker.model ;
import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Marker implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;

    private float lat;
    private float lng;
    private String information;

    private String domaine;
    private String imageUrl;
 
    @Column(nullable = false, updatable = false)
    private String markerCode;


    public Marker () {}

    public Marker(Long id, String name, float lat, float lng, String information, String domaine, String imageUrl, String markerCode) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.information = information;
        this.domaine = domaine;
        this.imageUrl = imageUrl;
        this.markerCode = markerCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMarkerCode() {
        return markerCode;
    }

    public void setMarkerCode(String markerCode) {
        this.markerCode = markerCode;
    }
}