package net.fabricmc.example.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class WildMagicCurse extends Enchantment {

    int randomChoice = 0;

    public WildMagicCurse() {
        super(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }
    
    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {

        randomChoice = (int) Math.random() * 10 + 1;

        switch (randomChoice) {
            case 1:
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 180, 2));
                break;
            case 2:
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 180, 1));
                break;
            case 3:
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 180, 2));
                break;
            case 4:
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 180, 3));
                break;
            case 5:
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 180, 2));
                break;
            case 6:
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 180, 1));
                break;
            case 7:
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 180, 1));
                break;
            case 8:
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 180, 1));
                break;
            case 9:
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 180, 1));
                break;
            case 10:
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 180, 2));
                break;
        }

    }

}
