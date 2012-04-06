package com.github.reddi1.DragonSpell;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class DragonSpell extends JavaPlugin {

	private static final Logger log = Logger.getLogger("Minecraft");
	private final DragonSpellListener dragonSpellListener = new DragonSpellListener(
			this);

	public void onDisable() {
		log.info("DragonSpell DISABLED");

	}

	public void onEnable() {
		getServer().getPluginManager()
				.registerEvents(dragonSpellListener, this);
		log.info("DragonSpell STARTED");

	}

}
