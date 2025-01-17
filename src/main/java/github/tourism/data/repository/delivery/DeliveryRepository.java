package github.tourism.data.repository.delivery;

import github.tourism.data.entity.delivery.Delivery;
import github.tourism.data.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

    Optional<Delivery> findByMainAddressAndUser(String mainAddress, User user);
}
