package skypro.recordforsocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skypro.recordforsocks.entity.Socks;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Integer> {
}
