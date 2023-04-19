package pkprocess;

import k8055n.K8055N;

/**
 * This class implements the Singleton design pattern for the K8055N class.
 * It ensures that only one instance of the K8055N class is created and provides global access to it.
 */
public class K8055NSingleton {
    private static K8055N instance;

    /**
     * Private constructor to prevent instantiation from other classes.
     */
    private K8055NSingleton() {
    }

    /**
     * Returns the single instance of the K8055N class.
     * If the instance does not exist, it creates one.
     *
     * @return the single instance of the K8055N class
     */
    public static synchronized K8055N getInstance() {
        if (instance == null) {
            instance = new K8055N();
        }
        return instance;
    }
}
