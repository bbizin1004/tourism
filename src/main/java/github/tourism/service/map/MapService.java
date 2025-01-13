package github.tourism.service.map;


import github.tourism.data.entity.map.Map;
import github.tourism.data.repository.map.MapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MapService {

    private final MapRepository mapRepository;

    public Page<Map> getAllMaps(Pageable pageable) {
        return mapRepository.findAll(pageable);
    }
}
