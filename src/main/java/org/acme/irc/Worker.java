package org.acme.irc;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.irc.IRC;
import org.hibernate.jdbc.Work;
import org.jboss.logging.Logger;

@ApplicationScoped
public class Worker extends Thread {

    private final Logger log = Logger.getLogger(Worker.class);

    private IRC irc;

    public Worker() {

    }

    public Worker(IRC irc) {
        this.irc = irc;
    }

    @Override
    public void run() {
        log.info("Trying to start PIRCBotX on thread " + this.getName());
        this.irc.connect();
    }

    public void close() {
        Log.info("Trying to close PIRCBotX");
        this.irc.close();
    }
}
