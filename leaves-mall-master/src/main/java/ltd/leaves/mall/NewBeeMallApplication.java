
package ltd.leaves.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("ltd.newbee.mall.dao")
@SpringBootApplication
public class NewBeeMallApplication {
//    git测试2
    public static void main(String[] args) {
        SpringApplication.run(NewBeeMallApplication.class, args);
    }
}
