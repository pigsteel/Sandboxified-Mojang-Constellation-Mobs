package com.github.pigsteel.eum.client.model.monster.windcaller;

//? >= 1.21.2 {
import com.github.pigsteel.eum.client.renderer.entity.state.WindcallerRenderState;
import com.github.pigsteel.eum.util.ModelUtil;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.monster.illager.IllagerModel;
import net.minecraft.util.Mth;
import org.joml.Vector3f;

public class WindcallerModel<S extends WindcallerRenderState> extends IllagerModel<S> {
	private final ModelPart staffPivot;
	private final ModelPart staff;
	private final ModelPart rightArm;
	private final ModelPart leftArm;

	public WindcallerModel(ModelPart root) {
		super(root);

		this.staffPivot = root.getChild("staff_pivot");
		this.staff = this.staffPivot.getChild("staff");

		this.leftArm = root.getChild("left_arm");
		this.rightArm = root.getChild("right_arm");
	}

	@Override
	public void setupAnim(final S state) {
		super.setupAnim(state);

		this.translateAndRotateStaffToArm(rightArm);
	}

	private void translateAndRotateStaffToArm(ModelPart arm) {
		staffPivot.offsetPos(new Vector3f(arm.x, arm.y, arm.z));
		staffPivot.offsetRotation(new Vector3f(arm.xRot + Mth.HALF_PI, arm.yRot, arm.zRot));
		staff.z -= 8;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = ModelUtil.createIllagerMesh(CubeDeformation.NONE);
		PartDefinition root = mesh.getRoot();
		PartDefinition head = root.getChild("head");

		PartDefinition crazyHair = head.addOrReplaceChild("crazy_hair",
				CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -20.0F, -4.0F, 8.0F, 10.0F, 8.0F),
				PartPose.ZERO
		);
		crazyHair.addOrReplaceChild("crazy_hair_top",
				CubeListBuilder.create().texOffs(24, 0).addBox(-4.0F, -15.0F, -4.0F, 8.0F, 0.0F, 8.0F),
				PartPose.ZERO
		);

		PartDefinition staffPivot = root.addOrReplaceChild("staff_pivot", CubeListBuilder.create(), PartPose.ZERO);

		// warning: torture ahead
		staffPivot.addOrReplaceChild("staff",
				CubeListBuilder.create()
						// shaft 1
						.texOffs(115, 15)
						.addBox(0.0F, 9.0F, 0.0F, 1.0F, 26.0F, 1.0F)

						// shaft 2
						.texOffs(111, 26)
						.addBox(0.0F, 4.0F, 0.0F, 1.0F, 4.0F, 1.0F)

						// left bottom spike (2 cubes)
						.texOffs(111, 38)
						.addBox(1.0F, 30.0F, 0.0F, 1.0F, 3.0F, 1.0F)
						.texOffs(111, 36)
						.addBox(2.0F, 31.0F, 0.0F, 1.0F, 1.0F, 1.0F)

						// right bottom spike (1 cube)
						.texOffs(111, 36)
						.addBox(-1.0F, 28.0F, 0.0F, 1.0F, 1.0F, 1.0F)

						// left top spike
						.texOffs(111, 36)
						.addBox(1.0F, 11.0F, 0.0F, 1.0F, 1.0F, 1.0F)

						// first deviation (right)
						.texOffs(111, 31)
						.addBox(-1.0F, 6.0F, 0.0F, 1.0F, 4.0F, 1.0F)
						.texOffs(111, 36)
						.addBox(-2.0F, 8.0F, 0.0F, 1.0F, 1.0F, 1.0F)

						// bottom half of crescent
						.texOffs(111, 21)
						.addBox(1.0F, 4.0F, 0.0F, 1.0F, 2.0F, 1.0F)

						// rest of the crescent
						.texOffs(111, 16)
						.addBox(-1.0F, 3.0F, 0.0F, 1.0F, 2.0F, 1.0F)
						.texOffs(111, 12)
						.addBox(-2.0F, 1.0F, 0.0F, 1.0F, 3.0F, 1.0F)
						.texOffs(111, 36)
						.addBox(-1.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F)
						.texOffs(107, 24)
						.addBox(-1.0F, 0.0F, 0.0F, 3.0F, 1.0F, 1.0F)
						.texOffs(111, 16)
						.addBox(2.0F, 1.0F, 0.0F, 1.0F, 2.0F, 1.0F)
						.texOffs(111, 19)
						.addBox(1.0F, 2.0F, 0.0F, 1.0F, 1.0F, 1.0F),
				PartPose.offsetAndRotation(-0.5F, -17.5F, -0.5F, 0F, -Mth.HALF_PI, 0F)
		);

		return LayerDefinition.create(mesh, 128, 64);
	}
}
//?}
