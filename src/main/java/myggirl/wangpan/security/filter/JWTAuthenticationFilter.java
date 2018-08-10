package myggirl.wangpan.security.filter;import io.jsonwebtoken.*;import myggirl.wangpan.security.ConstantKey;import myggirl.wangpan.security.author.GrantedAuthorityImpl;import org.springframework.security.authentication.AuthenticationManager;import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;import org.springframework.security.core.GrantedAuthority;import org.springframework.security.core.context.SecurityContextHolder;import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;import javax.servlet.FilterChain;import javax.servlet.ServletException;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import java.io.IOException;import java.util.ArrayList;public class JWTAuthenticationFilter extends BasicAuthenticationFilter {    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {        super(authenticationManager);    }    @Override    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {        String header = request.getHeader("Authorization");        if(header == null || !header.startsWith("Bearer ")) {            chain.doFilter(request, response);            // response.setStatus(401);            return;        }        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request, response);        if(authenticationToken == null && response.getStatus() == 401) {            return ;        }        SecurityContextHolder.getContext().setAuthentication(authenticationToken);        chain.doFilter(request, response);    }    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response)  {        String token = request.getHeader("Authorization");        if (token == null || token.isEmpty()) {            throw new RuntimeException("Token为空");        }        String user = null;        try{            user = Jwts.parser()                    .setSigningKey(ConstantKey.SIGNING_KEY)// 解密token,拿到里面的payload数据。                    .parseClaimsJws(token.replace("Bearer ", ""))                    .getBody()                    .getSubject();            if(user != null) {                String[] split = user.split("-")[1].split(",");                ArrayList<GrantedAuthority> authorities = new ArrayList<>();                for (int i=0; i < split.length; i++) {                    authorities.add(new GrantedAuthorityImpl(split[i]));                }                return new UsernamePasswordAuthenticationToken(user, null, authorities);            }        }  catch (ExpiredJwtException e) {            logger.error("Token已过期: {} " + e);            response.setStatus(401);//            throw new RuntimeException("Token已过期");        } catch (UnsupportedJwtException e) {            logger.error("Token格式错误: {} " + e);            throw new RuntimeException("Token格式错误");        } catch (MalformedJwtException e) {            logger.error("Token没有被正确构造: {} " + e);            throw new RuntimeException("Token没有被正确构造");        } catch (SignatureException e) {            logger.error("签名失败: {} " + e);            throw new RuntimeException("签名失败");        } catch (IllegalArgumentException e) {            logger.error("非法参数异常: {} " + e);            throw new RuntimeException("非法参数异常");        }        return null;    }}