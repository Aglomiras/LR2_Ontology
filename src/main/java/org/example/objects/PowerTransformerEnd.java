package org.example.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.data.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PowerTransformerEnd {
    private String mRID;
    private String name;
    private integerAttribut endNumber;
    private integerAttribut phaseAngleClock;
    private booleanAttribut grounded;
    private floatAttribut ratedU;
    private floatAttribut ratedS;
    private floatAttribut g;
    private floatAttribut b;
    private floatAttribut x;
    private floatAttribut r;
    //???
}
