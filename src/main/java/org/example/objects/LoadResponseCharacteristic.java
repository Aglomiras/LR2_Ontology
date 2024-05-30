package org.example.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.data.floatAttribut;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoadResponseCharacteristic {
    private String mRID;
    private floatAttribut pVoltageExponent;
    private floatAttribut qVoltageExponent;
    private floatAttribut qConstantPower;
}
