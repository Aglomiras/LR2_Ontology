package org.example.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.data.EquipmentContainer;
import org.example.data.booleanAttribut;
import org.example.data.floatAttribut;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PowerTransformer {
    private String mRID;
    private String name;
    private booleanAttribut isPartOfGeneratorUnit;
    private floatAttribut magBaseU;
    private floatAttribut bmagSat;
    private floatAttribut magSatFlux;
//    private EquipmentContainer equipmentContainer;
}
