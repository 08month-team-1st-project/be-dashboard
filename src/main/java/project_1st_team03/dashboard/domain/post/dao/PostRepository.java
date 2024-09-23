package project_1st_team03.dashboard.domain.post.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project_1st_team03.dashboard.domain.post.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select p from Post p join fetch p.member"
            //,countQuery = "select count(p) from Post p join p.member"
    )
    Page<Post> findPostPage(Pageable pageable);
}
