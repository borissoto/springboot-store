package com.gamla.admin.config;


/*import com.github.dandelion.core.web.DandelionFilter;
import com.github.dandelion.core.web.DandelionServlet;
import com.github.dandelion.datatables.thymeleaf.dialect.DataTablesDialect;
import com.github.dandelion.thymeleaf.dialect.DandelionDialect;*/

import org.springframework.context.annotation.Configuration;

/**
 * Created by root on 6/4/17.
 */
@Configuration
public class WebConfiguration {

   /* @Bean
    public DandelionDialect dandelionDialect()
    {
        return new DandelionDialect();
    }

    @Bean
    public DataTablesDialect dataTablesDialect()
    {
        return new DataTablesDialect();
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean()
    {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new DandelionFilter());
        return filterRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean()
    {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new DandelionServlet());
        servletRegistrationBean.addUrlMappings("/dandelion-assets*//*");
        servletRegistrationBean.setName("dandelionServlet");
        return servletRegistrationBean;
    }*/
}


