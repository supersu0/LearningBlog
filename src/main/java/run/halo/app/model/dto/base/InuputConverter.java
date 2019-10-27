package run.halo.app.model.dto.base;
import org.springframework.lang.Nullable;
import halo.app.utils.BeanUntils;
import halo.app.utils.ReflectionUtils;
import java.lang.reflect.ParameterizedType;
//https://juejin.im/post/5a266eec6fb9a0451170fcd3   反射就是把 java 类中的各种成分映射成相应的 java 类
https://blog.csdn.net/JustBeauty/article/details/81116144  ParameterizedType 详解
java.lang.reflect 包的浅析
import java.util.Objects;

//Converter interface for input DTO
public interface InputConverter<DOMIN>{
//Convert to domain(shallow)
//@return new domain with same value(not null)
@SuppressWarnings("unchecked")
//https://blog.csdn.net/cfhacker007/article/details/3971357  关于◎SuppressWarnings ("unchecked")
default DOMIN convertTo(){
https://ebnbin.com/2015/12/20/java-8-default-methods/ 。Java 8 默认方法（Default Methods）
//Get parameterized type
ParameterizedType currentType =parameterizedType();
//Assert not equal
Object.requireNonNull(currentType,"cannot fetch actual type because parameterize type is null");
Class<DOMIN> domainClass =(class<DOMIN>) currentType.getActualTypeArguments()[0];
return BeanUtils.transformFrom(this,domainClass);
}
//Update a domain by dto.(shallow)
default void update(DOOMAIN domain){
BeanUtils.updateProperties(this,domain);
}

//Get parameterized type.
default ParameterizedTpe parameterizedType(){
return ReflectionUtils.getParameterizedType(InputConverter.class,this.getClass());
}
}
