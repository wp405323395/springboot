package myggirl.wangpan.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;


import javax.sql.DataSource;
import java.io.IOException;

//事物的提交配置
@Configuration
public class MybatisSessionFactoryConfig {
    @Value("${mybatis.mybatis_config_file}")
    private String mybatisConfigFilePath;
    @Value("${mybatis.mapper_path}")
    private String mapperPath;
    @Value("${mybatis.entity_package}")
    private String entityPackage;

    @Autowired
    @Qualifier("dataSource")//ioc中bean的名称
    private DataSource dataSource;

    @Bean(name = "sqlSesstionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置mybatis配置文件路径。
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFilePath));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;
        //设置mapper文件的扫描路径
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageSearchPath));
        //设置datasource数据库连接池，这里是c3p0.
        sqlSessionFactoryBean.setDataSource(dataSource);
        //设置映射实体类的扫描路径。
        sqlSessionFactoryBean.setTypeAliasesPackage(entityPackage);
        return sqlSessionFactoryBean;
    }
}
