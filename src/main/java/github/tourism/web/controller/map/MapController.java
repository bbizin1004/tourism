package github.tourism.web.controller.map;

import github.tourism.service.favPlace.FavPlaceService;
import github.tourism.service.map.MapService;
import github.tourism.service.user.security.CustomUserDetails;
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


    //맵 상세조회
    @GetMapping("/{mapId}")
    public ResponseEntity<MapDetailsDTO> getMapDetail(@PathVariable Integer mapId,
                                                      @AuthenticationPrincipal CustomUserDetails user) {

        Integer userId = null;
        if(user != null){
             userId = Integer.valueOf(user.getUserId());
        }

        MapDetailsDTO mapDetails = mapService.getMapDetail(mapId,userId);
        return ResponseEntity.ok(mapDetails);
    }


    //찜 토글하기
    @PreAuthorize("isAuthenticated()") //로그인 체크
    @PostMapping("/{mapId}/new")
    public ResponseEntity<Boolean> toggleFavPlace(@AuthenticationPrincipal CustomUserDetails user,
                                                    @PathVariable Integer mapId) {

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Integer userId = Integer.valueOf(user.getUserId());
        boolean isFavorite = mapService.toggleFavoritePlace(userId, mapId);

        return ResponseEntity.ok(isFavorite);
    }




}
