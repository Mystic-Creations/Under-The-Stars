package net.mysticcreations.underthestars.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.Random;
import java.util.UUID;

public class SugarRush extends MobEffect {
    private static final Random RANDOM = new Random();
    private static final UUID SPEED_UUID = UUID.fromString("b7c2f6b3-8a9e-4d3f-b1a4-1a7b6c9d8e01");
    private static final UUID HASTE_UUID = UUID.fromString("e91c4b6d-5f2a-4e3b-8d77-9f6b2c1a0e12");

    public SugarRush() {
        super(MobEffectCategory.BENEFICIAL, 0xEDEDE8);
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        int speedLevel = RANDOM.nextInt(2);
        int hasteLevel = RANDOM.nextInt(2);

        if (speedLevel == 1 && hasteLevel == 1) {
            if (RANDOM.nextBoolean()) {
                speedLevel = 0;
            } else {
                hasteLevel = 0;
            }
        }

        this.addAttributeModifier(
            Attributes.MOVEMENT_SPEED,
            SPEED_UUID.toString(),
            speedLevel == 1 ? 0.4D : 0.2D,
            AttributeModifier.Operation.MULTIPLY_TOTAL
        );

        this.addAttributeModifier(
            Attributes.ATTACK_SPEED,
            HASTE_UUID.toString(),
            hasteLevel == 1 ? 0.2D : 0.1D,
            AttributeModifier.Operation.MULTIPLY_TOTAL
        );

        super.addAttributeModifiers(entity, attributeMap, amplifier);
    }

    @Override
    public double getAttributeModifierValue(int amplifier, AttributeModifier modifier) {
        return modifier.getAmount();
    }
}
