package me.enderkill98.notabackdoor;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Utils {
    public static boolean isBungee() {
        File f = new File("spigot.yml");
        FileConfiguration spigotCfg = new YamlConfiguration().loadConfiguration(f);
        if (f.exists()) return spigotCfg.getBoolean("settings.bungeecord");
        return false;
    }
}
