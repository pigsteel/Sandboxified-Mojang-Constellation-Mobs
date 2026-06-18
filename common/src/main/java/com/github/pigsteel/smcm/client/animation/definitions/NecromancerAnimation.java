package com.github.pigsteel.smcm.client.animation.definitions;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.animation.AnimationChannel.Interpolations;
import net.minecraft.client.animation.AnimationChannel.Targets;
import net.minecraft.client.animation.AnimationDefinition.Builder;

public class NecromancerAnimation {
    public static final AnimationDefinition NECROMANCER_SUMMON;
    public static final AnimationDefinition NECROMANCER_SHOOT;

    static {
        NECROMANCER_SUMMON = Builder.withLength(5.0F)
                .addAnimation(
                        "body",
                        new AnimationChannel(
                                Targets.ROTATION,
                                new Keyframe[]{
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
                                        new Keyframe(2.5F, KeyframeAnimations.degreeVec(-90.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
                                        new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
                                }
                        )
                )
                .addAnimation(
                        "body",
                        new AnimationChannel(
                                Targets.POSITION,
                                new Keyframe[] {
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
                                        new Keyframe(2.5F, KeyframeAnimations.degreeVec(-180.01F, 0.0F, 0.0F), Interpolations.CATMULLROM),
                                        new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
                                }
                        )
                )
                .addAnimation(
                        "head",
                        new AnimationChannel(
                                Targets.ROTATION,
                                new Keyframe[]{

                                }
                        )
                )
                .addAnimation(
                        "head",
                        new AnimationChannel(
                                Targets.POSITION,
                                new Keyframe[] {

                                }
                        )
                )
                .addAnimation(
                        "left_arm",
                        new AnimationChannel(
                                Targets.ROTATION,
                                new Keyframe[]{

                                }
                        )
                )
                .addAnimation(
                        "left_arm",
                        new AnimationChannel(
                                Targets.POSITION,
                                new Keyframe[] {

                                }
                        )
                )
                .addAnimation(
                        "right_arm",
                        new AnimationChannel(
                                Targets.ROTATION,
                                new Keyframe[]{

                                }
                        )
                )
                .addAnimation(
                        "right_arm",
                        new AnimationChannel(
                                Targets.POSITION,
                                new Keyframe[] {

                                }
                        )
                )
                .build();

        NECROMANCER_SHOOT = Builder.withLength(3.0F).build();
    }
}
