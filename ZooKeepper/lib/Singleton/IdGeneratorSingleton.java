package ZooKeepper.lib.Singleton;

public class IdGeneratorSingleton {
    private static int lastId = 0; 
    public static int getNewId(){
        lastId += 1;
        return lastId;
    }
    
}
