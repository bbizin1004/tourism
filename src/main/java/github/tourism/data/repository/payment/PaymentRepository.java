package github.tourism.data.repository.payment;

import github.tourism.data.entity.payment.Payment;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    // userId 로 결제내역 조회
    @Query("SELECT p FROM Payment p WHERE p.order.user.userId = :userId")
    List<Payment> findByUserId(@Param("userId") Integer userId);
}
