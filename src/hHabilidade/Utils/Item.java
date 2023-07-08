package hHabilidade.Utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagString;


public class Item {

	@SuppressWarnings("unchecked")
	public static void createSkull(Player p,String url, String displayname,@SuppressWarnings("rawtypes") ArrayList lore, float ammount) {
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
        p.getInventory().addItem(addNBT(skull, "quantidade", "" + ammount));	
    }
	
	public static ItemStack addNBT(ItemStack item, String key, String value) {
		
		
		net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemCompound = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();
        itemCompound.set(key, new NBTTagString(value));
        nmsItem.setTag(itemCompound);
       
        return CraftItemStack.asBukkitCopy(nmsItem);
    }
	
}
