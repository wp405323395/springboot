package myggirl.wangpan.security;import myggirl.wangpan.security.filter.JWTAuthenticationFilter;import myggirl.wangpan.security.filter.JWTLoginFilter;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.context.annotation.Configuration;import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;import org.springframework.security.config.annotation.web.builders.HttpSecurity;import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;import org.springframework.security.config.http.SessionCreationPolicy;@Configuration@EnableWebSecurity@EnableGlobalMethodSecurity(prePostEnabled = true)public class WebSecurityConfig extends WebSecurityConfigurerAdapter {    @Autowired    private UserDetailsServiceImpl userDetailsService;    /**     * 需要放行的URL     */    private static final String[] AUTH_WHITELIST = {            // -- register url            "/users/signup",            // -- swagger ui            "/v2/api-docs",            "/swagger-resources",            "/swagger-resources/**",            "/configuration/ui",            "/configuration/security",            "/swagger-ui.html",            "/webjars/**"            // other public endpoints of your API may be appended to this array    };    @Override    protected void configure(HttpSecurity http) throws Exception {        http.cors().and().csrf().disable()                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()                .authorizeRequests()                .antMatchers(AUTH_WHITELIST).permitAll()                .anyRequest().authenticated().and()                .addFilter(new JWTLoginFilter(authenticationManager()))                .addFilter(new JWTAuthenticationFilter(authenticationManager()))                .logout()                .logoutUrl("/logout")                .logoutSuccessUrl("/logout")                .permitAll();        super.configure(http);    }    @Override    protected void configure(AuthenticationManagerBuilder auth) throws Exception {        auth.authenticationProvider( new CustomAuthenticationProvider(userDetailsService));    }}