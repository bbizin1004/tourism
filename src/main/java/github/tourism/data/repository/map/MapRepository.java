package github.tourism.data.repository.map;

import github.tourism.data.entity.map.Map;
import github.tourism.web.dto.map.MapRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MapRepository extends JpaRepository<Map, Integer> {



    //카테고리별로 조회
    Page<Map> findByCategory(Pageable pageable, String category);

    Page<Map> findByMapId(Integer mapId, Pageable pageable);
}
