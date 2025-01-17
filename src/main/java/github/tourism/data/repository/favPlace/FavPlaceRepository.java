package github.tourism.data.repository.favPlace;

import github.tourism.data.entity.favPlace.FavPlace;
import github.tourism.data.entity.map.Map;
import github.tourism.data.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavPlaceRepository extends JpaRepository<FavPlace, Integer> {

//    사용자 ID로 찜한 장소 리스트 가져오기
    Page<FavPlace> findAllByUserUserId(Integer userId, Pageable pageable);

    // ID 존재 여부 확인
    boolean existsById(Integer favPlaceId);

    //사용자 ID와 MapID로 사용자가 해당 맵을 찜했는지 확인
    boolean existsByUserUserIdAndMapMapId(Integer userId, Integer mapId);

    //사용자와 Map로 단건 조회
    Optional<FavPlace> findByUserAndMap(User user, Map map);


}
