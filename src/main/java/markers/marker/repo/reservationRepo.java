package markers.marker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import markers.marker.model.reservations;
@Repository
public interface reservationRepo extends JpaRepository<reservations, Long> {
  List<reservations> findByMarkerId(Long markerId);
 
 
}
