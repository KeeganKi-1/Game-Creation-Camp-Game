package code;

public class CircleHitbox {
    int x;
    int y; 
    int r;
    public CircleHitbox(int x,int y,int r){
        this.x=x;
        this.y =y;
        this.r =r;
    }

    public boolean isIntersecting(int x, int y){
        return Math.sqrt(Math.pow(x-this.x-r,2)+Math.pow(y-this.y-r, 2))<r;
    }
    
}
