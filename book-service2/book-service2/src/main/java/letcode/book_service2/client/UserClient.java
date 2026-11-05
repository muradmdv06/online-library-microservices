package letcode.book_service2.client;


import letcode.book_service2.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-app")
public interface UserClient {
    @GetMapping("/users/{id}")
    UserResponseDto getById(@PathVariable String id);
}
