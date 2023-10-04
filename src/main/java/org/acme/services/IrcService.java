package org.acme.services;

import io.quarkus.logging.Log;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.context.ManagedExecutor;
import jakarta.inject.Inject;
import org.acme.irc.IRC;

@ApplicationScoped
public class IrcService {

    @Inject
    ManagedExecutor exec;

    private IRC irc;

    IrcService() {
        Log.info("IRC SERVICE STARTING...");
    }


    void onStart(@Observes StartupEvent ev) {
        Log.info("The application is starting...");
        this.irc = new IRC();
        exec.execute(() -> {
            irc.connect();
        });
    }

    void onStop(@Observes ShutdownEvent ev) throws InterruptedException {
        Log.info("The application is stopping...");
    }
}
