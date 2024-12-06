package net.milkshake.milkshakemod.entity.animations;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import org.joml.Vector3f;

/**
 * Made with Blockbench 4.11.2
 * Exported for Minecraft version 1.19 or later with Mojang mappings
 */
public class soul_benderAnimation {
	public static final AnimationDefinition IDLE = AnimationDefinition.Builder.withLength(2f).looping()
			.addAnimation("root", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
							AnimationChannel.Interpolations.LINEAR)))
			.build();

	public static final AnimationDefinition WALK = AnimationDefinition.Builder.withLength(1f).looping()
			.addAnimation("root", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0.5f, 0f),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5f, KeyframeAnimations.degreeVec(20f, 0f, 0f),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5f, KeyframeAnimations.degreeVec(-20f, 0f, 0f),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
							AnimationChannel.Interpolations.LINEAR)))
			.build();

	public static final AnimationDefinition ATTACK = AnimationDefinition.Builder.withLength(0.5f)
			.addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25f, KeyframeAnimations.degreeVec(-90f, 0f, 0f),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
							AnimationChannel.Interpolations.LINEAR)))
			.build();
}