package markers.marker.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import markers.marker.model.Marker;

import markers.marker.repo.MarkerRepo;
@Service
public class MarkerService {
      
    @Autowired
    private MarkerRepo MarkerRepo;
     
    public List<Marker> findAllMarkers() {
        return MarkerRepo.findAll();
    }
     

    public MarkerService(MarkerRepo MarkerRepo) {
        this.MarkerRepo = MarkerRepo;
    }

    public Marker addMarker(Marker Marker) {
        Marker.setMarkerCode(UUID.randomUUID().toString());
        return MarkerRepo.save(Marker);
    }
 
    
    public void deleteMarker(Long id) {
        MarkerRepo.deleteById(id);
    }

    public Optional<Marker> findMarkerByid(long id) {
        return MarkerRepo.findById(id) ;
    }

  

   

}
