package letcode.book_service2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BookService2Application {

	public static void main(String[] args) {
		SpringApplication.run(BookService2Application.class, args);
	}

}
