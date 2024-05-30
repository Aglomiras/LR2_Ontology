package org.example.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.data.floatAttribut;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseVoltage {
    private String mRID;
    private String name;
    private floatAttribut nominalVoltage;
    //???
}
