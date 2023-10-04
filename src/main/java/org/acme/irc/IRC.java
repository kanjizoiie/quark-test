package org.acme.irc;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.entities.UserObject;
import org.acme.repositories.UserRepository;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.MotdEvent;
import org.pircbotx.hooks.events.UserListEvent;

import java.io.IOException;


@ApplicationScoped
public class IRC extends ListenerAdapter {
    @Inject
    UserRepository userRepository;

    private final PircBotX bot;

    public IRC() {
        //Configure what we want our bot to do
        Configuration configuration = new Configuration
                .Builder()
                .setName("cheeseball")
                .setLogin("cheeseball")
                .setWebIrcUsername("cheeseball")
                .setRealName("[M/27/cheeseball]")
                .addServer("irc.lewdchat.com")
                .addAutoJoinChannel("#ostbollen")
                .addAutoJoinChannel("#nudes")
                .setAutoReconnect(true)
                .addListener(this) //Add our listener that will be called on Events
                .buildConfiguration();
        //Create our bot with the configuration
        this.bot = new PircBotX(configuration);
    }

    public void connect() {
        try {
            //Connect to the server
            this.bot.startBot();
        } catch (IOException | IrcException e) {
            Log.error(e);
        }
    }

    public void close() {
        this.bot.stopBotReconnect();
        this.bot.close();
    }

    @Override
    public void onJoin(JoinEvent event) {
        Log.info(event.getId());
    }

    @Override
    public void onMotd(MotdEvent event) {
        Log.info(event.getMotd());
    }

    @Override
    public void onUserList(UserListEvent event) {
        Log.info("USERLIST: " + event.getUsers());
        Log.info("REPO:" + userRepository.toString());

        var user = event.getUsers().first();
        userRepository.persist(
                new UserObject()
                        .setName(user.getNick())
                        .setRealname(user.getRealName())
        );

        Log.info(userRepository.listAll());
    }

    @Override
    public void onMessage(MessageEvent event) {
        Log.info(event.getMessage());
    }
}
