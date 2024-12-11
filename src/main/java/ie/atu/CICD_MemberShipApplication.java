package ie.atu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CICD_MemberShipApplication {
    public static void main(String[] args) {
        SpringApplication.run(CICD_MemberShipApplication.class, args);
    }
}
