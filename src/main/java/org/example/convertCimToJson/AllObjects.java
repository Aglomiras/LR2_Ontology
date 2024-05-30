package org.example.convertCimToJson;

import lombok.Data;
import org.example.objects.*;

import java.util.LinkedList;
import java.util.List;

@Data
public class AllObjects {
    private List<ACLineSegment> acLineSegment = new LinkedList<>();
    private List<BaseFrequency> baseFrequencies = new LinkedList<>();
    private List<BaseVoltage> baseVoltages = new LinkedList<>();
    private List<Breaker> breakers = new LinkedList<>();
    private List<BusbarSection> busbarSections = new LinkedList<>();
    private List<ConnectivityNode> connectivityNodes = new LinkedList<>();
    private List<Diagram> diagrams = new LinkedList<>();
    private List<DiagramObject> diagramObjects = new LinkedList<>();
    private List<DiagramObjectPoint> diagramObjectPoints = new LinkedList<>();
    private List<EnergyConsumer> energyConsumers = new LinkedList<>();
    private List<EquivalentInjection> equivalentInjections = new LinkedList<>();
    private List<Line> lines = new LinkedList<>();
    private List<LoadResponseCharacteristic> loadResponseCharacteristics = new LinkedList<>();
    private List<PowerTransformer> powerTransformers = new LinkedList<>();
    private List<PowerTransformerEnd> powerTransformerEnds = new LinkedList<>();
    private List<Substation> substations = new LinkedList<>();
    private List<SvVoltage> svVoltages = new LinkedList<>();
    private List<Terminal> terminals = new LinkedList<>();
    private List<TopologicalNode> topologicalNodes = new LinkedList<>();
    private List<VoltageLevel> voltageLevels = new LinkedList<>();
}
