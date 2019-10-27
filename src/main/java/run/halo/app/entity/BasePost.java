package run.halo.app.model.entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lomk.ToString;
import run.halo.app.model.enums.PostCreateFrom;
import run.halo.app.model.enums.PostStatus;
import javax.persistence.*;
import java.util.Date;
//Post base entity;
@Data
@Entity(name = "BasePost")
@Table(name = "posts")
@DiscriminatorColumn(name = "type",discriminatorType = DiscriminatorType.INTEGER, columnDefinition =  "int default 0")
//https://blog.csdn.net/jiana227/article/details/46429235   @DiscriminatorColumn 用法
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BasePost extends BaseEntity{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Intege id;
//Post title
@Column(name = "title",columnDefinition = "varchar(100) not null")
private String title;
//Post status
@Column(name = "status",columnDefinition = "int default 1")
private PostStatus status;
//Post url
@Column(name = "url",columnDefinition = "varchar(255) not null",unique = true)


}
