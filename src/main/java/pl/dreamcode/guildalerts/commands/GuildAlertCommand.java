package pl.dreamcode.guildalerts.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.dzikoysk.funnyguilds.basic.user.User;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import pl.dreamcode.guildalerts.Main;
import pl.dreamcode.guildalerts.utils.ColorUtils;

public class GuildAlertCommand implements CommandExecutor {
	
	
	private final Main plugin;
	
	public GuildAlertCommand(Main m) {
		plugin = m;
	}
	
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
    	if (!(sender instanceof Player)) {
            sender.sendMessage("Ta komenda jest przeznaczona tylko dla graczy!");
            return true;
    	}
    	final Player p = (Player)sender;
        final User user = User.get(p);
    	if (args.length < 2) {
    		p.sendMessage(ColorUtils.fixColor(plugin.getConfig().getString("messages.noarg")));
        	return true;
        }
    	if (args.length < 1) {
    		p.sendMessage(ColorUtils.fixColor(plugin.getConfig().getString("messages.noarg")));
    		return true;
    	}
        	if (!user.hasGuild()) {
        	p.sendMessage(ColorUtils.fixColor(plugin.getConfig().getString("messages.noguild")));
        	return true;
    }
        	if (! (user.isOwner() || user.isDeputy())) {
        		p.sendMessage(ColorUtils.fixColor(plugin.getConfig().getString("messages.noleader")));
        		return true;
        	}
        	StringBuilder msg = new StringBuilder();
        	for (int i = 1; i < args.length; i++) {
        	    msg.append(args[i]).append(" ");            
        	}
        	for(net.dzikoysk.funnyguilds.basic.user.User online : user.getGuild().getOnlineMembers()) {
        	    Player po = online.getPlayer();
        	    if (po != null) {
        	        if(args[0].equalsIgnoreCase("chat")) {
        	            po.sendMessage(ColorUtils.fixColor("&4&lALERT GUILD: &7" + msg));
        	            return true;
        	        }
        	        else 
        	        if (args[0].equalsIgnoreCase("actionbar")) {
        	        	po.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(msg.toString()));
        	        	return true;
        	        }
        	        else
        	        if (args[0].equalsIgnoreCase("title")) {
        	        	po.sendTitle("§4§lALERT", msg.toString(), 10, 40, 20);
        	        }
        	        else {
        	        	p.sendMessage(ColorUtils.fixColor("&8» &cPoprawne uzycie: /galert <chat/title/actionbar> (wiadomosc)"));
        	        	return false;
        	        }
        	    }
        	}
        	p.sendMessage(ColorUtils.fixColor(plugin.getConfig().getString("messages.succescommand")));
        	return true;
    }
}
