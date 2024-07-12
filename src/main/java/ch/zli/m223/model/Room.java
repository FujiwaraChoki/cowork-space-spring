package ch.zli.m223.model;

public interface Room {
    public Long getId();
    public String getName();
    public boolean getInUse();
    public void setToInUse();
}
