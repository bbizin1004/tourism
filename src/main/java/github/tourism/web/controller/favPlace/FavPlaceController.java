package github.tourism.web.controller.favPlace;

import github.tourism.data.entity.favPlace.FavPlace;
import github.tourism.service.favPlace.FavPlaceService;
import github.tourism.web.dto.favPlace.FavPlaceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/fav-places")
public class FavPlaceController {
    private final FavPlaceService favPlaceService;

    // 찜한 장소 리스트 가져오기
    @GetMapping("/{userId}")
    public ResponseEntity<List<FavPlaceDTO>> getUserFavPlaces(@PageableDefault(page = 0 , size = 10) Pageable pageable, @PathVariable Integer userId) {
        // 서비스 계층에서 유저 ID로 찜한 장소 리스트를 조회
        Page<FavPlace> favPlaces = favPlaceService.getUserFavPlaces(userId, pageable);

        // FavPlace -> FavPlaceDTO로 변환
        List<FavPlaceDTO> favPlaceDTOs = favPlaces.stream()
                .map(FavPlaceDTO::new) // 엔티티를 DTO로 변환
                .toList();

        return ResponseEntity.ok(favPlaceDTOs);
    }

    //   찜한 장소 삭제
    @DeleteMapping("/{favPlaceId}")
    public ResponseEntity<String> deleteFavPlace(@PathVariable Integer favPlaceId) {
        favPlaceService.deleteFavPlace(favPlaceId);
        return ResponseEntity.ok("찜한 장소가 삭제되었습니다.");
    }



}







