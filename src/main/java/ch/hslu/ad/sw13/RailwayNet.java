package ch.hslu.ad.sw13;

import java.util.Collection;

public interface RailwayNet {

    void addConnection(String from, String to, int duration);

    int getStationCount();

    int getConnectionCount();

    boolean hasConnectionBetween(String a, String b);

    Collection<String> getDirectlyConnectedStations(String station);

    int getDuration(String from, String to);

}