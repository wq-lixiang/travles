package world.lixiang.travels;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("world.lixiang.travels.dao")
public class TravelsApplication {
    public static void main(String[] args) {
        SpringApplication.run(TravelsApplication.class, args);
    }
}
