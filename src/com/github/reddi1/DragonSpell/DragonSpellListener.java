package com.github.reddi1.DragonSpell;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityCreatePortalEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class DragonSpellListener implements Listener {

	public static DragonSpell plugin;

	public DragonSpellListener(DragonSpell instance) {
		plugin = instance;
	}

	// spawn dragon
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if ((event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
				&& (event.getClickedBlock().getType().equals(Material.JUKEBOX))
				&& (event.getItem().getType().equals(Material.ENDER_PEARL))) {
			if (event.getClickedBlock().getWorld().getEnvironment()
					.equals(Environment.THE_END)) {
				List<LivingEntity> livingEntities = event.getClickedBlock()
						.getWorld().getLivingEntities();
				for (LivingEntity le : livingEntities) {
					if (le.getType().equals(EntityType.ENDER_DRAGON)) {
						return;
					}
				}
				event.getClickedBlock()
						.getWorld()
						.spawnCreature(
								event.getClickedBlock().getLocation()
										.add(0, 64, 0), EntityType.ENDER_DRAGON);
				String msg = event.getPlayer().getDisplayName()
						+ ChatColor.GREEN + " just summoned an Ender Dragon.";
				plugin.getServer().broadcastMessage(msg);
				event.setCancelled(true);
			}
		}
	}

	// prevent block destruction by the dragon
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event) {
		Entity ent = event.getEntity();
		if (ent.getType().equals(EntityType.ENDER_DRAGON)) {
			event.setCancelled(true);
		}
	}

	// prevent portal creation by the dragon
	@EventHandler
	public void onEntityCreatePortal(EntityCreatePortalEvent event) {
		Entity ent = event.getEntity();
		if (ent.getType().equals(EntityType.ENDER_DRAGON)) {
			event.setCancelled(true);
		}
	}

}
