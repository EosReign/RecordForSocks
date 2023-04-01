package skypro.recordforsocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import skypro.recordforsocks.entity.SocksBatch;

@Repository
public interface SocksBatchRepository extends JpaRepository<SocksBatch, Long> {

    @Query(value = "SELECT socks s FROM socks_batches WHERE color = s.color and s.cotton_part > socks_cotton_part", nativeQuery = true)
    SocksBatch findSocksBatchBySocks_ColorEqualsAndCottonPartIsGreaterThan(@Param("color") String color, @Param("socks_cotton_part") int socks_cottonPart);

    @Query(value = "SELECT socks s FROM socks_batches WHERE color = s.color and s.cotton_part < socks_cotton_part", nativeQuery = true)
    SocksBatch findSocksBatchBySocks_ColorEqualsAnd_CottonPartIsLessThan(String color, int socks_cottonPart);

    @Query(value = "SELECT socks s FROM socks_batches WHERE color = s.color and s.cotton_part = socks_cotton_part", nativeQuery = true)
    SocksBatch findSocksBatchBySocks_ColorEqualsAnd_CottonPartEquals(String color, int socks_cottonPart);

    @Query(value = "SELECT socks s FROM socks_batches WHERE color = s.color and s.cotton_part = socks_cotton_part", nativeQuery = true)
    boolean existsSocksBatchBySocks_ColorEqualsAnd_CottonPartEquals(String color, int socks_cottonPart);

}
