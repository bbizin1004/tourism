package github.tourism.web.controller.payment;

import github.tourism.service.payment.PaymentService;
import github.tourism.service.user.security.CustomUserDetails;
import github.tourism.web.dto.payment.PaymentHistoryResponseDTO;
import github.tourism.web.dto.payment.PaymentRequestDTO;
import github.tourism.web.dto.payment.PaymentResponseDTO;
import github.tourism.web.exception.PaymentProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    // 결제 처리
    @PostMapping("/process")
    public ResponseEntity<PaymentResponseDTO> processPayment(@RequestBody PaymentRequestDTO paymentRequestDTO,
                                                             @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        try {
            Integer userId = customUserDetails.getUserId();
            paymentRequestDTO.setUserId(userId);

            PaymentResponseDTO paymentResponseDTO = paymentService.processPayment(paymentRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(paymentResponseDTO);

        }catch (IllegalArgumentException | PaymentProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PaymentResponseDTO(null, BigDecimal.ZERO,e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new PaymentResponseDTO(null, BigDecimal.ZERO,e.getMessage()));
        }
    }

    // 결제 내역 조회
    @GetMapping("/history")
    public ResponseEntity<List<PaymentHistoryResponseDTO>> getPaymentHistory(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        try{
            Integer userId = customUserDetails.getUserId();
            List<PaymentHistoryResponseDTO> paymentHistory = paymentService.getPaymentHistory(userId);
            return ResponseEntity.status(HttpStatus.OK).body(paymentHistory);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.emptyList());
        }
    }

    // TODO : 결제내역 조회 -> 페이지나 필터링 써서 좀더 세분화 시켜놔야 함







}

