package run.halo.app.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//https://www.cnblogs.com/mr-wuxiansheng/p/6189383.html  spring-data-jpa Repository 的基本知识
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
//https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/lang/NonNull.html
import run.halo.app.model.entity.Attachment;
import run.halo.app.repository.base.BaseRepository;
import java.util.List;

//Attachment repository
public interface AttachmentRepository extends BaseRepository<Attachment,Integer>,JpaSpecificatinoExcutor<Attachment>{
//Find all attachment media type.
//@return list of media type.
@Query(value="select distinct a.mediaType from Attachment a")
List<String>findAllMediaType();
//Counts by attachment path.
//@param path attachment path must not be blank
//@return count of the given path
long countByPath(@NonNull String path);
}
