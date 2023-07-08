package hHabilidade.Event;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import hHabilidade.Main.Main;
import hHabilidade.Utils.Config;

public class hHabilidadeBuyEvent implements Listener{

	@SuppressWarnings("deprecation")
	@EventHandler
	void buy(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().equals("§7Habilidades")) {
			e.setCancelled(true);
			
			for (String names : Main.getInstance().getConfig().getConfigurationSection("Habilidades").getKeys(false)) {
				if (e.getCurrentItem().hasItemMeta()) {
					if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
						
						if (e.getCurrentItem().getType() == Material.getMaterial(Config.getHabilidadeId(names))) {
							
							if (Config.hasHabilidade(p, names)) {
								p.sendMessage("§cVoce ja possui essa habilidade !");
								
								ArrayList<String> perms = new ArrayList<>(Config.getHabilidadePerms(names));
								
								for (String perm : perms) {
									
									if (p.hasPermission(perm)) {
										
									}else {
										Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Main.getInstance().getConfig().getString("PermComando.Setar").replace("@player", p.getName()).replace("{perms}", perm));
									}
											
									
								}
								
								return;
							}else {
								if (Config.getMoedasPlayer(p) < Config.getHabilidadeCusto(names)) {
									p.sendMessage("§cVoce nao possui moedas suficientes");
									return;
								}
								
								if (Config.getMoedasPlayer(p) >= Config.getHabilidadeCusto(names)) {
									p.sendMessage("§aVoce acaba de comprar essa habilidade");
									Config.setHabilidade(p, names, true);
									Config.removeMoedas(p, Config.getHabilidadeCusto(names));
									p.closeInventory();
									
									ArrayList<String> perms = new ArrayList<>(Config.getHabilidadePerms(names));
									
									for (String perm : perms) {
										
										if (p.hasPermission(perm)) {
											
										}else {
											Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Main.getInstance().getConfig().getString("PermComando.Setar").replace("@player", p.getName()).replace("{perms}", perm));
										}
												
										
									}
									return;
								}
								
							}
							
						}
						
					}
				}
			}
			
		}
	}
}
