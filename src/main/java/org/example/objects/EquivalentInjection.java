package org.example.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.data.ConductingEquipment;
import org.example.data.Equipment;
import org.example.data.floatAttribut;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquivalentInjection {
    private String mRID;
    private String name;
    private floatAttribut r;
    private floatAttribut r2;
    private floatAttribut x;
    private floatAttribut x2;
    private floatAttribut r0;
    private floatAttribut x0;
//    private floatAttribut emfTimeConstant;
//    private ConductingEquipment baseVoltage;
//    private Equipment equipmentContainer;
}
