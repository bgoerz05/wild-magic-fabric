package net.fabricmc.example.statusEffects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;

public class Sticky extends StatusEffect{
    
    int tick = 0;

    public Sticky() {
        super(StatusEffectCategory.NEUTRAL, 0xe6c870);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        tick++;
        if(tick >= 11) {
            tick = 0;
            return true;
        } else {
            return false;
        }
        
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        
        if(entity instanceof PlayerEntity){
            entity.world.playSound(null, new BlockPos(entity.getPos()), SoundEvents.BLOCK_HONEY_BLOCK_STEP, SoundCategory.BLOCKS, 3.0f, 1f);
        }

    }

}
