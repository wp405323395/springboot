package myggirl.wangpan.security;import myggirl.wangpan.security.filter.JWTAuthenticationFilter;import myggirl.wangpan.security.filter.JWTLoginFilter;import myggirl.wangpan.security.authenticationProvider.CustomAuthenticationProvider;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.context.annotation.Configuration;import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;import org.springframework.security.config.annotation.web.builders.HttpSecurity;import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;import org.springframework.security.config.http.SessionCreationPolicy;@Configuration@EnableWebSecurity@EnableGlobalMethodSecurity(prePostEnabled = true)public class WebSecurityConfig extends WebSecurityConfigurerAdapter {    @Autowired    private CustomAuthenticationProvider customAuthenticationProvider;    /**     * 需要放行的URL     */    private static final String[] AUTH_WHITELIST = {            // -- register url            "/users/signup",            // -- swagger ui            "/v2/api-docs",            "/swagger-resources",            "/swagger-resources/**",            "/configuration/ui",            "/configuration/security",            "/swagger-ui.html",            "/webjars/**"            // other public endpoints of your API may be appended to this array    };    private static final  String[] ADMIN_AUTH_WHITELIST = {            "/admin/**"    };    private static final String[] DB_AUTH_WIHTELIST = {            "/db/**"    };    private static final String[] ROLE = {            "ADMIN","DBA"    };    @Override    protected void configure(AuthenticationManagerBuilder auth) throws Exception {        auth.authenticationProvider( customAuthenticationProvider);// 用户登录的校验和授权AuthenticationProvider    }    @Override    protected void configure(HttpSecurity http) throws Exception {        http.cors().and().csrf().disable()                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() //定制我们自己的 session 策略 调整为让 Spring Security 不创建和使用 session                .authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll()                                    .antMatchers(ADMIN_AUTH_WHITELIST).hasRole(ROLE[0])                                    .antMatchers(DB_AUTH_WIHTELIST).access("hasRole(ROLE[0]) and hasRole(ROLE[1])").anyRequest().authenticated().and()                .addFilter(new JWTLoginFilter(authenticationManager()))                .addFilter(new JWTAuthenticationFilter(authenticationManager()))                .logout()//                .logoutUrl("/logout")//                .logoutSuccessUrl("/logout")                .permitAll();        super.configure(http);    }}