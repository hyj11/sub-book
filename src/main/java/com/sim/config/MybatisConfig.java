package com.sim.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.sim.dao")
public class MybatisConfig {

    @Value("${mysql.jdbc.driverClassName}")
    private String driverClassName;
    @Value("${mysql.jdbc.url}")
    private String url;
    @Value("${mysql.jdbc.username}")
    private String username;
    @Value("${mysql.jdbc.password}")
    private String password;
    /**
     * 连接池最大使用连接数
     */
    @Value("${mysql.jdbc.maxActive}")
    private int maxActive;
    /**
     * 初始化连接大小
     */
    @Value("${mysql.jdbc.initialSize}")
    private int initialSize;
    /**
     * 获取连接最大等待时间
     */
    @Value("${mysql.jdbc.maxWait}")
    private long maxWait;
    /**
     * 连接池最大空闲
     */
    @Value("${mysql.jdbc.maxIdle}")
    private int maxIdle;
    /**
     * 连接池最小空闲
     */
    @Value("${mysql.jdbc.minIdle}")
    private int minIdle;
    /**
     * 自动清除无用连接
     */
    @Value("${mysql.jdbc.removeAbandoned}")
    private String removeAbandoned;
    /**
     * 清除无用连接的等待时间
     */
    @Value("${mysql.jdbc.removeAbandonedTimeout}")
    private int removeAbandonedTimeout;
    /**
     * 连接属性
     */
    @Value("${mysql.jdbc.connectionProperties}")
    private String connectionProperties;

    @Bean(name = "druidDataSource")
    @Primary
    public DataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(maxActive);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxWait(maxWait);
        //过期
//        dataSource.setMaxIdle(maxIdle);
        dataSource.setMinIdle(minIdle);
        if("true".equals(removeAbandoned)){
            dataSource.setRemoveAbandoned(true);
        } else {
            dataSource.setRemoveAbandoned(false);
        }
        dataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
        dataSource.setConnectionProperties(connectionProperties);

        return dataSource;
    }

    @Bean(name = "duridTransactionManager")
    @Primary
    public PlatformTransactionManager duridTransactionManager() {
        return new DataSourceTransactionManager(druidDataSource());
    }

    @Bean(name = "duridSqlSessionFactory")
    @Primary
    public SqlSessionFactory duridSqlSessionFactory(@Qualifier("druidDataSource") DataSource rdsDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(rdsDataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:/mappers/*.xml"));
        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sessionFactory.getObject();
    }

    /*
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
    */
}
