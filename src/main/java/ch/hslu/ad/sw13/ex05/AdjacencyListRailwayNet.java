package ch.hslu.ad.sw13.ex05;

import java.util.Collection;

import ch.hslu.ad.sw13.RailwayNet;

public class AdjacencyListRailwayNet implements RailwayNet {

    public AdjacencyListRailwayNet(String... stations) {
        
    }
    
    @Override
    public void addConnection(String from, String to, int duration) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getStationCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getConnectionCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean hasConnectionBetween(String a, String b) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Collection<String> getDirectlyConnectedStations(String station) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getDuration(String from, String to) {
        // TODO Auto-generated method stub
        return 0;
    }

}
