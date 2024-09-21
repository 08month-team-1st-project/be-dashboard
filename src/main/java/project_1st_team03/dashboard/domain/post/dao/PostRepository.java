package project_1st_team03.dashboard.domain.post.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project_1st_team03.dashboard.domain.post.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
