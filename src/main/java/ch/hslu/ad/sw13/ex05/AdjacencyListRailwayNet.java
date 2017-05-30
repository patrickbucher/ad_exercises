package ch.hslu.ad.sw13.ex05;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ch.hslu.ad.sw13.RailwayNet;

public class AdjacencyListRailwayNet implements RailwayNet {

    private final Map<String, Map<String, Integer>> connections;
    private int connectionCount = 0;

    public AdjacencyListRailwayNet(String... stations) {
        connections = new HashMap<>();
        for (String station : stations) {
            connections.put(station, new HashMap<>());
        }
    }

    @Override
    public void addConnection(String from, String to, int duration) {
        connections.get(from).put(to, duration);
        connections.get(to).put(from, duration);
        connectionCount++;
    }

    @Override
    public int getStationCount() {
        return connections.size();
    }

    @Override
    public int getConnectionCount() {
        return connectionCount;
    }

    @Override
    public boolean hasConnectionBetween(String a, String b) {
        Map<String, Integer> from = connections.get(a);
        if (from != null) {
            return from.containsKey(b);
        }
        return false;
    }

    @Override
    public Collection<String> getDirectlyConnectedStations(String station) {
        return connections.get(station).keySet();
    }

    @Override
    public int getDuration(String from, String to) {
        if (connections.containsKey(from)) {
            if (connections.get(from).containsKey(to)) {
                return connections.get(from).get(to);
            }
        }
        return 0;
    }

}
