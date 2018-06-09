package com.hd.api.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * mybatis 配置
 */
@Configuration
@PropertySource(value = {"classpath:jdbc.properties"})
public class MybatisSpringConfig {

    @Autowired
    private DataSource dataSource;

    /**
     * 创建一个sql会话工厂bean，指定数据源
     *
     * @return
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.hd.api.model,com.hd.api.dto");
        factoryBean.setPlugins(new Interceptor[]{pageInterceptor()});
        return factoryBean;
    }

    /**
     * 分页插件
     *
     * @return
     */
    @Bean
    public PageInterceptor pageInterceptor() {
        PageInterceptor interceptor = new PageInterceptor();
        return interceptor;
    }
}
