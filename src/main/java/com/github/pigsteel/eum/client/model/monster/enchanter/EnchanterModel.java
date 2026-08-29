package com.github.pigsteel.eum.client.model.monster.enchanter;

//? >= 1.21.2 {
import com.github.pigsteel.eum.client.renderer.entity.state.EnchanterRenderState;
import com.github.pigsteel.eum.util.ModelUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.monster.illager.IllagerModel;

public class EnchanterModel<S extends EnchanterRenderState> extends IllagerModel<S> {
    private final ModelPart head;
    private final ModelPart hat;
    private final ModelPart arms;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
	private final ModelPart book;

    public EnchanterModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.hat = this.head.getChild("hat");
        this.hat.visible = true;
        this.arms = root.getChild("arms");
        this.leftLeg = root.getChild("left_leg");
        this.rightLeg = root.getChild("right_leg");
        this.leftArm = root.getChild("left_arm");
        this.rightArm = root.getChild("right_arm");
		this.book = root.getChild("book");
    }

	@Override
	public void setupAnim(final S state) {
		super.setupAnim(state);
		this.book.setInitialPose(arms.getInitialPose());
	}

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = ModelUtil.createIllagerMesh(CubeDeformation.NONE);
		PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild(
				"book",
				CubeListBuilder.create(),
				PartPose.ZERO
		);

		root.getChild("head").addOrReplaceChild(
				"hat", CubeListBuilder.create().texOffs(32, 0).addBox(-5.0F, -14.0F, -5.0F, 10.0F, 8.0F, 10.0F), PartPose.offset(.0F, .0F, 0.0F)
		);

        return LayerDefinition.create(mesh, 128, 64);
    }

	public void translateBook(S state, PoseStack poseStack) {
		this.root.translateAndRotate(poseStack);
		this.book.translateAndRotate(poseStack);
	}
}
//?}
