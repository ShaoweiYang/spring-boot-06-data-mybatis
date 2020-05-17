package com.atguigu.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.util.*;

/**
 * @author Shawn.Yang
 * @create 2020-05-17-19  :09
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    //配置druid监控
    //配置管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean<Servlet> registrationBean = new ServletRegistrationBean<>();
        registrationBean.addUrlMappings("/druid/*");
        /*public static final String SESSION_USER_KEY = "druid-user";
        public static final String PARAM_NAME_USERNAME = "loginUsername";
        public static final String PARAM_NAME_PASSWORD = "loginPassword";
        public static final String PARAM_NAME_ALLOW = "allow";
        public static final String PARAM_NAME_DENY = "deny";
        public static final String PARAM_REMOTE_ADDR = "remoteAddress";*/
        //配置初始化参数
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "admin");//允许登录的用户名
        initParams.put("loginPassword", "123456");//允许登录的密码
        initParams.put("allow", "");//默认允许所有访问
        initParams.put("deny", "");//配置拒绝
        registrationBean.setInitParameters(initParams);

        registrationBean.setServlet(new StatViewServlet());
        return registrationBean;
    }

    //配置一个监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new WebStatFilter());

        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);//设置监控路径

        /*private static final Log LOG = LogFactory.getLog(WebStatFilter.class);
        public static final String PARAM_NAME_PROFILE_ENABLE = "profileEnable";
        public static final String PARAM_NAME_SESSION_STAT_ENABLE = "sessionStatEnable";
        public static final String PARAM_NAME_SESSION_STAT_MAX_COUNT = "sessionStatMaxCount";
        public static final String PARAM_NAME_EXCLUSIONS = "exclusions";
        public static final String PARAM_NAME_PRINCIPAL_SESSION_NAME = "principalSessionName";
        public static final String PARAM_NAME_PRINCIPAL_COOKIE_NAME = "principalCookieName";
        public static final String PARAM_NAME_REAL_IP_HEADER = "realIpHeader";*/
        Map<String, String> map = new LinkedHashMap<>();
        map.put("exclusions", "*.js,*.css,/druid/*");//设置不拦截的路径
        registrationBean.setInitParameters(map);

        return registrationBean;
    }

}
