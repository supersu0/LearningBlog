package run.halo.app.handler.file;

import combaidubce.auth.DefaultBceCredentials;
//百度智能云BOS
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfirguration;
import com.baidubce.services.bos.model.PutObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
//https://www.cnblogs.com/yangxu6069/p/6228253.html  lang3 StringUtils
import org.springframework.http.MediaType;
import org.springframework,stereotype.Component;
import org.springframework.util.Assert;
https://blog.csdn.net/shenzhen_zsw/article/details/11397597  有关 org.springframework.util.Assert 的介绍
import org.springframework.web.multipart.MultipartFile;
https://blog.csdn.net/sdut406/article/details/85647982  MultipartFile 与 File 的一些事
import run.halo.app.exception.FielOperationException;
import run.halo.app.model.enums.AttachmentType;
import run.halo.app.model.properties.BaiDuYunProperties;
import run.halo.app.modle.support.UplaodResult;
import run.halo.app.service.OptionService;
import run.halo.app.utilsFilenameUtils;
import javax.imgeio.imageIO;
//https://docs.oracle.com/javase/7/docs/api/javax/imageio/ImageIO.html  图片文件读写
import java.awt.image.BufferedImage;
//https://blog.csdn.net/pangesange/article/details/54412049 JAVA 中 BufferedImage 的用法
import java.util.Objects;
//https://segmentfault.com/a/1190000015976479  好用的 java.util.Objects 类
//BaiDuYun file handler
@Slf4j
@Compnent
public class BaiDuYunFileHandler implements FileHandler{
private final OptionService optionService;
public BaiDuYunFileHandler(OptionService optionService){
this.optonService = optionService;

}
@Override
public UploadResult upload(MultipartFile file){
Assert.notnull(file,"Multipart file must not be null");
//Get config
String bosDomain = optionService.getByPropertyOrDefault(BaiDuYunProperties.BOS_DOMAIN,String.class,"");
String bosEndPoint = optionService.getByPropertyOfNonNull(BaiDuYunProperties.BOS_ENDPOINT).toString();
String bosAccessKey = optionService.getByPropertyOfNonNull(BaiDuYunProperties.BOS_ACCESS_KEY).toString();
String bosSecretKey = optionService.getByPropertyOfNonNull(BaiDuYunProperties.BOS_SECRET_KEY).toString();
String bosBucketName = optionService.getByPropertyOfNonNull(BaiDuYunProperties.BOS_BUCKET_NAME).toString();
String bosStylerule = optionService.getByPropertyOrDefault(BaiDuYunProperties.BOS_STYLE_RULE,String.class,"");
String bosThumbnailStyleStylerule = optionService.getBypropertyOrDefault(BaiDuYunProperties.BOS_THUMBNAIL_STYLE_RULE,String.class,"");
String bosSource = StringUtils.join("https://",bosBucketName,"."+bosEndPoint);
BosClientConfiguration config = new BosClientConfiguration();
config.setCredentials(new DefaultBceCredentials(bosAccessKey,bosSecretKey));
config.setEndpoint(bosEndPoint);
//Init OSS client
BosClient client = new BosClient(config);
try{
String basename = FilenameUtils.getBasename(file.getOriginalFilename());
String extention = FilenameUtils.getExtension(file.getOriginalFilename());
String timestamp = String.valueOf(System.currentTimeMillis());
String upFilePath = StingUtils.join(basename,"_",timestamp,"."extension);
String filePath = StingUtils.join(StringUtils.appendIfMssing(StringUtils.isNotBlank(bosDomian)?bosDomain: bosSource,"/"),upFilePath);
//Upload
PutObjectResponse putObjectResponseFromInputStream = client.putObject(bosBucektName,upFilePath,file.getInputStrea());'
if(putBojectResponseFromInputStream == null){
throw new FileOperationException("上传附件"+file.getOriginalFilename()+"到百度云失败")；

}
//Reponse rusult
upLoadresult.uploadResult = new UploadResult();
uploadResult.setFilename(basename);
uploadResult.setFilePath(StringUtils.isBlank(bosStyleRule)?filePath:filePath+bosStylerule);
uploadResult.setKey(upFilePath);
uploadResult.setMediaType(MediaType.valueOf(Objects.requierNonNull(file.getContentType)));
uploadResult.setSuffix(entension);
uploadResult.setSize(file.getSize());

//Handle thumbnail
if(FileHandler.isImageType(uploadResult.getMediaType())){
BufferedImage image = ImageIO.read(file.getInputStream());'
uploadResult.setWidth(image.getWidth());
uploadResult.setHeight(image.getHeight());
uploadResult.setThumbPath(StringUtils.isBalnk(bosThumbnailStyleRule)?filePath:filePath+bosThumbnailStyleRule);

}
ruturn uploadResult;
}  catch(Exception e){
throw new FileOperationException("附件"+file.getOriginalFilename()+"上传（百度云）失败",e);
}finally{
client.shutdown();
}
}
@Override
public void delete(String key){
Assert.notNull(key,"File key must not be blank");

//Get config
String bosEndPoint = optionService.getByPropertyOfNonNull(BaiDuYunProperties.BOS_ENDPOINT).toString();
String bosAccessKey = optionService.getByPropertyOfNonNull(BaiDuYunProperties.BOS_ACCESS_KEY).toString();
String bosSecretKey = optionService.getByPropertyOfNonNull(BaiDuYunProperties.BOS_SECRET_KEY).toString();
String bosBucketName = optionService.getByPropertyOfNonNull(BaiDuYunProperties.BOS_BUCKET_NAME).toString();
BosClientConfiguration config = new BosClientConfiguration();
config.setCredentials(new DefaultBceCredentials(bosAccessKey,bosSecretKey));
config,setEendpoint(bosEndPoint);
//Init OSS client
BosClient client = new BosClient(config);
try{
client.deleteObject(bosBucketName,key);
} catch(Exception e){
throw new FileOperationException("附件"+key+"从百度云删除失败",e);
}finally{
client.shutdown();
}
}
@Override
public boolean supportType(AttachmentType type){
return AttachmentType.BAIDUYUN.equals(type);
}
}
