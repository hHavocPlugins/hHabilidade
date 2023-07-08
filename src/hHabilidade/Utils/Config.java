package hHabilidade.Utils;

import java.util.List;

import org.bukkit.entity.Player;

import hHabilidade.Config.hPlayerCFG;
import hHabilidade.Main.Main;

public class Config {
	
	public static Boolean hasHabilidade(Player p, String names) {
		return hPlayerCFG.get().getBoolean(p.getName() + "." + names);
	}
	
	public static void setHabilidade(Player p, String names, Boolean bollean) {
		hPlayerCFG.get().set(p.getName() + "." + names, bollean);
		hPlayerCFG.save();
	}
	
	public static Float getMoedasPlayer(Player p) {
		return (float) hPlayerCFG.get().getDouble(p.getName() + ".moedas");
	}
	
	public static void setMoedas(Player p, float ammount) {
		hPlayerCFG.get().set(p.getName() + ".moedas", Float.valueOf(ammount));
		hPlayerCFG.save();
	}
	
	public static void removeMoedas(Player p, float ammount) {
		hPlayerCFG.get().set(p.getName() + ".moedas", Float.valueOf(getMoedasPlayer(p) - ammount));
		hPlayerCFG.save();
	}
	
	public static void addMoedas(Player p, float ammount) {
		hPlayerCFG.get().set(p.getName() + ".moedas", Float.valueOf(getMoedasPlayer(p) + ammount));
		hPlayerCFG.save();
	}
	
	public static String getHabilidadeNome(String name) {
		return Main.getInstance().getConfig().getString("Habilidades." + name + ".nome").replace("&", "§");
	}
	
	public static List<String> getHabilidadeLore(String name) {
		return Main.getInstance().getConfig().getStringList("Habilidades." + name + ".lore");
	}
	
	public static int getHabilidadeId(String name) {
		return Main.getInstance().getConfig().getInt("Habilidades." + name + ".id");
	}
	
	public static int getHabilidadeData(String name) {
		return Main.getInstance().getConfig().getInt("Habilidades." + name + ".data");
	}

	public static Boolean getHabilidadeTexture(String name) {
		return Main.getInstance().getConfig().getBoolean("Habilidades." + name + ".texture.ativar");
	}
	
	public static String getHabilidadeUrl(String name) {
		return Main.getInstance().getConfig().getString("Habilidades." + name + ".texture.url");
	}
	
	public static Float getHabilidadeCusto(String name) {
		return (float) Main.getInstance().getConfig().getDouble("Habilidades." + name + ".custo");
	}
	
	public static List<String> getHabilidadePerms(String name) {
		return Main.getInstance().getConfig().getStringList("Habilidades." + name + ".permissao");
	}
	
	public static int getHabilidadeSlot(String name) {
		return Main.getInstance().getConfig().getInt("Habilidades." + name + ".slot");
	}
	
}
