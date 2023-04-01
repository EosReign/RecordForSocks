package skypro.recordforsocks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import skypro.recordforsocks.enums.Color;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordSocks {
    private Color color;
    private int cottonPart;
}
