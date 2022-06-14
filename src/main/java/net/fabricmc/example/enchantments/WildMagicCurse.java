package net.fabricmc.example.enchantments;

import java.util.List;

import net.fabricmc.example.WildMagic;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LargeEntitySpawnHelper;
import net.minecraft.entity.LargeEntitySpawnHelper.Requirements;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.SkeletonHorseEntity;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class WildMagicCurse extends Enchantment {

    int randomChoice = 0;
    boolean isReady = true;

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

        ServerWorld serverWorld = (ServerWorld) user.world;
        ChickenEntity chicken;
        FireballEntity fireball;
        SkeletonHorseEntity skeleHorse;
        EggEntity egg;
        AllayEntity allay;
        LightningEntity bolt;
        ArrowEntity arrow;
        List<Entity> targets;

        if (isReady) {

            randomChoice = (int) Math.floor(Math.random()*30);

            switch (randomChoice) {
                case 0:
                user.addStatusEffect(new StatusEffectInstance(WildMagic.STICKY, 60*20, 1));
                    break;
                case 1:
                    serverWorld.playSound(null, new BlockPos(user.getPos()), SoundEvents.ENTITY_WITHER_DEATH, SoundCategory.HOSTILE, 0.8f, 0.5f);
                    serverWorld.playSound(null, new BlockPos(user.getPos()), SoundEvents.ENTITY_WITHER_DEATH, SoundCategory.HOSTILE, 1.0f, 1f);
                    serverWorld.playSound(null, new BlockPos(user.getPos()), SoundEvents.ENTITY_WITHER_DEATH, SoundCategory.HOSTILE, 1.2f, 2f);
                    break;
                case 2:
                    chicken = (ChickenEntity) EntityType.CHICKEN.create(serverWorld);
                    chicken.setPos((user.getPos().x + target.getPos().x) / 2, (user.getPos().y + target.getPos().y) / 2 + 1, (user.getPos().z + target.getPos().z) / 2);
                    serverWorld.spawnEntity(chicken);
                    break;
                case 3:
                    fireball = (FireballEntity) EntityType.FIREBALL.create(serverWorld);
                    fireball.setPos(user.getPos().x, user.getPos().y + 3, user.getPos().z);
                    fireball.setVelocity(0, -1, 0);
                    serverWorld.spawnEntity(fireball);
                    break;
                case 4:
                    for(int i = 0; i < 12; i++) {
                        arrow = (ArrowEntity) EntityType.ARROW.create(serverWorld);
                        arrow.setPos(user.getPos().x, user.getPos().y + 2, user.getPos().z);
                        arrow.setVelocity(Math.random() - 0.5, 0.5, Math.random() - 0.5);
                        serverWorld.spawnEntity(arrow);
                    }
                    break;
                case 5:
                    skeleHorse = (SkeletonHorseEntity) EntityType.SKELETON_HORSE.create(serverWorld);
                    skeleHorse.setPos(Math.random() * 4 + user.getPos().x - 2, user.getPos().y + 1, Math.random() * 4 + user.getPos().z - 2);
                    serverWorld.spawnEntity(skeleHorse);
                    break;
                case 6:
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60*20, 1));
                    break;
                case 7:
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 60*20, 1));
                    break;
                case 8:
                    for(int i = 0; i < 12; i++) {
                        egg = (EggEntity) EntityType.EGG.create(serverWorld);
                        egg.setPos(user.getPos().x, user.getPos().y + 3, user.getPos().z);
                        egg.setVelocity(Math.random() / 2 - 0.25, 0.5, Math.random() / 2 - 0.25);
                        serverWorld.spawnEntity(egg);
                    }
                    break;
                case 9:
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 60*20, 1));
                    break;
                case 10:
                    LargeEntitySpawnHelper.trySpawnAt(EntityType.WARDEN, SpawnReason.TRIGGERED, serverWorld, new BlockPos(user.getPos()), 20, 5, 6, Requirements.WARDEN).isPresent();
                    break;
                case 11:
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 60*20, 1));
                    break;
                case 12:
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 60*20, 1));
                    break;
                case 13:
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 60*20, 1));
                    break;
                case 14:
                    user.clearStatusEffects();
                    break;
                case 15:
                    user.teleport(user.getPos().x, user.getPos().y + 60, user.getPos().z);
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 170, 1));
                    break;
                case 16:
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 60*20, 1));
                    break;
                case 17:
                    for(int i = 0; i < Math.floor(Math.random() * 5 + 1); i++) {
                        allay = (AllayEntity) EntityType.ALLAY.create(serverWorld);
                        allay.setPos(Math.random() * 4 + user.getPos().x - 2, user.getPos().y + 1, Math.random() * 4 + user.getPos().z - 2);
                        serverWorld.spawnEntity(allay);
                    }
                    break;
                case 18:
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 1));
                    break;
                case 19:
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 6*20, 4));
                    break;
                case 20:
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 18*20, 1));
                    break;
                case 21:
                    user.addStatusEffect(new StatusEffectInstance(WildMagic.BURNING, 18*20, 1));
                    break;
                case 22:
                    for(int i = 0; i < 16; i++) {
                        user.dropItem((ItemConvertible)Items.ENDER_PEARL, 3);
                    }
                    break;
                case 23:
                for(int i = 0; i < 3; i++) {
                    bolt = (LightningEntity) EntityType.LIGHTNING_BOLT.create(serverWorld);
                    bolt.setPos(Math.random() * 12 + user.getPos().x - 6, user.getPos().y + 1, Math.random() * 12 + user.getPos().z - 6);
                    serverWorld.spawnEntity(bolt);
                }
                    break;
                case 24:
                    targets = serverWorld.getOtherEntities(user, new Box(user.getPos().x - 10, user.getPos().y - 10, user.getPos().z - 10, user.getPos().x + 10, user.getPos().y + 10, user.getPos().z + 10));
                    for (int i = 0; i < targets.size(); i++) {
                        if (targets.get(i) instanceof LivingEntity) {
                            ((LivingEntity)targets.get(i)).addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 30*20, 1));
                        }
                    }
                    break;
                case 25:
                    if(target instanceof LivingEntity) {
                        ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 20*20, 1));
                    }
                    break;
                case 26:
                    if(target instanceof LivingEntity) {
                        ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 6*20, 3));
                    }
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 6*20, 3));
                    break;
                case 27:
                    if(target instanceof LivingEntity) {
                        ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 18*20, 1));
                    }
                    break;
                case 28:
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 60*20, 1));
                    break;
                case 29:
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60*20, 1));
                    break;
                default:
                    user.setHealth(0);
                    break;
            }

            isReady = false;

        } else {
            isReady = true;
        }

    }

    @Override
    public boolean isCursed() {
        return true;
    }


}
