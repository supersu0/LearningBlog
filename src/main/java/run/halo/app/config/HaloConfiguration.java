packagfe run.halo.app.config;
import com.fasterxml.jackson.databind.ObjectMapper;
//Jackson 之 jackson-databind
https://blog.csdn.net/u011499747/article/details/78762007
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalMissingBean;
//Spring Boot 基于特定条件创建 Bean 例子 : ConditionalOnMissingBean
//https://blog.csdn.net/andy_zhang2007/article/details/81285130
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
//掌握 Spring 之 RestTemplate
//https://juejin.im/post/5cd680eff265da037b612e28
import org.springframework.boot.web.servlet.FilterRegistrationBean;
//springboot 通过 FilterRegistrationBean 注册配置 Filter
//https://my.oschina.net/u/4058864/blog/2995539
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
//org.springframework.core.Ordered 接口
//https://www.xuebuyuan.com/2856490.html
import.springframework.http.client.HttpComponentsClienthttpRequestFactory;
//使用 HttpClient4 来构建 Spring RestTemplate
//https://www.cnblogs.com/hupengcool/p/4590006.html
import
