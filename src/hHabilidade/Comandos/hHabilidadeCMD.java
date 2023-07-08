package hHabilidade.Comandos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import hHabilidade.Main.Main;
import hHabilidade.Utils.Inventarios;
import hHabilidade.Utils.Item;

public class hHabilidadeCMD implements CommandExecutor{

	static String skull_item_url = "http://textures.minecraft.net/texture/efa75995ce53bd369ed73615bf323e14a9cd7788ca5acb65b0b1af5645ddd091";
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("habilidade")) {
			
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (args.length == 0) {
					Inventarios.hSkillsMenuMain(p);
					return true;
				}
				
				if (!p.hasPermission("hSkills.admin")) {
					p.sendMessage("§cVoce nao tem permissao para executar esse comando");
					return true;
				}
				
				if (args.length == 1) {
					
					if (args[0].equalsIgnoreCase("help")) {
						p.sendMessage(" §eLista de §e§lComandos");
						p.sendMessage("");
						p.sendMessage("§e/habilidade reload");
						p.sendMessage("§e/habilidade help");
						p.sendMessage("§e/habilidade give <jogador> <quantia>");
						p.sendMessage("");
					}else if (args[0].equalsIgnoreCase("reload")) {
						p.sendMessage("§eVoce acaba de recarregar a config com suceso !");
						Main.getInstance().reloadConfig();
						return true;
					}else if (args[0].equalsIgnoreCase("give")) {
						return true;
					}else {
						p.sendMessage("§eComando invalido");
						return true;
					}
				}

				if (args.length == 3) {
					if (args[0].equalsIgnoreCase("give")) {
						String player = args[1];
						Player target = Bukkit.getPlayerExact(player);
						
						if (target == null) {
							p.sendMessage("§eJogador invalido");
							return true;
						}else {
							try {
								float ammount = Float.valueOf(args[2]);
								
								if (ammount < 1000) {
									ArrayList<String> lore = new ArrayList<>();
									lore.add("");
									lore.add("§7Use esse item para comprar habilidades");
									lore.add("§7Quantidade: " + ammount);
									lore.add("");
									
									Item.createSkull(target, skull_item_url, "§7Moedas de habilidade", lore, ammount);
									
								}else if (ammount >= 1000 && ammount < 1000000) {
									int moedas_result = (int) (ammount / 1000);
									ArrayList<String>  lore = new ArrayList<>();
									lore.add("");
									lore.add("§7Use esse item para comprar habilidades");
									lore.add("§7Quantidade: " + moedas_result + "k");
									lore.add("");
									
									Item.createSkull(target, skull_item_url, "§7Moedas de habilidade", lore, ammount);
									
								}else if (ammount >= 1000000 && ammount < 1000000000) {
									int moedas_result = (int) (ammount / 1000000);
									ArrayList<String>  lore = new ArrayList<>();
									lore.add("");
									lore.add("§7Use esse item para comprar habilidades");
									lore.add("§7Quantidade: " + moedas_result + "M");
									lore.add("");
									
									Item.createSkull(target, skull_item_url, "§7Moedas de habilidade", lore, ammount);
									
								}else if (ammount >= 1000000000 && ammount < 100000000000.0) {
									int moedas_result = (int) (ammount / 1000000000);
									ArrayList<String>  lore = new ArrayList<>();
									lore.add("");
									lore.add("§7Use esse item para comprar habilidades");
									lore.add("§7Quantidade: " + moedas_result + "B");
									lore.add("");
									
									Item.createSkull(target, skull_item_url, "§7Moedas de habilidade", lore, ammount);
									
								}else if (ammount >= 100000000000.0 && ammount < 10000000000000.0) {
									int moedas_result = (int) (ammount / 1000000000000.0);
									ArrayList<String>  lore = new ArrayList<>();
									lore.add("");
									lore.add("§7Use esse item para comprar habilidades");
									lore.add("§7Quantidade: " + moedas_result + "T");
									lore.add("");
								
									Item.createSkull(target, skull_item_url, "§7Moedas de habilidade", lore, ammount);
									
								}
							} catch (NumberFormatException e) {
								p.sendMessage("§eAgurmento invalido !");
							}
						}
					}
				}
			}else {
				Bukkit.getConsoleSender().sendMessage("");
				Bukkit.getConsoleSender().sendMessage("§9[hSkills] §eApenas jogadores pode executar esse comando");
				Bukkit.getConsoleSender().sendMessage("");
			}
			
		}																															
		
		return false;
	}

}
