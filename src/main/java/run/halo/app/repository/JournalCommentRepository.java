package run.halo.app.repository;
import org.springframework.data.jpa.repository.Query;
//https://www.cnblogs.com/zj0208/p/6008627.html  @Query 注解的用法 (Spring Data JPA)
import org.springframewprk.lang.NonNull;
import.run.halo.app.model.entity.JournalComment;
import run.halo.app.model.projection.CommentChildrenCountProjection;
import run.halo.app.model.projection.CommentCountProjection;
import java.util.List;
//Journal comment repository.
public interface JournalCommentRepository extends BaseCommentRepository<JournalCommnent>{
@Query("select new run.halo.app.model.projection.CommentCoutProjection(count(comment.id),cment.postId)"+"from JournalComment comment"+"where comment.postID in ?1 group by comment.postId" )
@NonNull
@Override
List<CommentCountProjection>countByPostIds(@NonNull Interable<Intger> postIds)
//https://www.cnblogs.com/keyi/p/5821285.html  Java 迭代 : Iterator 和 Iterable 接口
@Query("select new run.halo.app.model.projection.ComentChildrenCountProjecton(count(comment.id),comment.parentId)"+"from JournalComment comment"+"where comment.parentId in ?1"+"group by comment.parentId")
@NonNull
@Override
List<CommentChildrenCountProjection> findDirectChildrenCount(@NonNull Iterable<Long> commentIds);
}
