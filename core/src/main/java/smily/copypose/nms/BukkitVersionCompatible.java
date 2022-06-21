package smily.copypose.nms;

public enum BukkitVersionCompatible {
    V1_18,
    V1_18_1,
    V1_18_2;

    public String toString(){
        if(this == V1_18) return "1.18-R0.1-SNAPSHOT";
        if(this == V1_18_1) return "1.18.1-R0.1-SNAPSHOT";
        if(this == V1_18_2) return "1.18.2-R0.1-SNAPSHOT";

        return null;
    }
}
