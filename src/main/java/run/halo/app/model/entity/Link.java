package run.halo.app.model.entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.*;
//Link entity

@Data
@Entity
@Table(name= "links")
@ToString
@EqualsAndHashCode(callSuper =true)


public class Link extends BaseEntity{

@id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Colum(name ="id")
private Integer id;
//Link name
@Column(name ="name",columnDefinition = "varchar(255) not null")
private String name;
//Link webiste address
@Column(name="url",columnDefinition = "varchar(1023) not null")
private String url;
//website logo
@Column(name ="logo",columnDefinition ="varchar(255) default ''")
private String logo
//website description
@Column(name "description",columnDefiniton = "varchar(255) default ''")
private String description;
//link team name
@Column(name = "team",columnDefiniton = "varchar(255) default ''")
private String team;
//Sort
@Column(name ="priority,columnDefinition = int default 0")
private Integer priotity;
@Override
public void prePersist(){
super.prePersist();

id =null;
if(priority == null){
priority =0;
}
if(logo == null){
logo ="";
}
if(description == null){
description = "";
}
if(team == null){
team = "";
}
}
}
