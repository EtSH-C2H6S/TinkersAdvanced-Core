package com.c2h6s.tinkers_advanced.core.client.util;

import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced.core.util.CommonUtil;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import java.util.List;

import static net.minecraft.client.renderer.RenderStateShard.*;

public class RenderUtil {
    public static ResourceLocation TEXTURE_WHITE = TinkersAdvanced.getLocation("textures/white.png");

    public static RenderType brightProjectileRenderType(ResourceLocation texture){
        return RenderType.create("bright_projectile",
                DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, true, true,
                RenderType.CompositeState.builder().setTextureState(new TextureStateShard(texture, false, false))
                        .setShaderState(RENDERTYPE_TEXT_SEE_THROUGH_SHADER)
                        .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                        .setCullState(NO_CULL)
                        .createCompositeState(true));
    }
    public static RenderType brightProjectileRenderType(){
        return RenderType.create("bright_projectile",
                DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, true, true,
                RenderType.CompositeState.builder().setTextureState(new TextureStateShard(TEXTURE_WHITE, false, false))
                        .setShaderState(RENDERTYPE_TEXT_SEE_THROUGH_SHADER)
                        .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                        .setCullState(NO_CULL)
                        .createCompositeState(true));
    }

    public static void drawPipe(PoseStack pPoseStack, VertexConsumer consumer, Matrix4f poseMatrix, float radius, float distance, int r, int g, int b, int a, Matrix3f normalMatrix){
        consumer.vertex(poseMatrix, -radius, -radius, 0).color(r,g,b,a).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        consumer.vertex(poseMatrix, -radius, radius, 0).color(r,g,b,a).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        consumer.vertex(poseMatrix, radius, radius, 0).color(r,g,b,a).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        consumer.vertex(poseMatrix, radius, -radius, 0).color(r,g,b,a).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();

        consumer.vertex(poseMatrix, -radius, -radius, distance).color(r,g,b,a).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        consumer.vertex(poseMatrix, -radius, radius, distance).color(r,g,b,a).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        consumer.vertex(poseMatrix, radius, radius, distance).color(r,g,b,a).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        consumer.vertex(poseMatrix, radius, -radius, distance).color(r,g,b,a).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();

        consumer.vertex(poseMatrix, -radius, -radius, 0).color(r,g,b,a).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        consumer.vertex(poseMatrix, -radius, radius, 0).color(r,g,b,a).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        consumer.vertex(poseMatrix, -radius, radius, distance).color(r,g,b,a).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        consumer.vertex(poseMatrix, -radius, -radius, distance).color(r,g,b,a).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(90));
        consumer.vertex(poseMatrix, -radius, -radius, 0).color(r,g,b,a).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        consumer.vertex(poseMatrix, -radius, radius, 0).color(r,g,b,a).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        consumer.vertex(poseMatrix, -radius, radius, distance).color(r,g,b,a).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        consumer.vertex(poseMatrix, -radius, -radius, distance).color(r,g,b,a).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(90));
        consumer.vertex(poseMatrix, -radius, -radius, 0).color(r,g,b,a).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        consumer.vertex(poseMatrix, -radius, radius, 0).color(r,g,b,a).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        consumer.vertex(poseMatrix, -radius, radius, distance).color(r,g,b,a).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        consumer.vertex(poseMatrix, -radius, -radius, distance).color(r,g,b,a).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(90));
        consumer.vertex(poseMatrix, -radius, -radius, 0).color(r,g,b,a).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        consumer.vertex(poseMatrix, -radius, radius, 0).color(r,g,b,a).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        consumer.vertex(poseMatrix, -radius, radius, distance).color(r,g,b,a).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        consumer.vertex(poseMatrix, -radius, -radius, distance).color(r,g,b,a).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(90));
    }

    public static void drawFoldedPipe(VertexConsumer consumer,PoseStack poseStack, List<Vec3> nodes, double radius, int argb, int faces){
        if (nodes.size()>=2){
            for (int i = 0; i < nodes.size()-1; i++) {
                var last = i>0?nodes.get(i-1):null;
                var start = nodes.get(i);
                var end = nodes.get(i+1);
                var direction = end.subtract(start);
                var legacy = last==null?direction:start.subtract(last);
                float deg = 360f/faces;
                var sOffset = legacy.cross(new Vec3(0.01,1,0.01)).normalize().scale(radius);
                var eOffset = direction.cross(new Vec3(0.01,1,0.01)).normalize().scale(radius);
                for (int j = 0; j < faces; j++) {
                    var sOffset1 = CommonUtil.rotateVec3(sOffset,legacy,deg*j);
                    var eOffset1 = CommonUtil.rotateVec3(eOffset,direction,deg*j);
                    var sOffset2 = CommonUtil.rotateVec3(sOffset,legacy,deg*(j+1));
                    var eOffset2 = CommonUtil.rotateVec3(eOffset,direction,deg*(j+1));
                    var v1 = start.add(sOffset1);
                    var v2 = start.add(sOffset2);
                    var v3 = end.add(eOffset1);
                    var v4 = end.add(eOffset2);
                    PoseStack.Pose pose = poseStack.last();
                    Matrix4f poseMatrix = pose.pose();
                    Matrix3f normalMatrix = pose.normal();
                    consumer.vertex(poseMatrix, (float) v1.x, (float) v1.y, (float) v1.z).color(argb).uv(1,1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0.0F, 1.0F, 0.0F).endVertex();
                    consumer.vertex(poseMatrix, (float) v2.x, (float) v2.y, (float) v2.z).color(argb).uv(1,0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0.0F, 1.0F, 0.0F).endVertex();
                    consumer.vertex(poseMatrix, (float) v4.x, (float) v4.y, (float) v4.z).color(argb).uv(0,0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0.0F, 1.0F, 0.0F).endVertex();
                    consumer.vertex(poseMatrix, (float) v3.x, (float) v3.y, (float) v3.z).color(argb).uv(0,1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0.0F, 1.0F, 0.0F).endVertex();
                }
            }
        }
    }

    public static void drawFoldedIsoRadiusPipe(VertexConsumer consumer,PoseStack poseStack, List<Vec3> nodes, double radius1,double radius2, int argb, int faces){
        if (nodes.size()>=2){
            double delta = (radius2-radius1)/nodes.size();
            for (int i = 0; i < nodes.size()-1; i++) {
                var last = i>0?nodes.get(i-1):null;
                var start = nodes.get(i);
                var end = nodes.get(i+1);
                var direction = end.subtract(start);
                var legacy = last==null?direction:start.subtract(last);
                float deg = 360f/faces;
                var sOffset = legacy.cross(new Vec3(0.01,1,0.01)).normalize().scale(radius1+(i*delta));
                var eOffset = direction.cross(new Vec3(0.01,1,0.01)).normalize().scale(radius1+((i+1)*delta));
                for (int j = 0; j < faces; j++) {
                    var sOffset1 = CommonUtil.rotateVec3(sOffset,legacy,deg*j);
                    var eOffset1 = CommonUtil.rotateVec3(eOffset,direction,deg*j);
                    var sOffset2 = CommonUtil.rotateVec3(sOffset,legacy,deg*(j+1));
                    var eOffset2 = CommonUtil.rotateVec3(eOffset,direction,deg*(j+1));
                    var v1 = start.add(sOffset1);
                    var v2 = start.add(sOffset2);
                    var v3 = end.add(eOffset1);
                    var v4 = end.add(eOffset2);
                    PoseStack.Pose pose = poseStack.last();
                    Matrix4f poseMatrix = pose.pose();
                    Matrix3f normalMatrix = pose.normal();
                    consumer.vertex(poseMatrix, (float) v1.x, (float) v1.y, (float) v1.z).color(argb).uv(1,1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0.0F, 1.0F, 0.0F).endVertex();
                    consumer.vertex(poseMatrix, (float) v2.x, (float) v2.y, (float) v2.z).color(argb).uv(1,0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0.0F, 1.0F, 0.0F).endVertex();
                    consumer.vertex(poseMatrix, (float) v4.x, (float) v4.y, (float) v4.z).color(argb).uv(0,0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0.0F, 1.0F, 0.0F).endVertex();
                    consumer.vertex(poseMatrix, (float) v3.x, (float) v3.y, (float) v3.z).color(argb).uv(0,1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0.0F, 1.0F, 0.0F).endVertex();
                }
            }
        }
    }
}
