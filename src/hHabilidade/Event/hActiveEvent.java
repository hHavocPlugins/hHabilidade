package hHabilidade.Event;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import hHabilidade.Utils.Config;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class hActiveEvent implements Listener {

	@EventHandler
	void active(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if (p.getItemInHand().hasItemMeta()) {
			if (p.getItemInHand().getItemMeta().hasDisplayName()) {
				if (p.getItemInHand().getItemMeta().getDisplayName().equals("§7Moedas de habilidade")) {
					if (p.getItemInHand().getType() == Material.SKULL_ITEM) {
						if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
							p.sendMessage("§aMoedas foram adicionados com sucesso a sua conta!");
							
				            ItemStack nmsItem = CraftItemStack.asNMSCopy(p.getItemInHand());
				            NBTTagCompound itemCompound = nmsItem.getTag();
							
							Config.addMoedas(p, Float.valueOf(itemCompound.getString("quantidade")));
							
							if (p.getItemInHand().getAmount() > 1) {
								p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
							}else {
								p.setItemInHand(null);
							}
							
							e.setCancelled(true);
							
						}																							
					}
				}
			}
		}
	}
}
