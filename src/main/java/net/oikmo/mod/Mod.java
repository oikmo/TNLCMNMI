package net.oikmo.mod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.oikmo.mod.events.UseBlockHandler;

public class Mod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("tnlcmnmi");
 
	@Override
	public void onInitialize() {
		UseBlockCallback.EVENT.register(new UseBlockHandler());
		LOGGER.info("Started TNLCMNMI!");
	}
	
}