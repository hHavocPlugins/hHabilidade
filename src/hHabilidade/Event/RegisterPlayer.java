package hHabilidade.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import hHabilidade.Config.hPlayerCFG;
import hHabilidade.Main.Main;

public class RegisterPlayer implements Listener {

	@EventHandler
	void rg(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		for (String names : Main.getInstance().getConfig().getConfigurationSection("Habilidades").getKeys(false)) {
			
			if (hPlayerCFG.get().get(p.getName() + "." + names) == null) {
				hPlayerCFG.get().set(p.getName() + "." + names, false);
				hPlayerCFG.save();
			}
			
		}
		
		if (hPlayerCFG.get().get(p.getName() + ".moedas") == null) {
			hPlayerCFG.get().set(p.getName() + ".moedas", Float.valueOf(0));
			hPlayerCFG.save();
		}	
		
	}
	
}
