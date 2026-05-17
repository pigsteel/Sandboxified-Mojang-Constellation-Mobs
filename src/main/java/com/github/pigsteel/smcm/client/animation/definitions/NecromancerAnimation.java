package com.github.pigsteel.smcm.client.animation.definitions;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationChannel.Interpolations;
import net.minecraft.client.animation.AnimationChannel.Targets;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.AnimationDefinition.Builder;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class NecromancerAnimation {
	public static final float NECROMANCER_SUMMON_LENGTH = 3.0F;

	public static boolean canIDisplayParticles(long progressInMillis) {
		float progress = (float) (progressInMillis / 1000.0D);
		return progress >= 0.1F && progress <= 0.7F;
	}

    public static final AnimationDefinition NECROMANCER_SUMMON = Builder.withLength(NECROMANCER_SUMMON_LENGTH)
                .addAnimation(
                        "head",
                        new AnimationChannel(
                                Targets.ROTATION,
                                new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
								new Keyframe(0.2F, KeyframeAnimations.degreeVec(-20.5F, 12.5F, 0.0F), Interpolations.CATMULLROM),
								new Keyframe(1.2F, KeyframeAnimations.degreeVec(-20.5F, 12.5F, 0.0F), Interpolations.CATMULLROM),
								new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
                        )
                )
                .addAnimation(
                        "head",
                        new AnimationChannel(
                                Targets.POSITION,
								new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
                        )
                )
                .addAnimation(
                        "right_arm",
                        new AnimationChannel(
                                Targets.ROTATION,
								new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
								new Keyframe(0.4F, KeyframeAnimations.degreeVec(-90.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
								new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
						)
                )
				.addAnimation(
						"staff",
						new AnimationChannel(
								Targets.POSITION,
								new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F,0.0F, 0.0F), Interpolations.CATMULLROM),
								new Keyframe(0.4F, KeyframeAnimations.posVec(0.0F,14.0F, 0.0F), Interpolations.CATMULLROM),
								new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F,0.0F, 0.0F), Interpolations.CATMULLROM)
						)
				)
			/*
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
                )*/
                .build();

	public static final AnimationDefinition NECROMANCER_SUMMON_LEFT = Builder.withLength(2.0F)
			/*
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
								new Keyframe[]{
										new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
										new Keyframe(2.5F, KeyframeAnimations.posVec(-180.01F, 0.0F, 0.0F), Interpolations.CATMULLROM),
										new Keyframe(5.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
								}
						)
                )*/
			/*
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
                )*/
			.addAnimation(
					"left_arm",
					new AnimationChannel(
							Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
							new Keyframe(0.4F, KeyframeAnimations.degreeVec(-90.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
							new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
					)
			)
			.addAnimation(
					"staff",
					new AnimationChannel(
							Targets.POSITION,
							new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F,0.0F, 0.0F), Interpolations.CATMULLROM),
							new Keyframe(0.4F, KeyframeAnimations.posVec(0.0F,14.0F, 0.0F), Interpolations.CATMULLROM),
							new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F,0.0F, 0.0F), Interpolations.CATMULLROM)
					)
			)
			/*
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
                )*/
			.build();

    public static AnimationDefinition NECROMANCER_SHOOT = Builder.withLength(3.0F).build();
}
