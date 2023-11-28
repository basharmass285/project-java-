package ZooKeepper.lib.Observer;

public interface Subject {
    public static enum Actions{
        FEED,
        MEDICINE
    }
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Actions action, Object value);
}