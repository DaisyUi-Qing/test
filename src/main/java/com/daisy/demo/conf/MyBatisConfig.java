//package com.daisy.demo.conf;
//
//import com.github.pagehelper.PageInterceptor;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.mybatis.spring.mapper.MapperScannerConfigurer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//@MapperScan("com.daisy.demo.dao") // 指定MyBatis的Mapper接口所在的包路径
//public class MyBatisConfig {
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//
//        // 创建PageInterceptor拦截器对象
//        PageInterceptor pageInterceptor = new PageInterceptor();
//        Properties properties = new Properties();
//
//        // 设置分页插件参数
//        properties.setProperty("helperDialect", "mysql");
//        properties.setProperty("reasonable", "true");
//        properties.setProperty("supportMethodsArguments", "true");
//        pageInterceptor.setProperties(properties);
//
//        // 将拦截器添加到SqlSessionFactory中
//        sessionFactory.setPlugins(new Interceptor[]{pageInterceptor});
//
//        return sessionFactory.getObject();
//    }
//
//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer() {
//        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
//        scannerConfigurer.setBasePackage("mapper"); // 指定Mapper接口所在的包路径
//        return scannerConfigurer;
//    }
//}
