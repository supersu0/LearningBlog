package run.halo.app.model.dto.base;
import org.springframework.lang.NonNull;
import static run.halo.app.utils.BeanUtils.updateProperties
//Converter interface for output DTO.
//<b>The implementation type must be equal to DTO type
//@param <DTO>  the implementation class type
//@param <DOMAIN> domain type

public interface OutputConverter<DTO extends OutputConverter<DTO,DOMAIN>,DOMIN>{
//Convert from domain.(shallow)
//@param domain domain data
//@return converted dto data
@SuppressWarnings("unchecked")
@NonNull
default <T extends DTO> T convertFrom(@NonNull DOMAIN domain){
updateProperties(domain,this);
return (T) this;
}
}
