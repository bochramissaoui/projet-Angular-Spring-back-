package markers.marker.control;

import markers.marker.exception.UserNotFoundException;
import markers.marker.repo.MarkerRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import markers.marker.model.Marker;

import markers.marker.service.MarkerService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/marker")
public class MarkerCont {
    private final MarkerService MarkerService;
    private final MarkerRepo MarkerRepo;
    public MarkerCont(MarkerService markerService , MarkerRepo MarkerRepo) {
        this.MarkerRepo=MarkerRepo;
        this.MarkerService = markerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Marker>> getAllMarkers () {
        List<Marker> markers = MarkerService.findAllMarkers();
        return new ResponseEntity<>(markers, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Marker>> getMarkerById (@PathVariable("id") long id) {
        Optional<Marker> marker= MarkerService.findMarkerByid(id);
        return new ResponseEntity<>(marker, HttpStatus.OK);
    }
    

    @PostMapping("/add")
    public ResponseEntity<Marker> addMarker (@RequestBody Marker marker) {
        Marker newmarker = MarkerService.addMarker(marker);
        return new ResponseEntity<>(newmarker, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Marker> updateMarker(@PathVariable Long id, @RequestBody Marker markerDetails){
        Marker marker = MarkerRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Marker not exist with id :" + id));

        marker.setName(markerDetails.getName());
        marker.setLat(markerDetails.getLat());
        marker.setLng(markerDetails.getLng());
        marker.setInformation(markerDetails.getInformation());
        marker.setImageUrl(markerDetails.getImageUrl());
        marker.setDomaine(markerDetails.getDomaine());

        Marker updatedMarker = MarkerRepo.save(marker);
        return ResponseEntity.ok(updatedMarker);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMarker(@PathVariable("id") Long id) {
        MarkerService.deleteMarker(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
