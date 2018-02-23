package myggirl.wangpan.config;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决跨域问题
 */

@Configuration
public class FilterConfig {
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());//添加过滤器
        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
        registration.addInitParameter("name", "alue");//添加默认参数
        registration.setName("MyFilter");//设置优先级
        registration.setOrder(1);//设置优先级
        return registration;
    }

    public class MyFilter implements Filter {
        @Override
        public void destroy() {
        }

        @Override
        public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
            System.out.println("初始化了");
        }

        @Override
        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain
                filterChain)
                throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) srequest;
            HttpServletResponse response = (HttpServletResponse) sresponse;

            String origin = request.getHeader("Origin");
            String headers = request.getHeader("Access-Control-Request-Headers");
            //支持跨域的域名，origin的值为跨域的域名。
            if(!StringUtils.isEmpty(origin)) {
                response.addHeader("Access-Control-Allow-Origin",origin);
            }
            //支持自定义头的跨域请求
            if(!StringUtils.isEmpty(headers)) {
                response.addHeader("Access-Control-Allow-Headers",headers);
            }
            //cookie跨域时不能用*。
            //response.addHeader("Access-Control-Allow-Origin","*");
            response.addHeader("Access-Control-Allow-Methods","*");
            response.addHeader("Access-Control-Max-Age","3600");//域检命令缓存时间，这段时间里不再发送域检命令。
            //enable cookie
            response.addHeader("Access-Control-Allow-Credentials","true");

            //打印请求Url
            System.out.println("过滤器过滤的,url :" + request.getRequestURI());
            filterChain.doFilter(srequest, sresponse);
        }


    }
}