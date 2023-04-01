package skypro.recordforsocks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import skypro.recordforsocks.enums.Color;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordSocksBatch {
    private Color color;
    private int cottonPart;
    private int socksCount;
}
