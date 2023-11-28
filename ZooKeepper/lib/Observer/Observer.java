package ZooKeepper.lib.Observer;

import ZooKeepper.lib.Observer.Subject.Actions;

public interface Observer {
    void update(Actions action, Object value);
}