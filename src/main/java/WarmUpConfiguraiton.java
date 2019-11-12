import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import packageOne.HelloWorld;

@Configuration
public class WarmUpConfiguraiton {

    @Bean
    public HelloWorld helloBean(){
        return new HelloWorld();
    }

}
