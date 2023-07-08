package hHabilidade.Main;


import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import hHabilidade.Comandos.hHabilidadeCMD;
import hHabilidade.Config.hPlayerCFG;
import hHabilidade.Event.RegisterPlayer;
import hHabilidade.Event.hActiveEvent;
import hHabilidade.Event.hHabilidadeBuyEvent;
import hHabilidade.Event.hSkillsInvClickEvent;

public class Main  extends JavaPlugin {

	public static PluginManager pm = Bukkit.getPluginManager();
	private static Main main;
	public static Main getInstance() {
		return main;
	}
	
	@Override
	public void onEnable() {
		main = this;
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("§9[hHabilidade] §ePlugin esta sendo iniciado");
		Bukkit.getConsoleSender().sendMessage("");
		
		Comandos(); Eventos(); saveDefaultConfig(); hPlayerCFG.setup();
		
	}
	
	void Comandos() {
		getCommand("habilidade").setExecutor(new hHabilidadeCMD());
	}
	
	void Eventos() {
		pm.registerEvents(new hSkillsInvClickEvent(), this);
		pm.registerEvents(new RegisterPlayer(), this);
		pm.registerEvents(new hHabilidadeBuyEvent(), this);
		pm.registerEvents(new hActiveEvent(), this);
	}
	
	@Override
	public void onDisable() {
		main = null;
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("§9[hHabilidade] §ePlugin esta sendo desligado");
		Bukkit.getConsoleSender().sendMessage("");
	}
	
}
