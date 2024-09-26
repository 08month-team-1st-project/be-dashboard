package project_1st_team03.dashboard.domain.comment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project_1st_team03.dashboard.domain.comment.domain.Reply;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Query("SELECT r FROM Reply r JOIN FETCH r.member WHERE r.comment.id = :commentId")
    List<Reply> findRepliesWithMemberByCommentId(@Param("commentId") Long commentId);
}
