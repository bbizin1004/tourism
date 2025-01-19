package github.tourism.service.payment.imp;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import github.tourism.web.dto.payment.PaymentRequestDTO;
import github.tourism.web.dto.payment.PaymentVerificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class IMPService {

    private final IamportClient iamportClient;

    // 결제 검증
    public PaymentVerificationResponse verifyPayment(PaymentRequestDTO requestDTO, BigDecimal paidAmount) {
        try {
            IamportResponse<Payment> response = iamportClient.paymentByImpUid(requestDTO.getImpUid());

            // 결제 응답이 null 인 경우 실패
            if (response.getResponse() == null) {
                return new PaymentVerificationResponse(false, "아임포트 결제 응답이 유효하지 않습니다.");
            }

            Payment paymentData = response.getResponse();

            // 금액이 일치하고 결제 상태가 'PAID' 인 경우에만 유효
            boolean isVerified = paymentData.getAmount().equals(paidAmount)
                    && "PAID".equals(paymentData.getStatus());

            // 각 상황별 에러 문구
            if (isVerified) {
                return new PaymentVerificationResponse(true, "결제가 성공적으로 확인되었습니다.");
            } else {
                return new PaymentVerificationResponse(false, "결제 금액 불일치 또는 결제되지 않았습니다.");
            }

        } catch (IamportResponseException | IOException e) {
            return new PaymentVerificationResponse(false, "결제 검증 실패: " + e.getMessage());
        }

    }
}

