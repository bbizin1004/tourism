package github.tourism.web.controller.map;

import github.tourism.data.entity.map.Map;
import github.tourism.service.map.MapService;
import github.tourism.web.dto.map.MapsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/maps")
public class MapController {

    private final MapService mapService;

    //전체 관광지 조회
//    @GetMapping
//    public ResponseEntity<PagedModel<MapsDTO>> getAllMaps(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size)
//    {
//        Page<Map> maps = mapService.getAllMaps(page, size);
//        PagedModel<MapsDTO> mapsDTO = new PagedModel<>(maps.map(MapsDTO::new));
//        return ResponseEntity.ok(new PagedModel<>())
//    }



}
