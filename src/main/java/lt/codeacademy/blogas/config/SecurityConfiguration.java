package lt.codeacademy.blogas.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    //    Praleidžiam visą srautą
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/login", "/records/public/**", "/")
                .permitAll()
                .antMatchers("/records/private/**")
                .authenticated()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .permitAll()
                .loginPage("/login")
                .loginProcessingUrl("/login")

                .usernameParameter("loginName")
                .passwordParameter("loginPassword")

                .defaultSuccessUrl("/records/public/all", true)
                .failureUrl("/login?error=true")
                .and()
                .logout()
                .permitAll()
        ;

    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(
                PathRequest.toStaticResources().atCommonLocations()); // statiniai failai (css, images, js)
    }

    /**
     * Šitas reikalingas authorizacijai
     */

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder encoder1 = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder1);

//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("SELECT name as username, password, TRUE as enabled FROM Users u WHERE u.name = ?")
//                .authoritiesByUsernameQuery("SELECT name as username, 'USER' as authority FROM Users u WHERE u.name = ?")
//                .passwordEncoder(passwordEncoder);
//                .inMemoryAuthentication()
//                .withUser("user")
//                    .password(passwordEncoder.encode("pass"))
//                    .roles("USER")
//                    .and()
//                .withUser("admin")
//                    .password(passwordEncoder.encode("admin"))
//                    .roles("ADMIN");
    }

//    @Bean
//    public PasswordEncoder encoder1() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }

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
