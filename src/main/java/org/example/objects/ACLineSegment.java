package org.example.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.data.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ACLineSegment {
    private String mRID;
    private String name;
    private floatAttribut length;
    private floatAttribut r;
    private floatAttribut r0;
    private floatAttribut x;
    private floatAttribut x0;
    private floatAttribut bch;
    private floatAttribut b0ch;
    private String baseVoltageId;
//    private String equipment;
    //???
}
