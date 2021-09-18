package hello.exception.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.exception.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 서블릿 컨테이너에 다시 exception 발생시켜서 BaseController 가고 이런거 없이 여기서 한번에 처리 가능
 */
@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        try {

            if (ex instanceof UserException) {
               log.info("UserException resolver to 400");
               String acceptHeader = request.getHeader("accept");
               response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

               if ("application/json".equals(acceptHeader)) {
                   Map<String, Object> errorResult = new HashMap<>();
                   errorResult.put("ex", ex.getClass());
                   errorResult.put("message", ex.getMessage());

                   String result = objectMapper.writeValueAsString(errorResult);

                   response.setContentType("application/json");
                   response.setCharacterEncoding("utf-8");
                   response.getWriter().write(result);
                   return new ModelAndView();
               } else {
                   //TEXT/HTML 등등
                   return new ModelAndView("error/500");
               }

            }

        } catch (IOException e) {
            log.info("resolver ex", e);
        }

        return null;
    }
}
