package project_1st_team03.dashboard.domain.post.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project_1st_team03.dashboard.domain.post.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    @Query(value = "select p from Post p join fetch p.member"
            //,countQuery = "select count(p) from Post p join p.member"
            // 본래는 페치조인 jpql 작성 시 카운트 쿼리를 별도로 작성했어야했었음
    )
    Page<Post> findPostPage(Pageable pageable);


    // Question where 조건문이 있을 때는 별도로 count 쿼리가 나가지 않는 이유
    @Query(value = "select p from Post p join fetch p.member m where m.email = :email")
    Page<Post> findPostPageByEmail(@Param("email")String email, Pageable pageable);
}
