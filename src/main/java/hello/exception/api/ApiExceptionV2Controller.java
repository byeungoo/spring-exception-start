package hello.exception.api;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ApiExceptionV2Controller {

//    /**
//     * 이 컨트롤러에서 이 에러가 방생하면 여기서 에러를 잡음.
//     * @param e
//     * @return
//     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ErrorResult illegalExHandler(IllegalArgumentException e) {
//        log.error("[exceptionHandler] ex", e);
//        return new ErrorResult("BAD", e.getMessage());
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<ErrorResult> userExHandler(UserException e) { //파라미터에 적어도 적용됨.
//        log.error("[exceptionHandler] ex", e);
//        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
//        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler
//    public ErrorResult exHandler(Exception e) {
//        log.error("[exceptionHandl;er]", e);
//        return new ErrorResult("EX", "내부 오류");
//    }


    @GetMapping("/api2/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {

        if(id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }

        if(id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력 값");
        }

        if(id.equals("user-ex")) {
            throw new UserException("사용자 오류");
        }

        return new MemberDto(id, "hello " + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }

}
