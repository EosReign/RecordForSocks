package skypro.recordforsocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skypro.recordforsocks.entity.Socks;

import java.util.List;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Integer> {
    List<Socks> findByColorIgnoreCaseAndCottonPartGreaterThan(String color, int cottonPart);

    List<Socks> findByColorIgnoreCaseAndCottonPartLessThan(String color, int cottonPart);

    Socks findByColorIgnoreCaseAndCottonPartEquals(String Color, int cottonPart);

    boolean existsSocksByColorIgnoreCaseAndCottonPartEquals(String Color, int cottonPart);

    boolean existsSocksByColorIgnoreCaseAndCottonPartGreaterThan(String Color, int cottonPart);

    boolean existsSocksByColorIgnoreCaseAndCottonPartLessThan(String Color, int cottonPart);
}
