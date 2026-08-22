public static final AnimationDefinition NECROMANCER_SHOOT = Builder.withLength(1.0F)
       .addAnimation("hip", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3125F, degreeVec(0.0F, 30.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.7292F, degreeVec(0.0F, 30.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("body", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3125F, degreeVec(-13.5F, 10.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, degreeVec(19.0F, -48.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.7292F, degreeVec(19.0F, -48.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("head", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3125F, degreeVec(19.0F, -37.0F, -12.5F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, degreeVec(-21.0F, 17.5F, -5.0F), Interpolations.CATMULLROM),
             new Keyframe(0.7292F, degreeVec(-21.0F, 17.5F, -5.0F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("right_arm", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3125F, degreeVec(-77.5F, 0.0F, -15.0F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, degreeVec(0.0F, 2.5F, -12.5F), Interpolations.CATMULLROM),
             new Keyframe(0.7292F, degreeVec(0.0F, 2.5F, -12.5F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("left_arm", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3125F, degreeVec(-65.0F, -35.0F, -15.0F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, degreeVec(17.5F, -7.5F, -40.0F), Interpolations.CATMULLROM),
             new Keyframe(0.7292F, degreeVec(17.5F, -7.5F, -40.0F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("staff_hand_pivot", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3125F, degreeVec(11.6F, 15.0F, -2.56F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, degreeVec(65.0F, 0.0F, -9.0F), Interpolations.CATMULLROM),
             new Keyframe(0.7292F, degreeVec(65.0F, 0.0F, -9.0F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("staff", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("staff", new AnimationChannel(Targets.POSITION,
             new Keyframe(0.0F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3125F, posVec(0.0F, 12.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, posVec(0.0F, 3.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.7292F, posVec(0.0F, 3.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("cloak", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3125F, degreeVec(7.5F, 0.0F, -2.5F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, degreeVec(42.5F, 0.0F, -2.5F), Interpolations.CATMULLROM),
             new Keyframe(0.7292F, degreeVec(42.5F, 0.0F, -2.5F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("right_leg", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3125F, degreeVec(17.5F, 0.0F, 7.5F), Interpolations.CATMULLROM),
             new Keyframe(0.7292F, degreeVec(17.5F, 0.0F, 7.5F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("left_leg", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3125F, degreeVec(-10.0F, 0.0F, -5.0F), Interpolations.CATMULLROM),
             new Keyframe(0.7292F, degreeVec(-10.0F, 0.0F, -5.0F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .build();


public static final AnimationDefinition NECROMANCER_SHOOT_LEFT = Builder.withLength(1.0F)
       .addAnimation("hip", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3125F, degreeVec(0.0F, -30.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.7292F, degreeVec(0.0F, -30.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("body", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3125F, degreeVec(-13.5F, -10.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, degreeVec(19.0F, 48.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.7292F, degreeVec(19.0F, 48.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("head", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3125F, degreeVec(19.0F, 37.0F, 12.5F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, degreeVec(-21.0F, -17.5F, 5.0F), Interpolations.CATMULLROM),
             new Keyframe(0.7292F, degreeVec(-21.0F, -17.5F, 5.0F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("left_arm", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3125F, degreeVec(-77.5F, 0.0F, 15.0F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, degreeVec(0.0F, -2.5F, 12.5F), Interpolations.CATMULLROM),
             new Keyframe(0.7292F, degreeVec(0.0F, -2.5F, 12.5F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("right_arm", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3125F, degreeVec(-65.0F, 35.0F, 15.0F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, degreeVec(17.5F, 7.5F, 40.0F), Interpolations.CATMULLROM),
             new Keyframe(0.7292F, degreeVec(17.5F, 7.5F, 40.0F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("staff_hand_pivot", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3125F, degreeVec(11.6F, -15.0F, 2.56F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, degreeVec(65.0F, 0.0F, 9.0F), Interpolations.CATMULLROM),
             new Keyframe(0.7292F, degreeVec(65.0F, 0.0F, 9.0F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("staff", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("staff", new AnimationChannel(Targets.POSITION,
             new Keyframe(0.0F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3125F, posVec(0.0F, 12.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, posVec(0.0F, 3.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.7292F, posVec(0.0F, 3.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("cloak", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3125F, degreeVec(7.5F, 0.0F, 2.5F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, degreeVec(42.5F, 0.0F, 2.5F), Interpolations.CATMULLROM),
             new Keyframe(0.7292F, degreeVec(42.5F, 0.0F, 2.5F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("left_leg", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3125F, degreeVec(17.5F, 0.0F, -7.5F), Interpolations.CATMULLROM),
             new Keyframe(0.7292F, degreeVec(17.5F, 0.0F, -7.5F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("right_leg", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3125F, degreeVec(-10.0F, 0.0F, 5.0F), Interpolations.CATMULLROM),
             new Keyframe(0.7292F, degreeVec(-10.0F, 0.0F, 5.0F), Interpolations.CATMULLROM),
             new Keyframe(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .build();


public static final AnimationDefinition NECROMANCER_SUMMON = Builder.withLength(1.5833F)
       .addAnimation("body", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3333F, degreeVec(60.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, degreeVec(60.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.6667F, degreeVec(-2.5F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.5833F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("right_arm", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3333F, degreeVec(-2.5F, -25.0F, 90.0F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, degreeVec(-2.5F, -25.0F, 90.0F), Interpolations.CATMULLROM),
             new Keyframe(0.6667F, degreeVec(-22.5F, 5.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.2292F, degreeVec(-22.5F, 5.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.5833F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("left_arm", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3333F, degreeVec(-75.0F, 12.5F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, degreeVec(-75.0F, 12.5F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.6667F, degreeVec(-25.2729F, -8.6473F, -28.8384F), Interpolations.CATMULLROM),
             new Keyframe(1.2292F, degreeVec(-25.2729F, -8.6473F, -28.8384F), Interpolations.CATMULLROM),
             new Keyframe(1.5833F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("staff_hand_pivot", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3333F, degreeVec(2.5F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, degreeVec(2.5F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.6667F, degreeVec(25.0F, 0.0F, -7.5F), Interpolations.CATMULLROM),
             new Keyframe(1.2292F, degreeVec(25.0F, 0.0F, -7.5F), Interpolations.CATMULLROM),
             new Keyframe(1.5833F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("staff", new AnimationChannel(Targets.POSITION,
             new Keyframe(0.0F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3333F, posVec(0.0F, 8.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, posVec(0.0F, 8.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.6667F, posVec(0.0F, 12.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.125F, posVec(0.0F, 12.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.375F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("cloak", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.6667F, degreeVec(25.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.2292F, degreeVec(25.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.5833F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .build();


public static final AnimationDefinition NECROMANCER_SUMMON_LEFT = Builder.withLength(1.5833F)
       .addAnimation("body", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3333F, degreeVec(60.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, degreeVec(60.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.6667F, degreeVec(-2.5F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.5833F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("left_arm", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3333F, degreeVec(-2.5F, 25.0F, -90.0F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, degreeVec(-2.5F, 25.0F, -90.0F), Interpolations.CATMULLROM),
             new Keyframe(0.6667F, degreeVec(-22.5F, -5.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.2292F, degreeVec(-22.5F, -5.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.5833F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("right_arm", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3333F, degreeVec(-75.0F, -12.5F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, degreeVec(-75.0F, -12.5F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.6667F, degreeVec(-25.2729F, 8.6473F, 28.8384F), Interpolations.CATMULLROM),
             new Keyframe(1.2292F, degreeVec(-25.2729F, 8.6473F, 28.8384F), Interpolations.CATMULLROM),
             new Keyframe(1.5833F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("staff_hand_pivot", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3333F, degreeVec(2.5F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, degreeVec(2.5F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.6667F, degreeVec(25.0F, 0.0F, 7.5F), Interpolations.CATMULLROM),
             new Keyframe(1.2292F, degreeVec(25.0F, 0.0F, 7.5F), Interpolations.CATMULLROM),
             new Keyframe(1.5833F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("staff", new AnimationChannel(Targets.POSITION,
             new Keyframe(0.0F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.3333F, posVec(0.0F, 8.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.5208F, posVec(0.0F, 8.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.6667F, posVec(0.0F, 12.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.125F, posVec(0.0F, 12.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.375F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .addAnimation("cloak", new AnimationChannel(Targets.ROTATION,
             new Keyframe(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(0.6667F, degreeVec(25.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.2292F, degreeVec(25.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
             new Keyframe(1.5833F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
       ))
       .build();
