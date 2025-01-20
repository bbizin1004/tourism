package github.tourism.web.filter;


import github.tourism.config.security.JwtTokenProvider;
import github.tourism.web.advice.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI(); // 요청 URI를 가져옴
        // /v3/api-docs 경로는 JWT 인증을 통과하도록 설정
        if (requestURI.equals("/v3/api-docs") || requestURI.startsWith("/swagger-ui")) {
            filterChain.doFilter(request, response); // 필터 체인을 계속 진행
            return; // 더 이상 처리하지 않음
        }

        String accessToken = jwtTokenProvider.resolveToken(request);

        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        if (jwtTokenProvider.isExpired(accessToken)) {
            sendErrorResponse(response, ErrorCode.EXPIRED_TOKEN2);
            return;
        }

        String category = jwtTokenProvider.getCategory(accessToken);
        if (!"access".equals(category)) {
            sendErrorResponse(response, ErrorCode.UNAUTHORIZED_TOKEN2);
            return;
        }

        if (jwtTokenProvider.validateToken(accessToken)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            sendErrorResponse(response, ErrorCode.UNAUTHORIZED_TOKEN);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void sendErrorResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setStatus(errorCode.getStatusCode());
        PrintWriter writer = response.getWriter();
        writer.print(errorCode.getErrorMessage());
    }
}
