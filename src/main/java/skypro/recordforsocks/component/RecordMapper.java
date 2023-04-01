package skypro.recordforsocks.component;

import org.springframework.stereotype.Component;
import skypro.recordforsocks.dto.RecordSocksBatch;
import skypro.recordforsocks.dto.RecordSocks;
import skypro.recordforsocks.entity.Socks;
import skypro.recordforsocks.entity.SocksBatch;

@Component
public class RecordMapper {

    public Socks toEntity(RecordSocks record) {
        Socks entity = new Socks();
        entity.setColor(record.getColor());
        entity.setCottonPart(record.getCottonPart());
        return entity;
    }

    public RecordSocks toRecord(Socks entity) {
        RecordSocks record = new RecordSocks();
        record.setColor(entity.getColor());
        record.setCottonPart(entity.getCottonPart());
        return record;
    }

    public SocksBatch toEntity(RecordSocksBatch recordBatch) {
        SocksBatch entityBatch = new SocksBatch();
        Socks entitySocks = new Socks();
        entitySocks.setColor(recordBatch.getColor());
        entitySocks.setCottonPart(recordBatch.getCottonPart());
        entityBatch.setSocks(entitySocks);
        entityBatch.setSocksCount(recordBatch.getSocksCount());
        return entityBatch;
    }

    public RecordSocksBatch toRecord(SocksBatch entityBatch) {
        RecordSocksBatch recordBatch = new RecordSocksBatch();
        recordBatch.setColor(entityBatch.getSocks().getColor());
        recordBatch.setCottonPart(entityBatch.getSocks().getCottonPart());
        recordBatch.setSocksCount(entityBatch.getSocksCount());
        return recordBatch;
    }


}
