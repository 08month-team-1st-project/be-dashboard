package project_1st_team03.dashboard.domain.comment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project_1st_team03.dashboard.domain.comment.domain.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
