package run.halo.app.config.properties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
//在 Spring Boot 中使用 @ConfigurationProperties 注解， @EnableConfigurationProperties
//https://www.cnblogs.com/duanxz/p/4520571.html
import run.halo.app.model.support.HaloConst;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
// Halo configuration properties
@Data 
@ConfigurationProperties("halo")
//"halo"实体类
public class HaloProperties{
//Doc api disabled. (Default is true)
private boolean docDisabled = true;
//Production env. (Default is true)
privae boolean productionEnv = true;
//Authentication enabled
private boolean authEnabled = true;
//Work directory
private String workDir = HaloConst.USER_HOME+"/.Halo/";
public HaloProperties() throws IOException{
// Create work directory if not exist
Files.createDirectiores(Path.get(WorkDir));
}
}
