package pl.dreamcode.guildalerts;

import static org.bukkit.Bukkit.getConsoleSender;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import pl.dreamcode.guildalerts.commands.GuildAlertCommand;

public class Main extends JavaPlugin {
	
    private final static Main instance;	
	
    public void onEnable() {
        PluginDescriptionFile pluginDescriptionFile = getDescription();
        if (!pluginDescriptionFile.getName().contains("DC_GuildAlerts")) {
            getConsoleSender().sendMessage(" >");
            getConsoleSender().sendMessage(" > Wykryto zmiane nazwy pluginu!");
            getConsoleSender().sendMessage(" > -> DC_GuildAlerts v1.0-SNAPSHOT zostal wylaczony!");
            Bukkit.getServer().getPluginManager().disablePlugin((Plugin)this);
            return;
        }
        if (!pluginDescriptionFile.getAuthors().contains("Kamilo")) {
            getConsoleSender().sendMessage(" >");
            getConsoleSender().sendMessage(" > Wykryto zmiane autora pluginu!");
            getConsoleSender().sendMessage(" > -> DC_GuildAlerts v1.0-SNAPSHOT zostal wylaczony!");
            Bukkit.getServer().getPluginManager().disablePlugin((Plugin)this);
            return;
        }
        if (!pluginDescriptionFile.getVersion().contains("1.0-SNAPSHOT")) {
            getConsoleSender().sendMessage(" >");
            getConsoleSender().sendMessage(" > Wykryto zmiane autora pluginu!");
            getConsoleSender().sendMessage(" > -> DC_GuildAlerts v1.0-SNAPSHOT zostal wylaczony!");
            Bukkit.getServer().getPluginManager().disablePlugin((Plugin)this);
            return;
        }
        getConsoleSender().sendMessage("[DC_GuildAlerts] DC_GuildAlerts v1.0-SNAPSHOT");
        getConsoleSender().sendMessage(" >");
        getConsoleSender().sendMessage(" > Twoj serwer posiada wazna licencje do pluginu");
        getConsoleSender().sendMessage(" > -> DC_GuildAlerts v1.0-SNAPSHOT");
        getCommand("galert").setExecutor((CommandExecutor)new GuildAlertCommand(this));
        instance = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
   
    public static Main getInst() {
        return instance;
      }
}
