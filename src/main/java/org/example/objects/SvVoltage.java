package org.example.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.data.floatAttribut;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SvVoltage {
    private String mRID;
    private floatAttribut v;
    private floatAttribut angle;
    //???
}
