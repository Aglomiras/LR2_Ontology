package org.example.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.data.floatAttribut;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diagram {
    private String mRID;
    private floatAttribut x1InitialView;
    private floatAttribut y1InitialView;
    private floatAttribut x2InitialView;
    private floatAttribut y2InitialView;

    //???
}
