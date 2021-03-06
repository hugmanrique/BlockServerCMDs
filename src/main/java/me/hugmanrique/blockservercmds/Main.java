package me.hugmanrique.blockservercmds;

import com.google.common.io.ByteStreams;
import me.hugmanrique.blockservercmds.listeners.PlayerListener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;

/**
 * @author Hugmanrique
 * @since 30/11/2016
 */
public class Main extends Plugin {
    private static final String CONFIG_FILE = "config.yml";

    private Configuration config;

    @Override
    public void onEnable() {
        if (!loadConfig()) {
            return;
        }

        new PlayerListener(this);
    }

    private boolean loadConfig() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        File file = new File(getDataFolder(), CONFIG_FILE);

        if (!file.exists()) {
            try {
                file.createNewFile();

                try (InputStream in = getResourceAsStream(CONFIG_FILE);
                     OutputStream out = new FileOutputStream(file)) {
                    ByteStreams.copy(in, out);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public Configuration getConfig() {
        return config;
    }
}
