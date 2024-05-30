package org.example.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.data.floatAttribut;
import org.example.data.integerAttribut;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagramObjectPoint {
    private String mRID;
    private integerAttribut sequenceNumber;
    private floatAttribut xPosition;
    private floatAttribut yPosition;
    //???
}
