package skypro.recordforsocks.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import skypro.recordforsocks.component.RecordMapper;
import skypro.recordforsocks.dto.RecordSocks;
import skypro.recordforsocks.entity.Socks;
import skypro.recordforsocks.exception.*;
import skypro.recordforsocks.repository.SocksRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SocksService {

    private final SocksRepository socksRepository;
    private final RecordMapper recordMapper;

    private SocksService(SocksRepository socksRepository,
                         RecordMapper recordMapper) {
        this.socksRepository = socksRepository;
        this.recordMapper = recordMapper;
    }

    public ResponseEntity<RecordSocks> addSocks(RecordSocks recordSocks) {
        checkSocks(recordSocks.getColor(), recordSocks.getCottonPart());
        Socks socks = recordMapper.toEntity(recordSocks);
        Socks newSocks;
        if (socksRepository.existsSocksByColorIgnoreCaseAndCottonPartEquals(socks.getColor().toString(), socks.getCottonPart())) {
            newSocks = socksRepository.findByColorIgnoreCaseAndCottonPartEquals(socks.getColor().toString(), socks.getCottonPart());
            newSocks.setSocksCount(newSocks.getSocksCount() + socks.getSocksCount());
            socksRepository.save(newSocks);
        } else {
            socksRepository.save(socks);
        }
        return ResponseEntity.ok(recordSocks);
    }

    public ResponseEntity<RecordSocks> outcomeSocks(RecordSocks recordSocks) {
        checkSocks(recordSocks.getColor(), recordSocks.getCottonPart());
        Socks socks = recordMapper.toEntity(recordSocks);
        Socks newSocks;
        if (socksRepository.existsSocksByColorIgnoreCaseAndCottonPartEquals(socks.getColor().toString(), socks.getCottonPart())) {
            newSocks = socksRepository.findByColorIgnoreCaseAndCottonPartEquals(socks.getColor().toString(), socks.getCottonPart());
            if (newSocks.getSocksCount() - socks.getSocksCount() > 0) {
                newSocks.setSocksCount(newSocks.getSocksCount() - socks.getSocksCount());
                socksRepository.save(newSocks);
            } else {
                throw new NegativeSocksCountException();
            }
        } else {
            throw new SocksNotExistInDBException();
        }
        return ResponseEntity.ok(recordSocks);
    }

    public ResponseEntity<Integer> getSocks(String color, String operation, int cottonPart) {
        checkSocks(color, cottonPart);
        checkOperation(operation);
        checkSocksExisting(color, operation, cottonPart);

        Integer socksCount = 0;
        List<Socks> newSocksList = new ArrayList<>();
        Socks newSocks = new Socks();
        if (operation.equalsIgnoreCase("moreThan")) {
            for (Socks s: socksRepository.findByColorIgnoreCaseAndCottonPartGreaterThan(color, cottonPart)) {
                socksCount+= s.getSocksCount();
            }
        } else if (operation.equalsIgnoreCase("lessThan")) {
            newSocksList.addAll(socksRepository.findByColorIgnoreCaseAndCottonPartLessThan(color.toUpperCase(), cottonPart));
            for (Socks s: newSocksList) {
                socksCount+= s.getSocksCount();
            }
        } else {
            newSocks = socksRepository.findByColorIgnoreCaseAndCottonPartEquals(color.toUpperCase(), cottonPart);
            socksCount = newSocks.getSocksCount();
        }

        return ResponseEntity.ok(socksCount);
    }

    private void checkSocks(String color, int cottonPart) {
        if (cottonPart > 100) {
            throw new CottonsPartContentNotExistException();
        }
    }

    private void checkOperation(String operation) {
        if (!(operation.equalsIgnoreCase("moreThan") || operation.equalsIgnoreCase("lessThan") || operation.equalsIgnoreCase("equal"))) {
            throw new WrongRequestOperation();
        }
    }

    private void checkSocksExisting(String color, String operation, int cottonPart) {
        if (operation.equalsIgnoreCase("moreThan")) {
            if (!socksRepository.existsSocksByColorIgnoreCaseAndCottonPartGreaterThan(color, cottonPart)) {
                throw new SocksNotExistInDBException();
            }
        } else if (operation.equalsIgnoreCase("lessThan")) {
            if (!socksRepository.existsSocksByColorIgnoreCaseAndCottonPartLessThan(color, cottonPart)) {
                throw new SocksNotExistInDBException();
            }
        } else {
            if (!socksRepository.existsSocksByColorIgnoreCaseAndCottonPartEquals(color, cottonPart)) {
                throw new SocksNotExistInDBException();
            }
        }
    }



}
