package github.tourism.web.controller.payment;

import github.tourism.service.payment.PaymentService;
import github.tourism.service.user.security.CustomUserDetails;
import github.tourism.web.dto.payment.*;
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

            PaymentResponseDTO paymentResponseDTO = paymentService.processPayment(paymentRequestDTO,userId);
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


    // 결제 취소 요청
    @DeleteMapping("/cancel/{impUid}")
    public ResponseEntity<PaymentCancelResponseDTO> cancelPayment(@PathVariable String impUid,
                                                                  @RequestBody cancelRequestDTO cancelRequestdto,
                                                                  @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        try {
            Integer userId = customUserDetails.getUserId();

            // 결제 취소 처리
            BigDecimal cancelledAmount =  paymentService.cancelPayment(impUid,cancelRequestdto.getReason(),userId);
            PaymentCancelResponseDTO responseDTO = new PaymentCancelResponseDTO(
                    "Success","결제 취소가 정상적으로 처리되었습니다.", impUid, cancelledAmount
            );
            return ResponseEntity.ok(responseDTO);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new PaymentCancelResponseDTO("Error", e.getMessage(),impUid,BigDecimal.ZERO)
            );
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new PaymentCancelResponseDTO("Error","결제 취소 중 오류가 발생했습니다.",impUid,BigDecimal.ZERO)
            );
        }

    }







}

