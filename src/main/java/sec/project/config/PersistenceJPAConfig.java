
package sec.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import sec.project.service.SignupService;

@Configuration
@EnableTransactionManagement
public class PersistenceJPAConfig {

    @Bean(name = "signupService")
    @DependsOn("entityManagerFactory")
    public SignupService signupService() {
        return new SignupService();
    }
}