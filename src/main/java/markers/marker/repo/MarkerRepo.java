package markers.marker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import markers.marker.model.Marker;



@Repository
public interface MarkerRepo extends JpaRepository<Marker,Long> {


}
