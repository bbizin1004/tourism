package github.tourism.data.repository.map;

import github.tourism.data.entity.map.Map;
import github.tourism.web.dto.map.MapRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MapRepository extends JpaRepository<Map, Integer> {

//    @Query("select from Map m ")
    List<MapRequestDTO> findMapRequestDto();
}
