package project_1st_team03.dashboard.domain.comment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project_1st_team03.dashboard.domain.comment.domain.Comment;
import project_1st_team03.dashboard.domain.comment.dto.CommentsResponse;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT new project_1st_team03.dashboard.domain.comment.dto.CommentsResponse(c, m.email) FROM Comment c JOIN c.member m")
    Optional<List<CommentsResponse>> findAllWithMemberEmail();
}
