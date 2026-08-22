// Save this class in your mod and generate all required imports
/**
 * Made with Blockbench 5.1.6
 * Exported for Minecraft version 1.19 or later with Yarn mappings
 * @author Author
 */
public class necromancerAnimation {
	public static final Animation NECROMANCER_SHOOT = Animation.Builder.create(1.0F)
		.addBoneAnimation("hip", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3125F, AnimationHelper.createRotationalVector(0.0F, 30.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.7292F, AnimationHelper.createRotationalVector(0.0F, 30.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("body", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3125F, AnimationHelper.createRotationalVector(-13.5F, 10.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5208F, AnimationHelper.createRotationalVector(19.0F, -48.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.7292F, AnimationHelper.createRotationalVector(19.0F, -48.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3125F, AnimationHelper.createRotationalVector(19.0F, -37.0F, -12.5F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5208F, AnimationHelper.createRotationalVector(-21.0F, 17.5F, -5.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.7292F, AnimationHelper.createRotationalVector(-21.0F, 17.5F, -5.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("right_arm", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3125F, AnimationHelper.createRotationalVector(-77.5F, 0.0F, -15.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5208F, AnimationHelper.createRotationalVector(0.0F, 2.5F, -12.5F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.7292F, AnimationHelper.createRotationalVector(0.0F, 2.5F, -12.5F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("left_arm", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3125F, AnimationHelper.createRotationalVector(-65.0F, -35.0F, -15.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5208F, AnimationHelper.createRotationalVector(17.5F, -7.5F, -40.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.7292F, AnimationHelper.createRotationalVector(17.5F, -7.5F, -40.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("staff_hand_pivot", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3125F, AnimationHelper.createRotationalVector(11.6F, 15.0F, -2.56F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5208F, AnimationHelper.createRotationalVector(65.0F, 0.0F, -9.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.7292F, AnimationHelper.createRotationalVector(65.0F, 0.0F, -9.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("staff", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("staff", new Transformation(Transformation.Targets.TRANSLATE, 
			new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3125F, AnimationHelper.createTranslationalVector(0.0F, 12.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5208F, AnimationHelper.createTranslationalVector(0.0F, 3.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.7292F, AnimationHelper.createTranslationalVector(0.0F, 3.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("cloak", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3125F, AnimationHelper.createRotationalVector(7.5F, 0.0F, -2.5F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5208F, AnimationHelper.createRotationalVector(42.5F, 0.0F, -2.5F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.7292F, AnimationHelper.createRotationalVector(42.5F, 0.0F, -2.5F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("right_leg", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3125F, AnimationHelper.createRotationalVector(17.5F, 0.0F, 7.5F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.7292F, AnimationHelper.createRotationalVector(17.5F, 0.0F, 7.5F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("left_leg", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3125F, AnimationHelper.createRotationalVector(-10.0F, 0.0F, -5.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.7292F, AnimationHelper.createRotationalVector(-10.0F, 0.0F, -5.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.build();

	public static final Animation NECROMANCER_SUMMON = Animation.Builder.create(1.5833F)
		.addBoneAnimation("body", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3333F, AnimationHelper.createRotationalVector(60.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5208F, AnimationHelper.createRotationalVector(60.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.6667F, AnimationHelper.createRotationalVector(-2.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.5833F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("right_arm", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3333F, AnimationHelper.createRotationalVector(-2.5F, -25.0F, 90.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5208F, AnimationHelper.createRotationalVector(-2.5F, -25.0F, 90.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.6667F, AnimationHelper.createRotationalVector(-22.5F, 5.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.2292F, AnimationHelper.createRotationalVector(-22.5F, 5.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.5833F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("left_arm", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3333F, AnimationHelper.createRotationalVector(-75.0F, 12.5F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5208F, AnimationHelper.createRotationalVector(-75.0F, 12.5F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.6667F, AnimationHelper.createRotationalVector(-25.2729F, -8.6473F, -28.8384F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.2292F, AnimationHelper.createRotationalVector(-25.2729F, -8.6473F, -28.8384F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.5833F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("staff_hand_pivot", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3333F, AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5208F, AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.6667F, AnimationHelper.createRotationalVector(25.0F, 0.0F, -7.5F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.2292F, AnimationHelper.createRotationalVector(25.0F, 0.0F, -7.5F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.5833F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("staff", new Transformation(Transformation.Targets.TRANSLATE, 
			new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3333F, AnimationHelper.createTranslationalVector(0.0F, 8.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5208F, AnimationHelper.createTranslationalVector(0.0F, 8.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.6667F, AnimationHelper.createTranslationalVector(0.0F, 12.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.125F, AnimationHelper.createTranslationalVector(0.0F, 12.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.375F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("cloak", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.6667F, AnimationHelper.createRotationalVector(25.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.2292F, AnimationHelper.createRotationalVector(25.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.5833F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.build();
}