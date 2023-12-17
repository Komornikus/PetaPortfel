package me.hikemc.data;

public class Statystyki {

    @SuppressWarnings({"all"})

    private String UUID;

    @SuppressWarnings({"all"})
    private double players_money;


    @SuppressWarnings({"all"})

    public Statystyki(String uuid, double players_money) {
        this.UUID = uuid;
        this.players_money = players_money;
    }

    public String getPlayerUUID() {
        return UUID;
    }

    @SuppressWarnings({"all"})

    public void setPlayerUUID(String playerUUID) {
        this.UUID = playerUUID;
    }

    public double getPlayers_money() {
        return players_money;
    }

    public double setPlayers_money(double players_money) {
        this.players_money = players_money;

        return players_money;
    }
}
