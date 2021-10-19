package pl.dreamcode.guildalerts.utils;

import org.bukkit.*;
import org.bukkit.entity.Player;
import java.util.*;

public class ColorUtils
{
    public static String fixColor(final String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static List<String> fixColor(final List<String> s) {
        final List<String> list = new ArrayList<String>();
        for (final String ss : s) {
            list.add(ChatColor.translateAlternateColorCodes('&', ss));
        }
        return list;
    }
    
    public static Boolean sendTitle(Player p, String t, String s) {
        p.sendTitle(fixColor(t), fixColor(s), 10, 40, 10);
        return Boolean.valueOf(false);
      }
    
    public static Boolean sendTitle(Collection<? extends Player> p, String t, String s) {
        p.stream().forEach(cp -> sendTitle(cp, t, s));
        return Boolean.valueOf(false);
      }
}
