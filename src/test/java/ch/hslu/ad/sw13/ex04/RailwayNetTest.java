package ch.hslu.ad.sw13.ex04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.hslu.ad.sw13.RailwayNet;
import ch.hslu.ad.sw13.RailwayNetTestUtils;
import nl.jqno.equalsverifier.internal.prefabvalues.Tuple;

public class RailwayNetTest {

    private RailwayNet net;
    private int stationCount;
    private int connectionCount;

    @Before
    public void init() {
        net = RailwayNetTestUtils.createAdjacencyMatrixRailwayNet();
        stationCount = RailwayNetTestUtils.getStationCount();
        connectionCount = RailwayNetTestUtils.getConnectionCount();
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
