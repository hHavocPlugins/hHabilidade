package hHabilidade.Event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import hHabilidade.Utils.Inventarios;

public class hSkillsInvClickEvent implements Listener {

	@EventHandler
	void click(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
		if (e.getInventory().getTitle().equals("§7Menu de Habilidades")) {
			e.setCancelled(true);
			
			if (e.getCurrentItem().hasItemMeta()) {
				
				if (e.getCurrentItem().getType() == Material.NETHER_STAR) {
					p.closeInventory();
				}
				
				if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {
					
					if (e.getSlot() == 14) {
						Inventarios.hHabilidadeMenu(p);
					}
					
				}
				
				if (e.getCurrentItem().getType() == Material.ARROW) {
					p.closeInventory();
				}
				
			}
			
		}
		
	}
	
}
