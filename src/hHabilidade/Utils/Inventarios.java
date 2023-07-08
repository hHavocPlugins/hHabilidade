package hHabilidade.Utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import hHabilidade.Main.Main;

public class Inventarios {
	
	static String barrel_skull = "http://textures.minecraft.net/texture/bae751770a70a008a03e1397cbc5be8ae3f782986a814f04943cf62a7121bc3f";
	
	static String healt_skull = "http://textures.minecraft.net/texture/aa377679613a115133dddc312a0099419901ec5ab865b94544048874ad6eaf12";
    static String chicken_skull = "http://textures.minecraft.net/texture/aa377679613a115133dddc312a0099419901ec5ab865b94544048874ad6eaf12";	
	
    @SuppressWarnings("deprecation")
	public static void hHabilidadeMenu(Player p) {
    	Inventory inv = Bukkit.createInventory(p, 6*9, "§7Habilidades");
    	
    	for (String names : Main.getInstance().getConfig().getConfigurationSection("Habilidades").getKeys(false)) {
    		
    		if (Config.getHabilidadeTexture(names) == false) {
        		ItemStack item = new ItemStack(Material.getMaterial(Config.getHabilidadeId(names)),1, (short) Config.getHabilidadeData(names));
        		ItemMeta itemm = item.getItemMeta();
        		itemm.setDisplayName(Config.getHabilidadeNome(names));
        		ArrayList<String> lore = new ArrayList<>();
        		for (String str : Config.getHabilidadeLore(names)) {
        			lore.add(str.replace("&", "§"));
        		}
        		
        		if (Config.hasHabilidade(p, names) == true) {
        			lore.add("§7Voce ja possui essa habilidade");
        		}else {
        			lore.add("§7Voce nao possui essa habilidade");
        		}
        		
        		itemm.setLore(lore);
        		item.setItemMeta(itemm);
        		inv.setItem(Config.getHabilidadeSlot(names), item);
    		}else {
    			
        		ArrayList<String> lore = new ArrayList<>();
        		for (String str : Config.getHabilidadeLore(names)) {
        			lore.add(str.replace("&", "§"));
        		}
        		
        		if (Config.hasHabilidade(p, names) == true) {
        			lore.add("§7Voce ja possui essa habilidade");
        		}else {
        			lore.add("§7Voce nao possui essa habilidade");
        		}
        		
    			createSkull(p, inv, Config.getHabilidadeSlot(names), Config.getHabilidadeUrl(names), Config.getHabilidadeNome(names), lore);
    		}
    	}
    	
    	p.openInventory(inv);
    }
    
	public static void hSkillsMenuMain(Player p) {
		Inventory inv = Bukkit.createInventory(p, 3*9, "§7Menu de Habilidades");
		
		ItemStack points = new ItemStack(Material.NETHER_STAR);
		ItemMeta pointsm = points.getItemMeta();
		pointsm.setDisplayName("§7Moedas");
		ArrayList<String> points_lore = new ArrayList<>();
		points_lore.add("");
		points_lore.add("§7Use isso para comprar habilidades");
		
		float moedas = Float.valueOf(Config.getMoedasPlayer(p));

		if (moedas < 1000) {
			points_lore.add("§a| §7Moedas: " + moedas);
		}else if (moedas >= 1000 && moedas < 1000000) {
			int moedas_result = (int) (moedas / 1000);
			points_lore.add("§a| §7Moedas: " + moedas_result + "k");
		}else if (moedas >= 1000000 && moedas < 1000000000) {
			int moedas_result = (int) (moedas / 1000000);
			points_lore.add("§a| §7Moedas: " + moedas_result + "M");
		}else if (moedas >= 1000000000 && moedas < 100000000000.0) {
			int moedas_result = (int) (moedas / 1000000000);
			points_lore.add("§a| §7Moedas: " + moedas_result + "B");
		}else if (moedas >= 100000000000.0 && moedas < 10000000000000.0) {
			int moedas_result = (int) (moedas / 1000000000000.0);
			points_lore.add("§a| §7Moedas: " + moedas_result + "T");
		}
			
		points_lore.add("");
		pointsm.setLore(points_lore);
		points.setItemMeta(pointsm);
		inv.setItem(12, points);
		
		ArrayList<String> barrel_lore = new ArrayList<>();
		barrel_lore.add("");
		barrel_lore.add("§7Melhore suas habilidades");
		barrel_lore.add("§7para desbloquar coisas novas.");
		barrel_lore.add("");
		createSkull(p, inv, 14, barrel_skull, "§7Habilidades", barrel_lore);
		
		ItemStack fechar = new ItemStack(Material.ARROW);
		ItemMeta fecharm = fechar.getItemMeta();
		fecharm.setDisplayName("§7Fechar");
		ArrayList<String> fechar_lore = new ArrayList<>();
		fechar_lore.add("");
		fechar_lore.add("§7Clique §aaqui §7para fechar esse menu");
		fechar_lore.add("");
		fecharm.setLore(fechar_lore);
		fechar.setItemMeta(fecharm);
		inv.setItem(13, fechar);
		
		p.openInventory(inv);
		
	}
	
	@SuppressWarnings("unchecked")
	public static void createSkull(Player p,Inventory inv, int Slot, String url, String displayname,@SuppressWarnings("rawtypes") ArrayList lore) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setDisplayName(displayname);
        GameProfile profile = new GameProfile(UUID.randomUUID(), (String) null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));

        try {
            Field profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, profile);
        } catch (Throwable var6) {
            var6.printStackTrace();
        }
        skullMeta.setLore(lore);
        skull.setItemMeta(skullMeta);
        inv.setItem(Slot, skull);
	}
	
}
