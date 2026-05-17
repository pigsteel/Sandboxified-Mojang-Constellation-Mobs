package com.github.pigsteel.smcm.util;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import org.joml.Vector3f;

public class AnimationUtil {
	public static float progress(float time, float start, float end) {
		return Mth.clamp(Mth.inverseLerp(time, start, end), 0.0F, 1.0F);
	}

	public static void lerpModelPartRotIntoVector(float progress, ModelPart part, Vector3f rotation) {
		lerpModelPartRotIntoVector(progress, part, rotation.x, rotation.y, rotation.z);
	}

	public static void lerpModelPartRotIntoVector(float progress, ModelPart part, float xRot, float yRot, float zRot) {
		part.setRotation(
				Mth.lerp(progress, part.xRot, xRot),
				Mth.lerp(progress, part.yRot, yRot),
				Mth.lerp(progress, part.zRot, zRot)
		);
	}
}
