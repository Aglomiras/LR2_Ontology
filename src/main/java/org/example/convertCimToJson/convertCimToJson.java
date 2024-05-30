package org.example.convertCimToJson;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.sail.memory.MemoryStore;
import org.example.data.booleanAttribut;
import org.example.data.floatAttribut;
import org.example.data.integerAttribut;
import org.example.objects.*;

import java.io.File;
import java.io.IOException;

public class convertCimToJson {
    AllObjects allObjects = new AllObjects();

    @SneakyThrows
    public void convertCim(Model model) {
        Repository repository = new SailRepository(new MemoryStore());
        RepositoryConnection connection = repository.getConnection();
        connection.add(model);

        //Вызов запросов
        getSubstationToCim(connection);
        getACLineSegment(connection);
        getBaseFrequency(connection);
        getBaseVoltage(connection);
        getBreaker(connection);
        getBusbarSection(connection);
        getConnectivityNode(connection);
        getDiagram(connection);
        getDiagramObject(connection);
        getDiagramObjectPoint(connection);
        getEnergyConsumer(connection);
        getEquivalentInjection(connection);
        getLine(connection);
        getLoadResponseCharacteristic(connection);
        getPowerTransformer(connection);
        getPowerTransformerEnd(connection);
        getSvVoltage(connection);
        getTerminal(connection);
        getTopologicalNode(connection);
        getVoltageLevel(connection);

        getJson();
    }

