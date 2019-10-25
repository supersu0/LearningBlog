package run.halo.app.controller.admin.api;

import io.swagger.annotations.ApiOperation;
https://my.oschina.net/dlam/blog/808315 . https://my.oschina.net/dlam/blog/808315
https://blog.csdn.net/i6448038/article/details/77622977  5 分钟了解 swagger
import lombok.extern.slf4j.Slf4j;
//https://www.cnblogs.com/weiapro/p/7633645.html . 注解 @Slf4jf
import org.springframework.http.HttpStatus;
//https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/package-summary.html
import org.springframework.web.bind.annotation.*;
import run.halo.app.cache.lock.CacheLock;
import run.halo.app.model.dto.EnvironmentDTO;
import run.halo.app.model.dto.StatisticDTO;
import run.halo.app.model.params.LoginParam;
import run.halo.app.model.params.ResetPasswordParam;
import run.halo.app.model.properties.PrimaryProperties;
import run.halo.app.model.support.BaseResponse;
import run.halo.app.security.token.AuthToken;
import run.halo.app.service.AdiminService;
import run.halo.app.service.OptionService;

import javax.validation.Valid;
//https://blog.csdn.net/qq_27680317/article/details/79970590
//@Validated 和 @Valid 区别：Spring validation 验证框架对入参实体进行嵌套验证必须在相应属性（字段）加上 @Valid 而不是 @Validated
//其实就是@Valid的功能更加全面一点

//Adimin controller
@Slf4j
@RestController
@RequestMapping("/api/admin")
//https://blog.csdn.net/u010412719/article/details/69710480  SpringBoot 中常用注解 @Controller/@RestController/@RequestMapping 介绍
public class AdminController{
private final AdminService adminService;
private final OptionService optionService;
public AdminController(AdminService adminService,OptionService optionService){
this.adminService = adminService;
this.optionService = optionService;
@GetMapping(value = "/is_installed")
https://www.jdon.com/springboot/requestmap.html  @RequestMapping 与 @GetMapping 和 @PostMapping 等新注释
@ApiOperation("Check install status")
//https://blog.csdn.net/u011217058/article/details/68926295  spring 接口文档注解：@ApiOperation
public boolean isInstall(){
return optonService.getByPropertyOrDefault(PrimaryProperties.IS_INSTALLED,Boolean.class,false);
@PostMapping("login")
@ApiOperation("Login")
@CacheLock(autoDelete =false)
https://www.cnblogs.com/carrychan/p/9431137.html   Springboot 分布式锁实践（redis）
public AuthToken auth(@RequestBody){
return adminService.authenticate(loginParam);
}
@PostMapping("logout")
@ApiOperation("Logs out(Clear session)")
@CacheLock(autoDelete = false)
public void logout(){
adminService.clearToken();
}
@PostMapping("Password/code")
@ApiOperation("Send reset password verify code.")
public void sendResetCode(@RequestBody @Valid resetPasswordParam param){
adminService.sendResetPasswordCode(param);

}
@PostMapping("refresh/{refreshToken}")
@ApiOperation("Refreshes token")
@CacheLock(autoDelete)
public AuthToken refresh(@PathVAriable("refreshToken") String refreshToken){
//https://blog.csdn.net/walkerJong/article/details/7946109  @RequestParam @RequestBody @PathVariable 等参数绑定注解详解
return adminService.refreshToken(refreshToken);
}



}
}


}
