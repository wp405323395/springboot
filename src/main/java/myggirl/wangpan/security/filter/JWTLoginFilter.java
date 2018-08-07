package myggirl.wangpan.security.filter;import com.fasterxml.jackson.databind.ObjectMapper;import io.jsonwebtoken.Jwts;import io.jsonwebtoken.SignatureAlgorithm;import myggirl.wangpan.domain.User;import myggirl.wangpan.security.ConstantKey;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.security.authentication.AuthenticationManager;import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;import org.springframework.security.core.Authentication;import org.springframework.security.core.AuthenticationException;import org.springframework.security.core.GrantedAuthority;import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;import javax.servlet.FilterChain;import javax.servlet.ServletException;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import java.io.IOException;import java.util.ArrayList;import java.util.Collection;import java.util.Date;import java.util.List;public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {    AuthenticationManager authenticationManager;    public JWTLoginFilter(AuthenticationManager authenticationManager) {        this.authenticationManager = authenticationManager;    }    @Override    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {        try {            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);            return authenticationManager.authenticate(                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())            );        } catch (IOException e) {            throw new RuntimeException(e);        }    }    @Override    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {        String token = null;        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();        List roleList = new ArrayList<>();        for (GrantedAuthority grantedAuthority : authorities) {            roleList.add(grantedAuthority.getAuthority());        }        token = Jwts.builder()                .setSubject(authResult.getName() + "-" + roleList)                .setExpiration(new Date(System.currentTimeMillis()+ 365 * 24 * 60 * 60 * 1000)) // 设置过期时间 365 * 24 * 60 * 60秒(这里为了方便测试，所以设置了1年的过期时间，实际项目需要根据自己的情况修改)                .signWith(SignatureAlgorithm.HS512, ConstantKey.SIGNING_KEY)                .compact();        response.addHeader("Authorization", "Bearer " + token);    }}