package net.fabricmc.example.statusEffects;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.math.BlockPos;

public class Burning extends StatusEffect {

    public Burning() {
        super(StatusEffectCategory.HARMFUL, 0xc97e1c);
    }
    
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        BlockPos pos = entity.getBlockPos();
        BlockState state = AbstractFireBlock.getState(entity.world, pos);
        if (entity.world.getBlockState(pos).isAir() && state.canPlaceAt(entity.world, pos)) {
            entity.world.setBlockState(pos, state);
        }
    }

}
