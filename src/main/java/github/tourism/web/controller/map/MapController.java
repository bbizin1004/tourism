package github.tourism.web.controller.map;

import github.tourism.service.map.MapService;
import github.tourism.web.dto.map.MapDetailsDTO;
import github.tourism.web.dto.map.MapsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    @GetMapping("/{mapId}")
    public ResponseEntity<MapDetailsDTO> getMapDetail(@PathVariable Integer mapId,
                                                      @AuthenticationPrincipal UserDetails user) {
        Integer userId = null;
        if(user != null){
             userId = Integer.valueOf(user.getUsername());
        }

        MapDetailsDTO mapDetails = mapService.getMapDetail(mapId,userId);
        return ResponseEntity.ok(mapDetails);
    }


    //찜 저장하기
//    @PreAuthorize("isAuthenticated()") //로그인 체크
//    @PostMapping("/favPlace/new")
//    public ResponseEntity<FavPlace> savefavPlace(@RequestParam("userId") Integer userId,
//                                                 @RequestParam("mapId") Integer mapId) {
//        FavPlace favPlace = favPlaceService.saveFavPlace(userId, mapId);
//        return ResponseEntity.ok(favPlace);
//    }


}
