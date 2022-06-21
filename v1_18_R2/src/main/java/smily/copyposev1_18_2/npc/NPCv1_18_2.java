package smily.copyposev1_18_2.npc;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class NPCv1_18_2 extends PathfinderMob {
    private final MinecraftServer server;
    private Player fakePlayer;
    private GameProfile gameProfile;


    protected NPCv1_18_2(EntityType<? extends PathfinderMob> entitytypes, Level world) {
        super(entitytypes, world);
        this.maxUpStep = 0.6F;
        this.setCanPickUpLoot(true);
        this.setCustomNameVisible(true);
        this.setCustomName(this.getName());
        this.setInvulnerable(true);
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

    public static AttributeSupplier.Builder createNPCAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.ATTACK_DAMAGE, 3.25D)
                .add(Attributes.ARMOR, 2.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2505D)
                .add(Attributes.FLYING_SPEED, 0.8D)
                .add(Attributes.FOLLOW_RANGE, 35.0D);
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




}
