package pl.gromadzki.spittr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
    private BCryptPasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;

    public SecurityConfigure(BCryptPasswordEncoder passwordEncoder,
                             @Qualifier("userDetailsService") UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/spittle").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/spittle/api").hasAuthority("ADMIN")
                .antMatchers("/spittle/random").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/users").hasAuthority("ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .logoutSuccessUrl("/")
                .and()
                .csrf().disable();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
