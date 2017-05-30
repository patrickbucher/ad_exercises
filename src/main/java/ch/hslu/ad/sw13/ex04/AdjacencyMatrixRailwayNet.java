package ch.hslu.ad.sw13.ex04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ch.hslu.ad.sw13.RailwayNet;

public class AdjacencyMatrixRailwayNet implements RailwayNet {

    private String[] stations;
    private int[][] connections;
    private int connectionCount;

    public AdjacencyMatrixRailwayNet(String... stations) {
        this.stations = stations;
        final int n = this.stations.length;
        connections = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                connections[i][j] = 0;
            }
        }
    }

    /* (non-Javadoc)
     * @see ch.hslu.ad.sw13.ex04.RailwayNetFuckYou#addConnection(java.lang.String, java.lang.String, int)
     */
    @Override
    public void addConnection(String from, String to, int duration) {
        int f = findStationIndex(from);
        int t = findStationIndex(to);
        if (connections[f][t] == 0 && connections[t][f] == 0) {
            connectionCount++;
        }
        connections[f][t] = duration;
        connections[t][f] = duration;
    }

    /* (non-Javadoc)
     * @see ch.hslu.ad.sw13.ex04.RailwayNetFuckYou#getStationCount()
     */
    @Override
    public int getStationCount() {
        return stations.length;
    }

    /* (non-Javadoc)
     * @see ch.hslu.ad.sw13.ex04.RailwayNetFuckYou#getConnectionCount()
     */
    @Override
    public int getConnectionCount() {
        return connectionCount;
    }

    /* (non-Javadoc)
     * @see ch.hslu.ad.sw13.ex04.RailwayNetFuckYou#hasConnectionBetween(java.lang.String, java.lang.String)
     */
    @Override
    public boolean hasConnectionBetween(String a, String b) {
        int i = findStationIndex(a);
        int j = findStationIndex(b);
        return connections[i][j] != 0;
    }

    /* (non-Javadoc)
     * @see ch.hslu.ad.sw13.ex04.RailwayNetFuckYou#getDirectlyConnectedStations(java.lang.String)
     */
    @Override
    public Collection<String> getDirectlyConnectedStations(String station) {
        List<String> connectedStations = new ArrayList<>();
        for (String other : stations) {
            if (hasConnectionBetween(station, other)) {
                connectedStations.add(other);
            }
        }
        return connectedStations;
    }

    /* (non-Javadoc)
     * @see ch.hslu.ad.sw13.ex04.RailwayNetFuckYou#getDuration(java.lang.String, java.lang.String)
     */
    @Override
    public int getDuration(String from, String to) {
        if (hasConnectionBetween(from, to)) {
            int i = findStationIndex(from);
            int j = findStationIndex(to);
            return connections[i][j];
        }
        throw new IllegalStateException("'" + from + "'/'" + to + "' not connected");
    }

    private int findStationIndex(String station) {
        for (int i = 0; i < stations.length; i++) {
            if (stations[i].equals(station)) {
                return i;
            }
        }
        throw new IllegalArgumentException(" station '" + station + "' doesn't exist");
    }
}
