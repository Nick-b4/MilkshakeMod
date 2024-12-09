package net.milkshake.milkshakemod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.milkshake.milkshakemod.entity.custom.FrostFallenKingEntity;
import net.milkshake.milkshakemod.entity.animations.frost_fallen_kingAnimation;

public class FrostFallenKingModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "frostfallen_king_model"), "main");
	private final ModelPart body;
	private final ModelPart torso;
	private final ModelPart head;
	private final ModelPart rightarm;
	private final ModelPart leftarm;
	private final ModelPart rightleg;
	private final ModelPart leftleg;

	public FrostFallenKingModel(ModelPart root) { 
		this.body = root.getChild("body");
		this.torso = this.body.getChild("torso");
		this.head = this.body.getChild("head");
		this.rightarm = this.body.getChild("rightarm");
		this.leftarm = this.body.getChild("leftarm");
		this.rightleg = this.body.getChild("rightleg");
		this.leftleg = this.body.getChild("leftleg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 40).addBox(-8.0F, -9.0F, -1.0F, 14.0F, 28.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -43.0F, 0.0F));

		PartDefinition cape_r1 = torso.addOrReplaceChild("cape_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -38.0F, -1.0F, 22.0F, 38.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 28.0F, 14.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(44, 40).addBox(-6.0F, -13.0F, -3.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -51.0F, 0.0F));

		PartDefinition rightarm = body.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(44, 64).addBox(1.0F, -3.0F, -1.0F, 8.0F, 28.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -49.0F, 0.0F));

		PartDefinition leftarm = body.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(48, 0).addBox(-9.0F, -2.0F, -1.0F, 8.0F, 28.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -50.0F, 0.0F));

		PartDefinition iceblade_r1 = leftarm.addOrReplaceChild("iceblade_r1", CubeListBuilder.create().texOffs(86, 96).addBox(-3.0F, -24.9696F, -0.6972F, 4.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(92, 24).addBox(-3.0F, -30.8824F, -1.6934F, 4.0F, 23.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(80, 0).addBox(-3.0F, -35.7952F, -2.6896F, 4.0F, 28.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 23.0F, 3.0F, 1.4835F, 0.0F, 0.0F));

		PartDefinition iceblade_r2 = leftarm.addOrReplaceChild("iceblade_r2", CubeListBuilder.create().texOffs(76, 96).addBox(-3.0F, -24.8824F, 1.6934F, 4.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(90, 0).addBox(-3.0F, -30.7952F, 2.6896F, 4.0F, 23.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(32, 76).addBox(-3.0F, -35.7081F, 3.6858F, 4.0F, 28.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 23.0F, 3.0F, 1.6581F, 0.0F, 0.0F));

		PartDefinition iceblade_r3 = leftarm.addOrReplaceChild("iceblade_r3", CubeListBuilder.create().texOffs(96, 96).addBox(-3.0F, -6.0F, 0.0F, 4.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 23.0F, 3.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition iceblade_r4 = leftarm.addOrReplaceChild("iceblade_r4", CubeListBuilder.create().texOffs(92, 48).addBox(-3.0F, -6.0F, -9.0F, 4.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 23.0F, 3.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition rightleg = body.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(0, 76).addBox(-4.0F, 1.0F, -1.0F, 8.0F, 24.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -25.0F, 0.0F));

		PartDefinition leftleg = body.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(76, 64).addBox(-4.0F, 1.0F, -1.0F, 8.0F, 24.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -25.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entity instanceof FrostFallenKingEntity frostFallenKing) {
			// Walking animation
			this.rightleg.xRot = (float) Math.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
			this.leftleg.xRot = (float) Math.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
			this.rightarm.xRot = (float) Math.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
			this.leftarm.xRot = (float) Math.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}