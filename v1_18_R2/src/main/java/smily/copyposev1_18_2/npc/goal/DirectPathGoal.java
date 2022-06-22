package smily.copyposev1_18_2.npc.goal;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.pathfinder.Path;

public class DirectPathGoal extends Goal {

    private final PathfinderMob mob;
    private final double speed;
    private Path path;

    public DirectPathGoal(PathfinderMob mob, double speed){
        this.mob = mob;
        this.speed = speed;
        this.path = path;
    }

    @Override
    public boolean canUse() {
        return !this.mob.getNavigation().isDone() && this.path != null;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    @Override
    public void start() {
        this.mob.getNavigation().moveTo(
            this.path,
            this.speed
        );
    }
}
