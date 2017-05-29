package ch.hslu.ad.sw13.ex04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nl.jqno.equalsverifier.internal.prefabvalues.Tuple;

public class RailwayNetTest {

    private RailwayNet net;
    private int stationCount;
    private int connectionCount;

    @Before
    public void createRailwayNet() {
        String[] stations = { "Olten", "Zofingen", "Aarau", "Brugg", "Lenzburg", "Wohlen", "Dietikon", "Luzern",
                "Rotkreuz", "Zürich", "Zug", "Arth-Goldau", "Pfäffikon" };
        this.net = new RailwayNet(stations);
        net.addConnection("Olten", "Zürich", 36);
        net.addConnection("Olten", "Aarau", 13);
        net.addConnection("Olten", "Zofingen", 7);
        net.addConnection("Zofingen", "Lenzburg", 34);
        net.addConnection("Zofingen", "Luzern", 35);
        net.addConnection("Aarau", "Brugg", 13);
        net.addConnection("Aarau", "Lenzburg", 8);
        net.addConnection("Brugg", "Lenzburg", 16);
        net.addConnection("Brugg", "Dietikon", 16);
        net.addConnection("Lenzburg", "Luzern", 80);
        net.addConnection("Lenzburg", "Wohlen", 9);
        net.addConnection("Lenzburg", "Zürich", 19);
        net.addConnection("Lenzburg", "Dietikon", 19);
        net.addConnection("Wohlen", "Dietikon", 30);
        net.addConnection("Wohlen", "Rotkreuz", 23);
        net.addConnection("Dietikon", "Zürich", 12);
        net.addConnection("Luzern", "Rotkreuz", 16);
        net.addConnection("Luzern", "Arth-Goldau", 30);
        net.addConnection("Rotkreuz", "Zug", 12);
        net.addConnection("Rotkreuz", "Arth-Goldau", 15);
        net.addConnection("Zürich", "Pfäffikon", 30);
        net.addConnection("Zürich", "Zug", 25);
        net.addConnection("Zug", "Arth-Goldau", 20);
        net.addConnection("Arth-Goldau", "Pfäffikon", 39);
        this.stationCount = stations.length;
        this.connectionCount = 24;
    }

    @Test
    public void testStationCount() {
        Assert.assertEquals(stationCount, net.getStationCount());
    }

    @Test
    public void testConnectionCount() {
        Assert.assertEquals(connectionCount, net.getConnectionCount());
    }

    @Test
    public void testHasConnectionBetweenConnected() {
        List<Tuple<String>> connected = new ArrayList<>();
        connected.add(new Tuple<String>("Olten", "Zürich"));
        connected.add(new Tuple<String>("Pfäffikon", "Arth-Goldau"));
        connected.add(new Tuple<String>("Rotkreuz", "Wohlen"));
        connected.add(new Tuple<String>("Luzern", "Zofingen"));
        for (Tuple<String> t : connected) {
            Assert.assertTrue(net.hasConnectionBetween(t.getRed(), t.getBlack()));
        }
    }

    @Test
    public void testHasConnectionBetweenUnconnected() {
        List<Tuple<String>> unconnected = new ArrayList<>();
        unconnected.add(new Tuple<String>("Olten", "Pfäffikon"));
        unconnected.add(new Tuple<String>("Zofingen", "Zürich"));
        unconnected.add(new Tuple<String>("Luzern", "Brugg"));
        unconnected.add(new Tuple<String>("Dietikon", "Arth-Goldau"));
        for (Tuple<String> t : unconnected) {
            Assert.assertFalse(net.hasConnectionBetween(t.getRed(), t.getBlack()));
        }
    }

    @Test
    public void testGetDirectlyConnectedStations() {
        String[] rotkreuzConnections = new String[] { "Luzern", "Wohlen", "Zug", "Arth-Goldau" };
        Collection<String> connected = net.getDirectlyConnectedStations("Rotkreuz");
        Assert.assertEquals(4, connected.size());
        for (String to : rotkreuzConnections) {
            Assert.assertTrue(connected.contains(to));
        }
    }

    @Test
    public void testGetDurationOltenZuerich() {
        Assert.assertEquals(36, net.getDuration("Olten", "Zürich"));
    }

    @Test
    public void testGetDurationBruggAarau() {
        Assert.assertEquals(13, net.getDuration("Brugg", "Aarau"));
    }

    @Test
    public void testGetDurationRotkreuzArthGoldau() {
        Assert.assertEquals(15, net.getDuration("Rotkreuz", "Arth-Goldau"));
    }

}
