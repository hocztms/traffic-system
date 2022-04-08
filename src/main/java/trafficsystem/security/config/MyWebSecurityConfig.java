package trafficsystem.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import trafficsystem.filters.JwtAuthTokenFilter;
import trafficsystem.security.hander.MyAccessDeniedHandler;
import trafficsystem.security.hander.MyAuthenticationEntryPoint;
import trafficsystem.security.hander.MyLogoutSuccessHandler;
import trafficsystem.security.servie.MyAdminDetailServiceImpl;
import trafficsystem.security.xss.XssFilter;


@Configuration
@EnableWebSecurity
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAdminDetailServiceImpl userDetailService;

    @Autowired
    private JwtAuthTokenFilter jwtAuthTokenFilter;

    @Autowired
    private XssFilter xssFilter;
//    @Autowired
//    private IpLimitFilter ipLimitFilter;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        auth.authenticationProvider(wxLoginAuthenticationProvider());
        auth.userDetailsService(userDetailService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

////        login表单提交
//        http.formLogin()
//                .loginPage("/login.html")
//                .loginProcessingUrl("/user/login");

//        基于jwt 所以取消session
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()


                .antMatchers("/socket/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/index/**").permitAll()
                .anyRequest().authenticated();


        //自定义未登录 无权限处理
        http.exceptionHandling()
                .accessDeniedHandler(new MyAccessDeniedHandler())
                .authenticationEntryPoint(new MyAuthenticationEntryPoint());

        http.logout().logoutSuccessHandler(new MyLogoutSuccessHandler());

        //jwt
        http.addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //xss防护
        http.addFilterAfter(xssFilter, CsrfFilter.class);
        /*
        ip限流
         */
//        http.addFilterBefore(ipLimitFilter,CsrfFilter.class);
        http.csrf().disable();
        http.cors();
    }   




    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Bean
//    public WxLoginAuthenticationProvider wxLoginAuthenticationProvider (){
//        return new WxLoginAuthenticationProvider();
//    }


    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        return httpServletRequest -> {
            CorsConfiguration cfg = new CorsConfiguration();
            cfg.addAllowedHeader("*");
            cfg.addAllowedMethod("*");
            cfg.addAllowedOriginPattern("*");
            cfg.setAllowCredentials(true);
            return cfg;
        };
    }
}
