
package ltd.leaves.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("ltd.leaves.mall.dao")
@SpringBootApplication
public class LeavesMallApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeavesMallApplication.class, args);
    }
}
