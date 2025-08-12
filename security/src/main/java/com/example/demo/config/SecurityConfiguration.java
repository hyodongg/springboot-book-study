package com.example.demo.config;

import com.example.demo.model.Authority;
import com.example.demo.model.Member;
import com.example.demo.model.MemberUserDetails;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsServiceCustom(MemberRepository memberRepository, AuthorityRepository authorityRepository) {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Member member = memberRepository.findByEmail(username).orElseThrow();
                List<Authority> authorities = authorityRepository.findByMember(member);
                return new MemberUserDetails(member, authorities);
            }
        };
    }

    /**@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/home").permitAll()
                .requestMatchers("/member/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated())
                .formLogin(withDefaults())
                .logout(withDefaults());
        return http.build();
    }*/

    /**
    // 자동로그인 rememberMe()
    @Bean
    public SecurityFilterChain securityFilterChain1(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated())
                .rememberMe(withDefaults())
                .formLogin(withDefaults())
                .logout(withDefaults());

        return http.build();
    }
     */


    @Bean
    PersistentTokenRepository persistentTokenRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        return repository;
    }

    @Bean
    RememberMeServices rememberMeServices(UserDetailsService userDetailsService,
                                          PersistentTokenRepository tokenRepository) {
        return new PersistentTokenBasedRememberMeServices("myRememberKey",
                userDetailsService,tokenRepository);
    }
/**
    @Bean
    public SecurityFilterChain securityFilterChain2(HttpSecurity http,
                                                    RememberMeServices rememberMeServices) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated())
                .rememberMe(remember -> remember
                        .rememberMeServices(rememberMeServices))
                .formLogin(withDefaults())
                .logout(withDefaults());

        return http.build();
    }
 */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize ->authorize
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/home")
                        .permitAll());
        return http.build();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration conf = new CorsConfiguration();
        conf.setAllowedOrigins(Arrays.asList("https://hanbit.co.kr","https://campus.co"));
        conf.setAllowedMethods(Arrays.asList("GET","POST"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", conf);

        return source;

    }

    @Bean
    public SecurityFilterChain securityFilterChainCors(CorsConfigurationSource corsConfigurationSource,
                                                       HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors
                        .configurationSource(corsConfigurationSource))
                .httpBasic(withDefaults());

        return http.build();
    }

}
