package net.milkshake.milkshakemod.entity.client;

import net.milkshake.milkshakemod.entity.custom.UnlimitedVoidEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class UnlimitedVoidModel extends HumanoidModel<UnlimitedVoidEntity> {
	public UnlimitedVoidModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		// Head
		partdefinition.addOrReplaceChild("head",
			CubeListBuilder.create()
				.texOffs(0, 0)
				.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F),
			PartPose.offset(0.0F, 0.0F, 0.0F));

		// Body
		partdefinition.addOrReplaceChild("body",
			CubeListBuilder.create()
				.texOffs(16, 16)
				.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F),
			PartPose.offset(0.0F, 0.0F, 0.0F));

		// Right Arm
		partdefinition.addOrReplaceChild("right_arm",
			CubeListBuilder.create()
				.texOffs(40, 16)
				.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F),
			PartPose.offset(-5.0F, 2.0F, 0.0F));

		// Left Arm
		partdefinition.addOrReplaceChild("left_arm",
			CubeListBuilder.create()
				.texOffs(40, 16)
				.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F),
			PartPose.offset(5.0F, 2.0F, 0.0F));

		// Right Leg
		partdefinition.addOrReplaceChild("right_leg",
			CubeListBuilder.create()
				.texOffs(0, 16)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
			PartPose.offset(-1.9F, 12.0F, 0.0F));

		// Left Leg
		partdefinition.addOrReplaceChild("left_leg",
			CubeListBuilder.create()
				.texOffs(0, 16)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
			PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}