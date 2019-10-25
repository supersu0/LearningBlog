package run.halo.app.event.freemarker;
import freemarker.template.Configuration;
//https://my.oschina.net/u/699015/blog/175704  FreeMarker 配置 (Configuration)
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
http://blog.didispace.com/Spring-Boot-2-0-feature-2-ApplicationStartedEvent/  Spring Boot 2.0 新特性（二）：新增事件 ApplicationStartedEvent
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
//https://juejin.im/post/5ccfc888e51d456e8240dd7b  spring core 包中的 order 作用
//https://www.cnblogs.com/lzmrex/p/6944961.html  spring @Order 标记
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import run.halo.app.event.options.OptionUpdatedEvent;
import run.halo.app.event.theme.ThemeActivatedEvent;
import run.halo.app.event.user.UserUpdatedEvent;
import run.halo.app.hadler.theme.config.support.ThemeProperty;
import run.halo.app.model.properties.OtherProperties;
import run.halo.app.model.support.HaloConst;
import run.halo.app.service.OptionService;
import run.halo.app.service.ThemeSevice;
import run.halo.app.service.themeSettingService;
import run.halo.app.service.UserService;
//Freemarker config aware listener.
@Slf4j
@Component
public class FreemarkerConfigAwareListener{
private final OptionService optionService;
private final Configuration configuration;
private final ThemeService themeService;
private final ThemeSettingService themeSettingService;
private final UserService userService;
public FreemarkerConfigAwareListener(OptonService optionService,Configuration configration,ThemeService themeService,UserService userService){
this.optionService = optionService;
this.configuration = configuration;
this.themeService = themeService;
this.themeSettingService = themeSettingService;
this.userService = userService;
@EventListener
@Order(Order.HIGHEST_PRECEDENCE+1)  //最高优先级
pulic void onApplicationStartedEvent(ApplicationStartedEvent applicationStartedEvent) throws TemplateModelException{
//CONTINUE TO CODE ....
}
}

}


