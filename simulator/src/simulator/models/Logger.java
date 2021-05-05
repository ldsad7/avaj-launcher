package simulator.models;

import simulator.exceptions.LoggerException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger implements AutoCloseable {
    private static Logger logger;
    private final String fileName = "simulation.txt";
    private final BufferedWriter writer;

    private Logger() {
        try {
            writer = new BufferedWriter(new FileWriter(fileName, false));
        } catch (IOException e) {
            throw new LoggerException("We weren't able to open the file `" + fileName + "` (" + e + ")");
        }
    }

    public static synchronized Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public void log(String message) {
        try {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            throw new LoggerException("We weren't able to write to the file `" + fileName + "`, " +
                    "probably because the corresponding writer is closed (" + e + ")");
        }
    }

    @Override
    public void close() throws Exception {
        writer.close();
    }
}
