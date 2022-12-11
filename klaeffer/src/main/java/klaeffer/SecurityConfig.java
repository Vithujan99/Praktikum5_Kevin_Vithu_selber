package klaeffer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain configure(HttpSecurity chainBuilder) throws Exception {
        chainBuilder.authorizeHttpRequests(
                configurer -> configurer.anyRequest().authenticated()
        ).oauth2Login(Customizer.withDefaults());
        return chainBuilder.build();
    }
}

//CLIENT-ID=1b4f7d1a411ff8fc1675
//CLIENT-SECRET=19740cd2fd5eb9d727d86e0e475e057d5caf522e