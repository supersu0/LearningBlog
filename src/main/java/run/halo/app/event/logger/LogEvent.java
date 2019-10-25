package run.halo.app.event.logger
import org.springframework.context.ApplicationEvent;
//https://blog.csdn.net/u014231523/article/details/76241152  ApplicationEvent 和 ApplicationListener 的使用
import run.halo.app.model.enums.LogType;
import run.halo.app.model.params.LogParam;
import run.halo.app.utils.ValidationUtils;

public class LogEvent extends ApplicationEvent{
private final LogParam logParam;
/*Create a new ApplicationEvent.
  @param source the object on which the event initially occured (never {@code null}) source就是事件源嘛
  @param logParam login param
public LogEvent(Object source,LogParam logParam){
super(source);
//Validate the log param
ValidationUntils.validate(logParam);
this.logParam= logParam;
}
public LogEvent(Object source,String logKey,LogType logType,String content){
this(source,new LogParam(logKey,logType,content));
}
