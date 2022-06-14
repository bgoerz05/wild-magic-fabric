package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.enchantments.WildMagicCurse;
import net.fabricmc.example.statusEffects.Burning;
import net.fabricmc.example.statusEffects.Sticky;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WildMagic implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("wildmagic");

	private static Enchantment WILD_MAGIC = new WildMagicCurse();

	public static final StatusEffect BURNING = new Burning();
	public static final StatusEffect STICKY = new Sticky();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Registry.register(Registry.STATUS_EFFECT, new Identifier("wildmagic", "burning"), BURNING);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("wildmagic", "sticky"), STICKY);
		Registry.register(Registry.ENCHANTMENT, new Identifier("wildmagic", "wildmagic"), WILD_MAGIC);
		LOGGER.info("Hello Fabric world!");
	}
}
