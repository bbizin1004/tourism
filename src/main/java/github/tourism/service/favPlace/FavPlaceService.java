package github.tourism.service.favPlace;

import github.tourism.data.entity.favPlace.FavPlace;
import github.tourism.data.repository.favPlace.FavPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class FavPlaceService {
    private final FavPlaceRepository favPlaceRepository;

    // 유저의 찜한 장소 가져오기
    @Transactional(readOnly = true)
    public Page<FavPlace> getUserFavPlaces(Integer userId, Pageable pageable) {
        return favPlaceRepository.findAllByUserUserId(userId, pageable);
    }

    // 찜한 장소 삭제
    @Transactional
    public void deleteFavPlace(Integer favPlaceId) {
        if(!favPlaceRepository.existsById(favPlaceId)){
            throw new IllegalArgumentException("해당 찜한 장소가 존재하지 않습니다. ID: " + favPlaceId);
        }
        favPlaceRepository.deleteById(favPlaceId);
    }
}
