package markers.marker.control;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import markers.marker.exception.UserNotFoundException;

import markers.marker.model.reservations;
import markers.marker.repo.MarkerRepo;
import markers.marker.repo.reservationRepo;
@RestController
@RequestMapping("/marker")
public class reservationCont {
  @Autowired
  private MarkerRepo markerRepo;
  @Autowired
  private reservationRepo reservationRepo;
  @GetMapping("{markerId}/reservations")
  public ResponseEntity<List<reservations>> getAllreservationssBymarkerId(@PathVariable(value = "markerId") Long markerId) {
    if (!markerRepo.existsById(markerId)) {
      throw new UserNotFoundException("Not found marker with id = " + markerId);
    }
    List<reservations> reservationss = reservationRepo.findByMarkerId(markerId);
    return new ResponseEntity<>(reservationss, HttpStatus.OK);
  }
  @GetMapping("/reservations/{id}")
  public ResponseEntity<reservations> getreservationssBymarkerId(@PathVariable(value = "id") Long id) {
    reservations reservations = reservationRepo.findById(id)
        .orElseThrow(() -> new UserNotFoundException("Not found reservations with id = " + id));
    return new ResponseEntity<>(reservations, HttpStatus.OK);
  }
  @PostMapping("/{markerId}/reservations")
  public ResponseEntity<reservations> createreservations(@PathVariable(value = "markerId") Long markerId,
      @RequestBody reservations reservationsRequest) {
    reservations reservations = markerRepo.findById(markerId).map(marker -> {
      reservationsRequest.setMarker(marker);
      return reservationRepo.save(reservationsRequest);
    }).orElseThrow(() -> new UserNotFoundException("Not found marker with id = " + markerId));
    return new ResponseEntity<>(reservations, HttpStatus.CREATED);
  }

  @DeleteMapping("/delete/reservations/{id}")
  public ResponseEntity<?> deletereservation(@PathVariable("id") Long id) {
    reservationRepo.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  
}
