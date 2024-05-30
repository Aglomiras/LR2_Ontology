package org.example.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.data.booleanAttribut;
import org.example.data.floatAttribut;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnergyConsumer {
    private String mRID;
    private String name;
    private booleanAttribut grounded;
    private floatAttribut pfixed;
    private floatAttribut p;
    private floatAttribut qfixed;
    private floatAttribut q;
//    private floatAttribut ratedVoltage;
    //???
}
