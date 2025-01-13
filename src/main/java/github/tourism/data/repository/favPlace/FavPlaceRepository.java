package github.tourism.data.repository.favPlace;

import github.tourism.data.entity.favPlace.FavPlace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavPlaceRepository extends JpaRepository<FavPlace, Integer> {

//    사용자 ID로 찜한 장소 리스트 가져오기
    Page<FavPlace> findAllByUserUserId(Integer userId, Pageable pageable);

    // ID 존재 여부 확인
    boolean existsById(Integer favPlaceId);
}
