package github.tourism.service.map;

import github.tourism.data.entity.map.Map;
import github.tourism.data.repository.map.MapRepository;
import github.tourism.web.dto.map.MapsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MapService {

    private final MapRepository mapRepository;

    public Page<MapsDTO> getAllMaps(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Map> maps = mapRepository.findAll(pageable);
        Page<MapsDTO> mapPage = maps.map(MapsDTO::new);

        return mapPage;

    }


    //카테고리별로 맵 조회
    public Page<Map> getMapsByCategory(int page, int size, String category) {
        Pageable pageable = PageRequest.of(page, size);
        return mapRepository.findByCategory(pageable, category);
    }
}
