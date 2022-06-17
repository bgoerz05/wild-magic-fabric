package net.fabricmc.example.statusEffects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class Distorsion extends StatusEffect {

    int tick = 0;
    boolean up = true;
    boolean inPosition = false;

    public Distorsion() {
        super(StatusEffectCategory.HARMFUL, 0x4287f5);
    }
    
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {

        tick++;

        if(tick >= 25) {
            tick = 0;
            if(up) {
                up = false;
                inPosition = true;
            } else {
                up = true;
            }
        } 
        
        return true;

    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(up) {
            if (inPosition) {
                entity.setVelocity(entity.getVelocity().x, 0.1 * amplifier + 0.2, entity.getVelocity().z);
            } else {
                entity.setVelocity(entity.getVelocity().x, 0.1 * amplifier + 0.5, entity.getVelocity().z);
            }
        } else {
            entity.setVelocity(entity.getVelocity().x, -0.1 * amplifier - 0.2, entity.getVelocity().z);
        }
    }

}