    public void getACLineSegment(RepositoryConnection connection) {
        String request = "PREFIX cim: <" + "http://iec.ch/TC57/2016/CIM-schema-cim#" + "> " +
                "SELECT ?mRID ?name ?length ?r ?r0 ?x ?x0 ?bch ?b0ch ?baseUId ?equipId " +
                "WHERE { " +
                " ?t a cim:ACLineSegment ; " +
                " cim:IdentifiedObject.mRID ?mRID ; " +
                " cim:IdentifiedObject.name ?name ; " +
                " cim:Conductor.length ?length ; " +
                " cim:ACLineSegment.r ?r ; " +
                " cim:ACLineSegment.r0 ?r0 ;" +
                " cim:ACLineSegment.x ?x ; " +
                " cim:ACLineSegment.x0 ?x0 ;" +
                " cim:ACLineSegment.bch ?bch ; " +
                " cim:ACLineSegment.b0ch ?b0ch ; " +
                " cim:ConductingEquipment.BaseVoltage ?baseU . " +
                " ?baseU cim:IdentifiedObject.mRID ?baseUId . " +
//                " cim:Equipment.EquipmentContainer ?equip . " +
//                " ?equip cim:IdentifiedObject.mRID ?equipId . " +
                "}";

        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, request);

        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet bindings : result) {
                String mRID = bindings.getValue("mRID").stringValue();
                String name = bindings.getValue("name").stringValue();
                floatAttribut length = new floatAttribut();
                length.setValue(Float.parseFloat(bindings.getValue("length").stringValue()));
                floatAttribut r = new floatAttribut();
                r.setValue(Float.parseFloat(bindings.getValue("r").stringValue()));
                floatAttribut r0 = new floatAttribut();
                r0.setValue(Float.parseFloat(bindings.getValue("r0").stringValue()));
                floatAttribut x = new floatAttribut();
                x.setValue(Float.parseFloat(bindings.getValue("x").stringValue()));
                floatAttribut x0 = new floatAttribut();
                x0.setValue(Float.parseFloat(bindings.getValue("x0").stringValue()));
                floatAttribut bch = new floatAttribut();
                bch.setValue(Float.parseFloat(bindings.getValue("bch").stringValue()));
                floatAttribut b0ch = new floatAttribut();
                b0ch.setValue(Float.parseFloat(bindings.getValue("b0ch").stringValue()));

                String baseU = bindings.getValue("baseUId").stringValue();
//                String equip = bindings.getValue("equipId").stringValue();

                ACLineSegment acLineSegment = new ACLineSegment(mRID, name, length, r, r0, x, x0, bch, b0ch, baseU);
                allObjects.getAcLineSegment().add(acLineSegment);
            }
        }
    }

    public void getBaseFrequency(RepositoryConnection connection) {
        String request = "PREFIX cim: <" + "http://iec.ch/TC57/2016/CIM-schema-cim#" + "> " +
                "SELECT ?mRID ?frequency " +
                "WHERE { " +
                " ?t a cim:BaseFrequency ; " +
                " cim:IdentifiedObject.mRID ?mRID ; " +
                " cim:BaseFrequency.frequency ?frequency ; " +
                "}";

        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, request);

        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet bindings : result) {
                String mRID = bindings.getValue("mRID").stringValue();
                floatAttribut frequency = new floatAttribut();
                frequency.setValue(Float.parseFloat(bindings.getValue("frequency").stringValue()));

                BaseFrequency baseFrequency = new BaseFrequency(mRID, frequency);
                allObjects.getBaseFrequencies().add(baseFrequency);
            }
        }
    }

    public void getBaseVoltage(RepositoryConnection connection) {
        String request = "PREFIX cim: <" + "http://iec.ch/TC57/2016/CIM-schema-cim#" + "> " +
                "SELECT ?mRID ?name ?volt " +
                "WHERE { " +
                " ?t a cim:BaseVoltage ; " +
                " cim:IdentifiedObject.mRID ?mRID ; " +
                " cim:IdentifiedObject.name ?name ; " +
                " cim:BaseVoltage.nominalVoltage ?volt ; " +
                "}";

        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, request);

        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet bindings : result) {
                String mRID = bindings.getValue("mRID").stringValue();
                String name = bindings.getValue("name").stringValue();
                floatAttribut volt = new floatAttribut();
                volt.setValue(Float.parseFloat(bindings.getValue("volt").stringValue()));

                BaseVoltage baseVoltage = new BaseVoltage(mRID, name, volt);
                allObjects.getBaseVoltages().add(baseVoltage);
            }
        }
    }

    public void getBreaker(RepositoryConnection connection) {
        String request = "PREFIX cim: <" + "http://iec.ch/TC57/2016/CIM-schema-cim#" + "> " +
                "SELECT ?mRID ?name ?open ?rate " +
                "WHERE { " +
                " ?t a cim:Breaker ; " +
                " cim:IdentifiedObject.mRID ?mRID ; " +
                " cim:IdentifiedObject.name ?name ; " +
                " cim:Switch.open ?open ; " +
                " cim:Switch.ratedCurrent ?rate ; " +
                "}";

        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, request);

        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet bindings : result) {
                String mRID = bindings.getValue("mRID").stringValue();
                String name = bindings.getValue("name").stringValue();
                booleanAttribut open = new booleanAttribut();
                open.setValue(Boolean.parseBoolean(bindings.getValue("open").stringValue()));
                floatAttribut rate = new floatAttribut();
                rate.setValue(Float.parseFloat(bindings.getValue("rate").stringValue()));

                Breaker breaker = new Breaker(mRID, name, open, rate);
                allObjects.getBreakers().add(breaker);
            }
        }
    }

    public void getBusbarSection(RepositoryConnection connection) {
        String request = "PREFIX cim: <" + "http://iec.ch/TC57/2016/CIM-schema-cim#" + "> " +
                "SELECT ?mRID ?name " +
                "WHERE { " +
                " ?t a cim:BusbarSection ; " +
                " cim:IdentifiedObject.mRID ?mRID ; " +
                " cim:IdentifiedObject.name ?name ; " +
                "}";

        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, request);

        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet bindings : result) {
                String mRID = bindings.getValue("mRID").stringValue();
                String name = bindings.getValue("name").stringValue();

                BusbarSection busbarSection = new BusbarSection(mRID, name);
                allObjects.getBusbarSections().add(busbarSection);
            }
        }
    }

    public void getConnectivityNode(RepositoryConnection connection) {
        String request = "PREFIX cim: <" + "http://iec.ch/TC57/2016/CIM-schema-cim#" + "> " +
                "SELECT ?mRID " +
                "WHERE { " +
                " ?t a cim:ConnectivityNode ; " +
                " cim:IdentifiedObject.mRID ?mRID ; " +
                "}";

        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, request);

        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet bindings : result) {
                String mRID = bindings.getValue("mRID").stringValue();

                ConnectivityNode connectivityNode = new ConnectivityNode(mRID);
                allObjects.getConnectivityNodes().add(connectivityNode);
            }
        }
    }

    public void getDiagram(RepositoryConnection connection) {
        String request = "PREFIX cim: <" + "http://iec.ch/TC57/2016/CIM-schema-cim#" + "> " +
                "SELECT ?mRID ?x1 ?y1 ?x2 ?y2  " +
                "WHERE { " +
                " ?t a cim:Diagram ; " +
                " cim:IdentifiedObject.mRID ?mRID ; " +
                " cim:Diagram.x1InitialView ?x1 ; " +
                " cim:Diagram.y1InitialView ?y1 ; " +
                " cim:Diagram.x2InitialView ?x2 ; " +
                " cim:Diagram.y2InitialView ?y2 ; " +
                "}";

        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, request);

        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet bindings : result) {
                String mRID = bindings.getValue("mRID").stringValue();

                floatAttribut x1 = new floatAttribut();
                x1.setValue(Float.parseFloat(bindings.getValue("x1").stringValue()));
                floatAttribut y1 = new floatAttribut();
                y1.setValue(Float.parseFloat(bindings.getValue("y1").stringValue()));
                floatAttribut x2 = new floatAttribut();
                x2.setValue(Float.parseFloat(bindings.getValue("x2").stringValue()));
                floatAttribut y2 = new floatAttribut();
                y2.setValue(Float.parseFloat(bindings.getValue("y2").stringValue()));

                Diagram diagram = new Diagram(mRID, x1, y1, x2, y2);
                allObjects.getDiagrams().add(diagram);
            }
        }
    }

    public void getDiagramObject(RepositoryConnection connection) {
        String request = "PREFIX cim: <" + "http://iec.ch/TC57/2016/CIM-schema-cim#" + "> " +
                "SELECT ?mRID ?rotation " +
                "WHERE { " +
                " ?t a cim:DiagramObject ; " +
                " cim:IdentifiedObject.mRID ?mRID ; " +
                " cim:DiagramObject.rotation ?rotation ; " +
                "}";

        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, request);

        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet bindings : result) {
                String mRID = bindings.getValue("mRID").stringValue();
                integerAttribut rotation = new integerAttribut();
                rotation.setValue(Integer.parseInt(bindings.getValue("rotation").stringValue()));

                DiagramObject diagramObject = new DiagramObject(mRID, rotation);
                allObjects.getDiagramObjects().add(diagramObject);
            }
        }
    }

    public void getDiagramObjectPoint(RepositoryConnection connection) {
        String request = "PREFIX cim: <" + "http://iec.ch/TC57/2016/CIM-schema-cim#" + "> " +
                "SELECT ?mRID ?seq ?x ?y " +
                "WHERE { " +
                " ?t a cim:DiagramObjectPoint ; " +
                " cim:IdentifiedObject.mRID ?mRID ; " +
                " cim:DiagramObjectPoint.sequenceNumber ?seq ; " +
                " cim:DiagramObjectPoint.xPosition ?x ; " +
                " cim:DiagramObjectPoint.yPosition ?y ; " +
                "}";

        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, request);

        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet bindings : result) {
                String mRID = bindings.getValue("mRID").stringValue();
                integerAttribut seq = new integerAttribut();
                seq.setValue(Integer.parseInt(bindings.getValue("seq").stringValue()));
                floatAttribut x = new floatAttribut();
                x.setValue(Float.parseFloat(bindings.getValue("x").stringValue()));
                floatAttribut y = new floatAttribut();
                y.setValue(Float.parseFloat(bindings.getValue("y").stringValue()));

                DiagramObjectPoint diagramObjectPoint = new DiagramObjectPoint(mRID, seq, x, y);
                allObjects.getDiagramObjectPoints().add(diagramObjectPoint);
            }
        }
    }

    public void getEnergyConsumer(RepositoryConnection connection) {
        String request = "PREFIX cim: <" + "http://iec.ch/TC57/2016/CIM-schema-cim#" + "> " +
                "SELECT ?mRID ?name ?gnd ?pf ?p ?qf ?q " +
                "WHERE { " +
                " ?t a cim:EnergyConsumer ; " +
                " cim:IdentifiedObject.mRID ?mRID ; " +
                " cim:IdentifiedObject.name ?name ; " +
                " cim:EnergyConsumer.grounded ?gnd ; " +
                " cim:EnergyConsumer.pfixed ?pf ; " +
                " cim:EnergyConsumer.p ?p ; " +
                " cim:EnergyConsumer.qfixed ?qf ; " +
                " cim:EnergyConsumer.q ?q ; " +
                "}";

        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, request);

        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet bindings : result) {
                String mRID = bindings.getValue("mRID").stringValue();
                String name = bindings.getValue("name").stringValue();
                booleanAttribut gnd = new booleanAttribut();
                gnd.setValue(Boolean.parseBoolean(bindings.getValue("gnd").stringValue()));
                floatAttribut pf = new floatAttribut();
                pf.setValue(Float.parseFloat(bindings.getValue("pf").stringValue()));
                floatAttribut p = new floatAttribut();
                p.setValue(Float.parseFloat(bindings.getValue("p").stringValue()));
                floatAttribut qf = new floatAttribut();
                qf.setValue(Float.parseFloat(bindings.getValue("qf").stringValue()));
                floatAttribut q = new floatAttribut();
                q.setValue(Float.parseFloat(bindings.getValue("q").stringValue()));

                EnergyConsumer energyConsumer = new EnergyConsumer(mRID, name, gnd, pf, p, qf, q);
                allObjects.getEnergyConsumers().add(energyConsumer);
            }
        }
    }

    public void getEquivalentInjection(RepositoryConnection connection) {
        String request = "PREFIX cim: <" + "http://iec.ch/TC57/2016/CIM-schema-cim#" + "> " +
                "SELECT ?mRID ?name ?r ?r2 ?x ?x2 ?r0 ?x0 " +
                "WHERE { " +
                " ?t a cim:EquivalentInjection ; " +
                " cim:IdentifiedObject.mRID ?mRID ; " +
                " cim:IdentifiedObject.name ?name ; " +
                " cim:EquivalentInjection.r ?r ; " +
                " cim:EquivalentInjection.r2 ?r2 ; " +
                " cim:EquivalentInjection.x ?x ; " +
                " cim:EquivalentInjection.x2 ?x2 ; " +
                " cim:EquivalentInjection.r0 ?r0 ; " +
                " cim:EquivalentInjection.x0 ?x0 ; " +
                "}";

        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, request);

        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet bindings : result) {
                String mRID = bindings.getValue("mRID").stringValue();
                String name = bindings.getValue("name").stringValue();
                floatAttribut r = new floatAttribut();
                r.setValue(Float.parseFloat(bindings.getValue("r").stringValue()));
                floatAttribut r2 = new floatAttribut();
                r2.setValue(Float.parseFloat(bindings.getValue("r2").stringValue()));
                floatAttribut x = new floatAttribut();
                x.setValue(Float.parseFloat(bindings.getValue("x").stringValue()));
                floatAttribut x2 = new floatAttribut();
                x2.setValue(Float.parseFloat(bindings.getValue("x2").stringValue()));
                floatAttribut r0 = new floatAttribut();
                r0.setValue(Float.parseFloat(bindings.getValue("r0").stringValue()));
                floatAttribut x0 = new floatAttribut();
                x0.setValue(Float.parseFloat(bindings.getValue("x0").stringValue()));

                EquivalentInjection equivalentInjection = new EquivalentInjection(mRID, name, r, r2, x, x2, r0, x0);
                allObjects.getEquivalentInjections().add(equivalentInjection);
            }
        }
    }

    public void getLine(RepositoryConnection connection) {
        String request = "PREFIX cim: <" + "http://iec.ch/TC57/2016/CIM-schema-cim#" + "> " +
                "SELECT ?mRID ?name " +
                "WHERE { " +
                " ?t a cim:Line ; " +
                " cim:IdentifiedObject.mRID ?mRID ; " +
                " cim:IdentifiedObject.name ?name ; " +
                "}";

        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, request);

        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet bindings : result) {
                String mRID = bindings.getValue("mRID").stringValue();
                String name = bindings.getValue("name").stringValue();

                Line line = new Line(mRID, name);
                allObjects.getLines().add(line);
            }
        }
    }

    public void getLoadResponseCharacteristic(RepositoryConnection connection) {
        String request = "PREFIX cim: <" + "http://iec.ch/TC57/2016/CIM-schema-cim#" + "> " +
                "SELECT ?mRID ?pV ?qV ?qC " +
                "WHERE { " +
                " ?t a cim:LoadResponseCharacteristic ; " +
                " cim:IdentifiedObject.mRID ?mRID ; " +
                " cim:LoadResponseCharacteristic.pVoltageExponent ?pV ; " +
                " cim:LoadResponseCharacteristic.qVoltageExponent ?qV ; " +
                " cim:LoadResponseCharacteristic.qConstantPower ?qC ; " +
                "}";

        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, request);

        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet bindings : result) {
                String mRID = bindings.getValue("mRID").stringValue();
                floatAttribut pV = new floatAttribut();
                pV.setValue(Float.parseFloat(bindings.getValue("pV").stringValue()));
                floatAttribut qV = new floatAttribut();
                qV.setValue(Float.parseFloat(bindings.getValue("qV").stringValue()));
                floatAttribut qC = new floatAttribut();
                qC.setValue(Float.parseFloat(bindings.getValue("qC").stringValue()));

                LoadResponseCharacteristic loadResponseCharacteristic = new LoadResponseCharacteristic(mRID, pV, qV, qC);
                allObjects.getLoadResponseCharacteristics().add(loadResponseCharacteristic);
            }
        }
    }

    public void getPowerTransformer(RepositoryConnection connection) {
        String request = "PREFIX cim: <" + "http://iec.ch/TC57/2016/CIM-schema-cim#" + "> " +
                "SELECT ?mRID ?name ?gen ?magBaseU ?bmagSat ?magSatFlux " +
                "WHERE { " +
                " ?t a cim:PowerTransformer ; " +
                " cim:IdentifiedObject.mRID ?mRID ; " +
                " cim:IdentifiedObject.name ?name ; " +
                " cim:PowerTransformer.isPartOfGeneratorUnit ?gen ; " +
                " cim:TransformerEnd.magBaseU ?magBaseU ; " +
                " cim:TransformerEnd.bmagSat ?bmagSat ; " +
                " cim:TransformerEnd.magSatFlux ?magSatFlux ; " +
                "}";

        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, request);

        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet bindings : result) {
                String mRID = bindings.getValue("mRID").stringValue();
                String name = bindings.getValue("name").stringValue();
                booleanAttribut gen = new booleanAttribut();
                gen.setValue(Boolean.parseBoolean(bindings.getValue("gen").stringValue()));
                floatAttribut magBaseU = new floatAttribut();
                magBaseU.setValue(Float.parseFloat(bindings.getValue("magBaseU").stringValue()));
                floatAttribut bmagSat = new floatAttribut();
                bmagSat.setValue(Float.parseFloat(bindings.getValue("bmagSat").stringValue()));
                floatAttribut magSatFlux = new floatAttribut();
                magSatFlux.setValue(Float.parseFloat(bindings.getValue("magSatFlux").stringValue()));

                PowerTransformer powerTransformer = new PowerTransformer(mRID, name, gen, magBaseU, bmagSat, magSatFlux);
                allObjects.getPowerTransformers().add(powerTransformer);
            }
        }
    }

    public void getPowerTransformerEnd(RepositoryConnection connection) {
        String request = "PREFIX cim: <" + "http://iec.ch/TC57/2016/CIM-schema-cim#" + "> " +
                "SELECT ?mRID ?name ?num ?phs ?gnd ?dU ?dS ?g ?b ?x ?r " +
                "WHERE { " +
                " ?t a cim:PowerTransformerEnd ; " +
                " cim:IdentifiedObject.mRID ?mRID ; " +
                " cim:IdentifiedObject.name ?name ; " +
                " cim:TransformerEnd.endNumber ?num ; " +
                " cim:PowerTransformerEnd.phaseAngleClock ?phs ; " +
                " cim:TransformerEnd.grounded ?gnd ; " +
                " cim:PowerTransformerEnd.ratedU ?dU ; " +
                " cim:PowerTransformerEnd.ratedS ?dS ; " +
                " cim:PowerTransformerEnd.g ?g ; " +
                " cim:PowerTransformerEnd.b ?b ; " +
                " cim:PowerTransformerEnd.x ?x ; " +
                " cim:PowerTransformerEnd.r ?r ; " +
                "}";

        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, request);

        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet bindings : result) {
                String mRID = bindings.getValue("mRID").stringValue();
                String name = bindings.getValue("name").stringValue();
                integerAttribut num = new integerAttribut();
                num.setValue(Integer.parseInt(bindings.getValue("num").stringValue()));
                integerAttribut phs = new integerAttribut();
                phs.setValue(Integer.parseInt(bindings.getValue("phs").stringValue()));
                booleanAttribut gnd = new booleanAttribut();
                gnd.setValue(Boolean.parseBoolean(bindings.getValue("gnd").stringValue()));
                floatAttribut dU = new floatAttribut();
                dU.setValue(Float.parseFloat(bindings.getValue("dU").stringValue()));
                floatAttribut dS = new floatAttribut();
                dS.setValue(Float.parseFloat(bindings.getValue("dS").stringValue()));
                floatAttribut g = new floatAttribut();
                g.setValue(Float.parseFloat(bindings.getValue("g").stringValue()));
                floatAttribut b = new floatAttribut();
                b.setValue(Float.parseFloat(bindings.getValue("b").stringValue()));
                floatAttribut x = new floatAttribut();
                x.setValue(Float.parseFloat(bindings.getValue("x").stringValue()));
                floatAttribut r = new floatAttribut();
                r.setValue(Float.parseFloat(bindings.getValue("r").stringValue()));

                PowerTransformerEnd powerTransformerEnd = new PowerTransformerEnd(mRID, name, num, phs, gnd, dU, dS, g, b, x, r);
                allObjects.getPowerTransformerEnds().add(powerTransformerEnd);
            }
        }
    }

    public void getSubstationToCim(RepositoryConnection connection) {
        String request = "PREFIX cim: <" + "http://iec.ch/TC57/2016/CIM-schema-cim#" + "> " +
                "SELECT ?mRID ?name " +
                "WHERE { " +
                " ?t a cim:Substation ; " +
                " cim:IdentifiedObject.mRID ?mRID ; " +
                " cim:IdentifiedObject.name ?name ; " +
                "}";

        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, request);

        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet bindings : result) {
                String mRID = bindings.getValue("mRID").stringValue();
                String name = bindings.getValue("name").stringValue();

                Substation substation = new Substation(mRID, name);
                allObjects.getSubstations().add(substation);
            }
        }
    }

    public void getSvVoltage(RepositoryConnection connection) {
        String request = "PREFIX cim: <" + "http://iec.ch/TC57/2016/CIM-schema-cim#" + "> " +
                "SELECT ?mRID ?v ?angle " +
                "WHERE { " +
                " ?t a cim:SvVoltage ; " +
                " cim:IdentifiedObject.mRID ?mRID ; " +
                " cim:SvVoltage.v ?v ; " +
                " cim:SvVoltage.angle ?angle ;" +
                "}";

        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, request);

        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet bindings : result) {
                String mRID = bindings.getValue("mRID").stringValue();
                floatAttribut v = new floatAttribut();
                v.setValue(Float.parseFloat(bindings.getValue("v").stringValue()));
                floatAttribut angle = new floatAttribut();
                angle.setValue(Float.parseFloat(bindings.getValue("angle").stringValue()));

                SvVoltage svVoltage = new SvVoltage(mRID, v, angle);
                allObjects.getSvVoltages().add(svVoltage);
            }
        }
    }

    public void getTerminal(RepositoryConnection connection) {
        String request = "PREFIX cim: <" + "http://iec.ch/TC57/2016/CIM-schema-cim#" + "> " +
                "SELECT ?mRID ?name ?seq " +
                "WHERE { " +
                " ?t a cim:Terminal ; " +
                " cim:IdentifiedObject.mRID ?mRID ; " +
                " cim:IdentifiedObject.name ?name ; " +
                " cim:ACDCTerminal.sequenceNumber ?seq ; " +
                "}";

        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, request);

        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet bindings : result) {
                String mRID = bindings.getValue("mRID").stringValue();
                String name = bindings.getValue("name").stringValue();
                integerAttribut seq = new integerAttribut();
                seq.setValue(Integer.parseInt(bindings.getValue("seq").stringValue()));

                Terminal terminal = new Terminal(mRID, name, seq);
                allObjects.getTerminals().add(terminal);
            }
        }
    }

    public void getTopologicalNode(RepositoryConnection connection) {
        String request = "PREFIX cim: <" + "http://iec.ch/TC57/2016/CIM-schema-cim#" + "> " +
                "SELECT ?mRID " +
                "WHERE { " +
                " ?t a cim:TopologicalNode ; " +
                " cim:IdentifiedObject.mRID ?mRID ; " +
                "}";

        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, request);

        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet bindings : result) {
                String mRID = bindings.getValue("mRID").stringValue();

                TopologicalNode topologicalNode = new TopologicalNode(mRID);
                allObjects.getTopologicalNodes().add(topologicalNode);
            }
        }
    }

    public void getVoltageLevel(RepositoryConnection connection) {
        String request = "PREFIX cim: <" + "http://iec.ch/TC57/2016/CIM-schema-cim#" + "> " +
                "SELECT ?mRID " +
                "WHERE { " +
                " ?t a cim:VoltageLevel ; " +
                " cim:IdentifiedObject.mRID ?mRID ; " +
                "}";

        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, request);

        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet bindings : result) {
                String mRID = bindings.getValue("mRID").stringValue();

                VoltageLevel voltageLevel = new VoltageLevel(mRID);
                allObjects.getVoltageLevels().add(voltageLevel);
            }
        }
    }

    public void getJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(
                new File("src/main/resources/jsonModel.json"), allObjects);
    }
}
