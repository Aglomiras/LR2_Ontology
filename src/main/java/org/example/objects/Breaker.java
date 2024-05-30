package org.example.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.data.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Breaker {
    private String mRID;
    private String name;
    private booleanAttribut open;
    private floatAttribut ratedCurrent;
    //???

}
