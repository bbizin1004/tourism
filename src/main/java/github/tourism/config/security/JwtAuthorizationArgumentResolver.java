package github.tourism.config.security;

import github.tourism.config.util.JwtAuthorization;
import github.tourism.web.advice.ErrorCode;
import github.tourism.web.dto.user.UserInfoDTO;
import github.tourism.web.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthorizationArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtTokenProvider jwtProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JwtAuthorization.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.info("JwtAuthorizationArgumentResolver 동작!!");

        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);

        String token = httpServletRequest.getHeader("Authorization");

        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7).trim();

            if (jwtProvider.validateToken(jwtToken)) {
                return jwtProvider.getClaim(jwtToken);
            }
        }

        JwtAuthorization annotation = parameter.getParameterAnnotation(JwtAuthorization.class);
        if (annotation != null && !annotation.required()) {
            return new UserInfoDTO();
        }

        throw new UnauthorizedException(ErrorCode.ACCESS_DENIED);
    }
}
