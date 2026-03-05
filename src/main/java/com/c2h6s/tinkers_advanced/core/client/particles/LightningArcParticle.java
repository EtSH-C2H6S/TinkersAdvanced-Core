package com.c2h6s.tinkers_advanced.core.client.particles;

import com.c2h6s.tinkers_advanced.core.client.util.RenderUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import lombok.Getter;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LightningArcParticle extends Particle {
    protected LightningArcParticle(ClientLevel pLevel, double pX, double pY, double pZ) {
        super(pLevel, pX, pY, pZ);
    }
    public List<Vec3> offsets = new ArrayList<>();
    @Getter
    private Vec3 velocity;
    LightningArcParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed){
        super(pLevel,pX,pY,pZ,pXSpeed,pYSpeed,pZSpeed);
        this.lifetime = 4;
        this.xd = 0;
        this.yd = 0;
        this.zd = 0;
        Random random1 = new Random();
        velocity = new Vec3(pXSpeed,pYSpeed,pZSpeed);
        var offsetDirection = velocity.cross(new Vec3(random1.nextDouble()-0.5,random1.nextDouble()-0.5,random1.nextDouble()-0.5)).normalize();
        if (velocity.length()<=0.25f) offsets.clear();
        else if (velocity.length()<=8){
            offsetDirection = offsetDirection.scale(0.5);
            for (int i = 0; i < velocity.length()*4; i++) {
                offsets.add(offsetDirection.scale(random1.nextDouble()-0.5));
            }
        } else {
            offsetDirection = offsetDirection.scale(velocity.length()/8);
            for (int i = 0; i < 32; i++) {
                offsets.add(offsetDirection.scale(random1.nextDouble()-0.5));
            }
        }
    }

    @Override
    public void move(double pX, double pY, double pZ) {
    }

    @Override
    public void render(VertexConsumer pBuffer, Camera pRenderInfo, float pPartialTicks) {
        if (!offsets.isEmpty()) {
            Vec3 pos = this.getPos().subtract(pRenderInfo.getPosition());
            float ticks = this.age + pPartialTicks;
            float percRadius = (this.lifetime - ticks) / this.lifetime;
            float percOffset = ticks/this.lifetime;
            var nodes = new ArrayList<Vec3>();
            var bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
            nodes.add(Vec3.ZERO);
            Vec3 direction = velocity.normalize().scale(velocity.length() / (offsets.size()+1));
            for (int i = 0; i < offsets.size(); i++) {
                var offset = offsets.get(i).scale(1+percOffset);
                nodes.add(direction.scale(i).add(offset));
            }
            nodes.add(velocity);
            if (percRadius > 0) {
                var poseStack = new PoseStack();
                poseStack.translate(pos.x,pos.y,pos.z);
                RenderUtil.drawFoldedPipe(bufferSource.getBuffer(RenderUtil.brightProjectileRenderType()),poseStack, nodes , percRadius * 0.1, 0xFFE2FEFF, 3);
                bufferSource.endBatch();
            }
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.CUSTOM;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        public Provider() {}

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            return new LightningArcParticle(pLevel,pX,pY,pZ,pXSpeed,pYSpeed,pZSpeed);
        }
    }
}
