package github.tourism.web.controller.map;

import github.tourism.data.entity.map.Map;
import github.tourism.service.map.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/maps")
public class MapController {

    private final MapService mapService;

    //전체 관광지 조회
//    @GetMapping
//    public Map getMapById(@PathVariable Long id) {
//        return mapService.getMapById(id);
//    }



}
