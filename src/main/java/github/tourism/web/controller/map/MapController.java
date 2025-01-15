package github.tourism.web.controller.map;

import github.tourism.data.entity.map.Map;
import github.tourism.service.map.MapService;
import github.tourism.web.dto.map.MapsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/maps")
public class MapController {

    private final MapService mapService;


    //전체 관광지 조회
    @GetMapping
    public ResponseEntity<PagedModel<MapsDTO>> getAllMaps(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size)
    {
        Page<MapsDTO> allMaps = mapService.getAllMaps(page, size);
        return ResponseEntity.ok(new PagedModel<>(allMaps));
    }

    //카테고리별 조회
//    @GetMapping("/{category}")
//    public ResponseEntity<PagedModel<MapsDTO>> getMapsByCategory(
//            @PathVariable String category, 
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        Page<MapsDTO> maps = mapService.getMapsByCategory(page, size, category);
//        return ResponseEntity.ok(new PagedModel<>(maps));
//    }
    
    //맵 상세조회
    @GetMapping("/details/{mapId}")
    public ResponseEntity<Map> getMapDetail(@PathVariable Integer mapId) {
        Map map = mapService.getMapDetail(mapId);
        return ResponseEntity.ok(map);
    }

}
