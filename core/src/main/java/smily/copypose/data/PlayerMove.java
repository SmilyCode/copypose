package smily.copypose.data;

public class PlayerMove {
    private double locationX;
    private double locationY;
    private double locationZ;
    private float yaw;
    private float pitch;

    public PlayerMove(double locationX, double locationY, double locationZ, float yaw, float pitch){
        this.locationX = locationX;
        this.locationY = locationY;
        this.locationZ = locationZ;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    public void setLocationZ(double locationZ) {
        this.locationZ = locationZ;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public double getLocationX() {
        return locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public double getLocationZ() {
        return locationZ;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }
}
