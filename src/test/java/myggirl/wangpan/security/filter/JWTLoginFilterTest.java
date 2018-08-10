package myggirl.wangpan.security.filter;import io.jsonwebtoken.JwtBuilder;import io.jsonwebtoken.Jwts;import io.jsonwebtoken.SignatureAlgorithm;import org.junit.Test;import javax.crypto.spec.SecretKeySpec;import javax.xml.bind.DatatypeConverter;import java.security.Key;import java.util.Date;import static org.junit.Assert.*;public class JWTLoginFilterTest {    private final static String base64Secret = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";    private final static int expiresSecond = 172800000;    @Test    public void successfulAuthentication() {        String token = createJWT("张三", "[admin]");        System.out.println("生成的token为");        System.out.println(token);    }    public static String createJWT(String username, String roles) {        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;        long nowMillis = System.currentTimeMillis();        Date now = new Date(nowMillis);        //生成签名密钥        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Secret);        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());        //添加构成JWT的参数        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")                .claim("user_name", username)                .claim("user_role", roles)                .signWith(signatureAlgorithm, signingKey);        //添加Token过期时间        if (expiresSecond >= 0) {            long expMillis = nowMillis + expiresSecond;            Date exp = new Date(expMillis);            builder.setExpiration(exp).setNotBefore(now);        }        //生成JWT        return builder.compact();    }}