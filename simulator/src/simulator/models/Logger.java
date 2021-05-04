package simulator.models;

public class Logger {
    private static Logger logger = null;
    private final String fileName = "simulation.txt";

    public static synchronized Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }
}
