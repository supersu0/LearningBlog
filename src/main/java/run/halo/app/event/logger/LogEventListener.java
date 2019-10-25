package run.halo.app.envent.logger;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
//https://blog.csdn.net/u014231523/article/details/76272273   @EnableAsync 和 @Async 开始异步任务支持
import org.springframework.stereotype.Component;
//https://www.cnblogs.com/mhc-fly/p/7170607.html  Spring Framework] 学习笔记－－@Component 等 stereotype 的基础
import run.halo.app.model.entity.Log;
import run.halo.app.service.LogService;

//Log event listener
@Component
public class LogEventListener{
private final LogService logService;
public LogEventListener(LogService logService){
this.logService =logService;
}
@EventListener
@Async
//算是理解了：事件在监听器中的方法进行注册，当event被触发时，方法（onApplicationEvent）就会被运行，从而产生log记录
public void onApplicationEvent(LogEvent event){
//Convert to log
Log logToCreate = event.getLogParam().convertTo();
logService.create(logToCreate);
}
}
