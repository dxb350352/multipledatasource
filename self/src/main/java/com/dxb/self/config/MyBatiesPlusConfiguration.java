package com.dxb.self.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.dxb.self.aop.DataSourceEnum;
import com.dxb.self.aop.MultipleDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyBatiesPlusConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.self.ds1")
    public DataSource dataSource1() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.self.ds2")
    public DataSource dataSource2() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态数据源配置
     *
     * @param ds1
     * @param ds2
     * @return
     */
    @Bean
    @Primary
    public DataSource multipleDataSource(@Qualifier("dataSource1") DataSource ds1, @Qualifier("dataSource2") DataSource ds2) {
        MultipleDataSource multipleDataSource = new MultipleDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceEnum.DS1.getKey(), ds1);
        dataSourceMap.put(DataSourceEnum.DS2.getKey(), ds2);
        //添加数据源
        multipleDataSource.setTargetDataSources(dataSourceMap);
        //设置默认数据源
        multipleDataSource.setDefaultTargetDataSource(ds1);

        return multipleDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(multipleDataSource(dataSource1(), dataSource2()));

        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*/*Mapper.xml"));

        MybatisConfiguration configuration=new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);

        sqlSessionFactoryBean.setConfiguration(configuration);

        sqlSessionFactoryBean.setPlugins(new Interceptor[]{
                paginationInterceptor() //添加分页功能
        });
        return sqlSessionFactoryBean.getObject();
    }


    /*
     * 分页插件，自动识别数据库类型
     * 多租户，请参考官网【插件扩展】
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}
