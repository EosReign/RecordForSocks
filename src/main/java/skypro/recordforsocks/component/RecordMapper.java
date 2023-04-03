package skypro.recordforsocks.component;

import org.springframework.stereotype.Component;
import skypro.recordforsocks.dto.RecordSocks;
import skypro.recordforsocks.entity.Socks;

@Component
public class RecordMapper {

    public Socks toEntity(RecordSocks record) {
        Socks entity = new Socks();
        entity.setColor(record.getColor().toUpperCase());
        entity.setCottonPart(record.getCottonPart());
        entity.setSocksCount(record.getSocksCount());
        return entity;
    }

    public RecordSocks toRecord(Socks entity) {
        RecordSocks record = new RecordSocks();
        record.setColor(entity.getColor());
        record.setCottonPart(entity.getCottonPart());
        record.setSocksCount(entity.getSocksCount());
        return record;
    }




}
