package de.jonas.beefgames;

import de.jonas.beefgames.listener.JoinListener;
import de.jonas.jlibrary.handler.database.MariaDbHandler;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Die Haupt- und Main-Klasse des Haupt-Plugins des Beefgames Minecraft Server Netzwerks. In dieser Klasse wird das
 * Haupt-Plugin initialisiert und alles nötige wird geladen.
 */
public class Beefgames extends JavaPlugin {

    //<editor-fold desc="STATIC FIELDS">
    /** Der Prefix des Plugins. */
    @Getter
    private static String prefix;
    /** Die Instanz, mit der man auf das {@link Beefgames Plugin} zugreifen kann. */
    @Getter
    private static Beefgames instance;
    //</editor-fold>

    private MariaDbHandler mariaDbHandler;


    //<editor-fold desc="setup and start">
    @Override
    public void onEnable() {
        // load plugin prefix
        loadPrefix();

        // load plugin instance
        instance = this;

        // connect to database
        this.getSLF4JLogger().info("Connecting to database (MariaDB).");
        this.mariaDbHandler = MariaDbHandler.createFromFile(
            "beefgames",
            new File("beefgames.mariadb.properties")
        );

        // load migrations
        this.getSLF4JLogger().info("Loading database migrations.");
        this.mariaDbHandler.executeMigrations(getClass().getClassLoader());

        // register listener
        this.getSLF4JLogger().info("Registering listener.");
        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), this);
    }
    //</editor-fold>


    //<editor-fold desc="disabling and shutdown">
    @Override
    public void onDisable() {
        // disconnect from database
        this.getSLF4JLogger().info("Disconnecting from database (MariaDB).");
        this.mariaDbHandler.getDataSource().close();
    }
    //</editor-fold>


    /**
     * Lädt den Plugin-Prefix.
     */
    private void loadPrefix() {
        prefix = ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "["
            + ChatColor.RED + "Beefgames"
            + ChatColor.DARK_GRAY + ChatColor.BOLD + "]"
            + ChatColor.GRAY + " ";
    }
}
