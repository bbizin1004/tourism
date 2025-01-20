package github.tourism.web.controller.map;

import github.tourism.service.favPlace.FavPlaceService;
import github.tourism.service.map.MapService;
import github.tourism.web.dto.favPlace.FavPlaceDTO;
import github.tourism.web.dto.map.MapDetailsDTO;
import github.tourism.web.dto.map.MapsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/maps")
public class MapController {

    private final MapService mapService;
    private final FavPlaceService favPlaceService;


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


    //찜 토글하기
    @PreAuthorize("isAuthenticated()") //로그인 체크
    @PostMapping("/{mapId}/new")
    public ResponseEntity<Boolean> saveFavPlace(@AuthenticationPrincipal UserDetails user,
                                                    @PathVariable Integer mapId) {

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Integer userId = Integer.valueOf(user.getUsername());
        boolean isFavorite = mapService.toggleFavoritePlace(userId, mapId);

        return ResponseEntity.ok(isFavorite);
    }

    //찜 삭제하기
//    @PreAuthorize("isAuthenticated()")
//    @DeleteMapping("/delete")
//    public ResponseEntity<Void> deleteFavPlace(@RequestParam("userId") Integer userId,
//                                               @RequestParam("mapId") Integer mapId) {
//        favPlaceService.deleteFavPlace(userId, mapId);
//        return ResponseEntity.noContent().build();
//    }


}
