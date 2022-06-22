package smily.copyposev1_18_2.npc;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddPlayerPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import smily.copyposev1_18_2.npc.goal.DirectPathGoal;

public class PlayerNPCv1_18_2 extends PathfinderMob {
    private final MinecraftServer server;
    private Player fakePlayer;
    private GameProfile gameProfile;
    private Goal directPathGoal;

    protected PlayerNPCv1_18_2(EntityType<? extends PathfinderMob> entitytypes, Level world) {
        super(entitytypes, world);
        this.maxUpStep = 0.6F;
        this.setCanPickUpLoot(true);
        this.setCustomNameVisible(true);
        this.setCustomName(this.getName());
        this.setInvulnerable(false);
        this.setPersistenceRequired();
        this.xpReward = 0;
        this.setSpeed(0.4F);

        this.server = world.getServer();
        this.gameProfile = new GameProfile(this.getUUID(), this.getName().getString());

        if (this.fakePlayer == null) {
            this.constructFakePlayer();
        }
    }

    public void constructFakePlayer(){
        this.fakePlayer = new Player(this.level, this.blockPosition(), this.yHeadRot, new GameProfile(this.uuid, null)) {
            @Override
            public boolean isSpectator() {
                return false;
            }

            @Override
            public boolean isCreative() {
                return false;
            }
        };
    }

    @Override
    public SynchedEntityData getEntityData() {
        if (this.fakePlayer == null) {
            // Fixes gravity changer incompatibility
            this.constructFakePlayer();
        }
        return this.fakePlayer.getEntityData();
    }

    @Override
    public void setShiftKeyDown(boolean sneaking) {
        this.fakePlayer.setShiftKeyDown(sneaking);
        super.setShiftKeyDown(sneaking);
    }

    @Override
    public void setPose(Pose pose) {
        this.fakePlayer.setPose(pose);
        super.setPose(pose);
    }

    @Override
    public boolean canBeLeashed(Player entityhuman) {
        return false;
    }

    @Override
    protected void registerGoals() {
        this.directPathGoal = new DirectPathGoal(this, this.getSpeed());

        this.goalSelector.addGoal(1, this.directPathGoal);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        this.fakePlayer.moveTo(this.getX(), this.getY(), this.getZ());
        this.fakePlayer.setYRot((byte)((int)(this.getYHeadRot() * 256.0F / 360.0F)));
        this.fakePlayer.setXRot((byte)((int)(this.getXRot() * 256.0F / 360.0F)));

        return new ClientboundAddPlayerPacket(this.fakePlayer);
    }

    @Override
    public void setSprinting(boolean flag) {
        this.fakePlayer.setSprinting(flag);
        super.setSprinting(flag);
    }

    @Override
    public void setJumping(boolean flag) {
        this.fakePlayer.setSprinting(flag);
        super.setJumping(flag);
    }

    @Override
    public void setSwimming(boolean flag) {
        this.fakePlayer.setSprinting(flag);
        super.setSwimming(flag);
    }
}
