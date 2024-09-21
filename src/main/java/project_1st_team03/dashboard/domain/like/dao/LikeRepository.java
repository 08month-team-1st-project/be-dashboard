package project_1st_team03.dashboard.domain.like.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project_1st_team03.dashboard.domain.like.domain.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
}
