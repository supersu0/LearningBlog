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
log.debug("Recieived application started event")
//https://blog.csdn.net/qq_15037231/article/details/79645503   如何使用 log.debug ()
loadThemeConfig();
loadOptionsConfig();
loadUserConfig();
  
}
@EventListener
public void onThemeActivatedEvent(ThemeActivatedEvent themeActivatedEvent ) throws TemplateException{
log.debug("Received theme activated event");
loadThemeConfig();
}
@EventListener
public void onUserUpdate(UserUpdatedEvent event )throws TemplateModelException{
log.debug("Received user updated event,user id:[{}]",event.getUserId());
loadUserConfig();
@EventListener
public void onOptionUpdate(OptioUpdatedEvent event) throws TemplateModelException{
log.debug("Received option update event");
loadOptionsConfig();
loadThemeConfig();
}
private void loadUserConfig() throws TemplateModelException{
configuration.setSharedVariable("user",userService.getCurrentUser().orElse(null));
//http://freemarker.foofun.cn/pgui_config_sharedvariables.html
log.debug("Loaded user");
}
private voidOptinos() throws TemplateModelException{
configuration.setSharedVariable("options",optionService.listOptions());
configuration.setSharedVariable("context",optionService.getBlogBaseUrl());
configuration.setSharedVariab;e("version",HaloConst.HALOO_VERSION);
log.debug("Loaded options");
}
private void loadThemeConfig() throws TemplateModelException{
String baseUrl =optonService.getByPropertyDefault(OtherProperties.CDN_DOMIN,String.class,optionService.getBlogBaseUrl());
//相当于得到运行时类
ThemeProperty activatedTheme = themeService.getActivatedTheme();
configuration.setSharedVariable("theme",activatedTheme);
configuration.setSharedVariable("static",baseUrl+"/"+activatedTheme.getFolderName());
configuration.setSharedVariable("settings",themeSettingService.listAsMapBy(themeService.getActivatedThemeId()));
log.debug("Loaded theme and settings");
}  
}

}

}


