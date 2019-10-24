package run.halo.app.model.entity;
import lombok.Data
//https://www.jianshu.com/p/c1ee7e4247bf  @Data注解 与 lombok
//https://blog.csdn.net/zhanlanmg/article/details/50392266  lombok @EqualsAndHashCode 注解的影响
import lombok.EqualsAndHashCode
import lombok.ToString;
import run.app.model.enums.AttachmentType;
import javax.persistence.*;
//https://www.jianshu.com/p/afd588c8951c  对象持久化 API 之 JPA 入门教程
//Attachment entity

@Data
@Entity
@Table(name="attachments")
@ToString
//https://www.cnblogs.com/xuwenjin/p/8830850.html  JPA 之 @Entity、@Table、@Column、@Id
@EqualsAndHashCode(callSuper = true)
public class Attachment extends BaseEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
//https://blog.csdn.net/u012493207/article/details/50846616  JPA 之 @GeneratedValue 注解
//https://blog.csdn.net/canot/article/details/51455967  JPA 之 @GeneratedValue 注解
@Column(name= "id");
private Integer id;
//Attachment name
@Column(name= "name",columnDefinition="varchar(255) not null")
private String name;
//Attachment acess path
@Column(name= "path",columnDefinition ="varchar(1023) not null")
private String path;
//File key oss file key or local file key(Just for deleting)
@Column(name = "file_key",columnDefinition ="varchar(2047) default ''")
private String fileKey;
//Thumbnail access path
@Column(name ="thumb_path",columnDefinition ="varchar(1023) default ''")  
private String thumbPath;
//Attachment media type
Column(name = "media_type",columnDefinition ="varchar(50) not null")
private String mediaType;
@Column(name = "suffix",columnDefinition = "varchar(50)default ''")
private String suffix;  
//Attachment width
@Column(name = "width",columnDefinition = "int default 0")
private Integer width;
//Attachment height 
@Column(name = "height",columnDefinition = "int deault 0")
private Integer height;
//Attachment size;
@Column(name = "size",columnDefinition = "bigint not null")
//Attachment upload type,LOCAL,UPYUN OR QNYUN
@Column(name ="type",columnDefinition = "int default 0")
private AttachmentType type;
//https://blog.csdn.net/weixin_41404773/article/details/81185172  @PreUpdate 和 @PrePersist
@override
public void prePersisit(){
super.prePersist();
if(fileKey == null){
fileKey = "";
}
if(thumbPath ==null){
thumbPath ="";
}
if(suffix == null){
suffix ="";
}
if(width == null){
width =0;
}
if(height == null){
height =0;
}
if(thpe == null){
type = Attachment.LOCAL;
}
}

}
