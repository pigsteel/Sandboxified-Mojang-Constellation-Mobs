package com.github.pigsteel.eum.client.animation.definitions;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationChannel.Interpolations;
import net.minecraft.client.animation.AnimationChannel.Targets;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.AnimationDefinition.Builder;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class RedstoneGolemAnimation {
    public static final AnimationDefinition WALK_ANIMATION = Builder.withLength(2.0F)
            .looping()
            .addAnimation(
                    "right_leg",
                    new AnimationChannel(
                            Targets.ROTATION,
                            new Keyframe(0.0F, KeyframeAnimations.degreeVec(-7.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                            new Keyframe(0.1667F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                            new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                            new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                            new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                            new Keyframe(1.75F, KeyframeAnimations.degreeVec(35.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                            new Keyframe(2.0F, KeyframeAnimations.degreeVec(-7.0F, 0.0F, 0.0F), Interpolations.LINEAR)
                    )
            )
            .addAnimation(
                    "right_leg",
                    new AnimationChannel(
                            Targets.POSITION,
                            new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -5.5F), Interpolations.LINEAR),
                            new Keyframe(0.1667F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                            new Keyframe(0.3333F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.0F), Interpolations.LINEAR),
                            new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 6.0F), Interpolations.LINEAR),
                            new Keyframe(1.1667F, KeyframeAnimations.posVec(0.0F, 0.0F, 3.0F), Interpolations.LINEAR),
                            new Keyframe(1.9167F, KeyframeAnimations.posVec(0.0F, 4.0F, -1.0F), Interpolations.LINEAR),
                            new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -5.5F), Interpolations.LINEAR)
                    )
            )
            .addAnimation(
                    "left_leg",
                    new AnimationChannel(
                            Targets.ROTATION,
                            new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                            new Keyframe(0.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                            new Keyframe(0.75F, KeyframeAnimations.degreeVec(35.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                            new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                            new Keyframe(1.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                            new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), Interpolations.LINEAR)
                    )
            )
            .addAnimation(
                    "left_leg",
                    new AnimationChannel(
                            Targets.POSITION,
                            new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 12.0F), Interpolations.LINEAR),
                            new Keyframe(0.1667F, KeyframeAnimations.posVec(0.0F, 0.0F, 3.0F), Interpolations.LINEAR),
                            new Keyframe(0.9167F, KeyframeAnimations.posVec(0.0F, 4.0F, -1.0F), Interpolations.LINEAR),
                            new Keyframe(1.1667F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                            new Keyframe(1.3333F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.0F), Interpolations.LINEAR),
                            new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 21.0F), Interpolations.LINEAR)
                    )
            )
            .addAnimation(
                    "body",
                    new AnimationChannel(
                            Targets.ROTATION,
                            new Keyframe(0.0F, KeyframeAnimations.degreeVec(1.0F, 0.0F, -2.5F), Interpolations.LINEAR),
                            new Keyframe(0.5F, KeyframeAnimations.degreeVec(-1.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                            new Keyframe(1.0F, KeyframeAnimations.degreeVec(1.0F, 0.0F, 2.5F), Interpolations.LINEAR),
                            new Keyframe(1.5F, KeyframeAnimations.degreeVec(-1.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                            new Keyframe(2.0F, KeyframeAnimations.degreeVec(1.0F, 0.0F, -2.5F), Interpolations.LINEAR)
                    )
            )
            .addAnimation(
                    "body",
                    new AnimationChannel(
                            Targets.POSITION,
                            new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                            new Keyframe(0.2083F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                            new Keyframe(0.375F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), Interpolations.LINEAR),
                            new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                            new Keyframe(1.2083F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                            new Keyframe(1.375F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), Interpolations.LINEAR),
                            new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), Interpolations.LINEAR)
                    )
            )
            .addAnimation(
                    "head",
                    new AnimationChannel(
                            Targets.ROTATION,
                            new Keyframe(0.0F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), Interpolations.CATMULLROM),
                            new Keyframe(0.1667F, KeyframeAnimations.degreeVec(9.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
                            new Keyframe(0.875F, KeyframeAnimations.degreeVec(-1.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
                            new Keyframe(1.25F, KeyframeAnimations.degreeVec(7.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
                            new Keyframe(1.75F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
                            new Keyframe(2.0F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), Interpolations.CATMULLROM)
                    )
            )
            .build();
}
