package skypro.recordforsocks.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import skypro.recordforsocks.component.RecordMapper;
import skypro.recordforsocks.dto.RecordSocksBatch;
import skypro.recordforsocks.entity.Socks;
import skypro.recordforsocks.entity.SocksBatch;

import skypro.recordforsocks.repository.SocksBatchRepository;

@Service
public class SocksService {

    private final SocksBatchRepository socksBatchRepository;
    private final RecordMapper recordMapper;

    private SocksService(SocksBatchRepository socksBatchRepository,
                         RecordMapper recordMapper) {
        this.socksBatchRepository = socksBatchRepository;
        this.recordMapper = recordMapper;
    }

    public ResponseEntity<RecordSocksBatch> addSocksBatch(RecordSocksBatch recordSocksBatch) {
        SocksBatch batch = recordMapper.toEntity(recordSocksBatch);
        SocksBatch newBatch;
        Socks socks = batch.getSocks();

        if (socksBatchRepository.existsSocksBatchBySocks_ColorEqualsAnd_CottonPartEquals(socks.getColor().toString(), socks.getCottonPart())) {
            newBatch = socksBatchRepository.findSocksBatchBySocks_ColorEqualsAnd_CottonPartEquals(socks.getColor().toString(), socks.getCottonPart());
            newBatch.setSocksCount(newBatch.getSocksCount() + batch.getSocksCount());
            socksBatchRepository.save(newBatch);
        } else {
            socksBatchRepository.save(batch);
        }
        return ResponseEntity.ok(recordSocksBatch);
    }

    public ResponseEntity<RecordSocksBatch> outcomeSocks(RecordSocksBatch recordSocksBatch) {
        SocksBatch batch = recordMapper.toEntity(recordSocksBatch);
        Socks socks = batch.getSocks();
        SocksBatch newBatch;

        if (socksBatchRepository.existsSocksBatchBySocks_ColorEqualsAnd_CottonPartEquals(socks.getColor().toString(), socks.getCottonPart())) {
            newBatch = socksBatchRepository.findSocksBatchBySocks_ColorEqualsAnd_CottonPartEquals(socks.getColor().toString(), socks.getCottonPart());
            newBatch.setSocksCount(newBatch.getSocksCount() - batch.getSocksCount());
            socksBatchRepository.save(newBatch);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(recordSocksBatch);
    }

    public ResponseEntity<Integer> getSocks(String color, String operation, int cottonPart) {
        if (cottonPart > 100) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        SocksBatch socksBatch;

        if (operation.equalsIgnoreCase("moreThan")) {
            socksBatch = socksBatchRepository.findSocksBatchBySocks_ColorEqualsAndCottonPartIsGreaterThan(color, cottonPart);
        } else if (operation.equalsIgnoreCase("lessThan")) {
            socksBatch = socksBatchRepository.findSocksBatchBySocks_ColorEqualsAnd_CottonPartIsLessThan(color, cottonPart);
        } else if (operation.equalsIgnoreCase("equal")) {
            socksBatch = socksBatchRepository.findSocksBatchBySocks_ColorEqualsAnd_CottonPartEquals(color, cottonPart);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(socksBatch.getSocksCount());
    }

}
