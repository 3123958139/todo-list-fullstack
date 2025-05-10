// 声明该类所在的包名为 server
package server;

// 导入 Spring Boot 应用启动类
import org.springframework.boot.SpringApplication;
// 导入 Spring Boot 自动配置注解类
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 应用的主类，用于启动整个应用。
 */
@SpringBootApplication
public class ServerApplication {
    /**
     * 程序的入口方法，Spring Boot 应用从这里开始启动。
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 启动 Spring Boot 应用
        SpringApplication.run(ServerApplication.class, args);
    }
}
