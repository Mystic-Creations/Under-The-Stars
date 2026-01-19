package net.mysticcreations.underthestars.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

import java.util.Random;

public class SugarRush extends MobEffect {
    private static final Random RANDOM = new Random();

    public SugarRush() {
        super(MobEffectCategory.BENEFICIAL, 0xF5E16B);
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.addAttributeModifiers(entity, attributeMap, amplifier);

        int speedAmp = RANDOM.nextInt(2);
        int hasteAmp = RANDOM.nextInt(2);

        if (speedAmp == 1 && hasteAmp == 1) {
            if (RANDOM.nextBoolean()) {
                speedAmp = 0;
            } else {
                hasteAmp = 0;
            }
        }

        int duration = entity.getEffect(this).getDuration();

        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, duration, speedAmp, false, false, true));
        entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, duration, hasteAmp, false, false, true));
    }
}
