package lt.codeacademy.blogas.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


//    Praleidžiam visą srautą
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                .formLogin()
                    .permitAll()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")

                    .usernameParameter("loginName")
                    .passwordParameter("loginPassword")

                    .defaultSuccessUrl("/records/all",true)
                    .failureUrl("/login?error=true")
                    .and()
                .logout()
                    .permitAll()
        ;

    }


//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
////        http
////                .authorizeRequests()
////                .anyRequest()
////                .authenticated();
////                .and()
////                .formLogin()
////                .permitAll();
////                .loginPage("/prisijungimas")
////                .loginProcessingUrl("/prisijungimas")
////                .usernameParameter("loginName")
////                .passwordParameter("loginPassword")
////                .defaultSuccessUrl("/products", true)
////                .failureUrl("/prisijungimas?error");
//    }
}
