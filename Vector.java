import java.util.*;

final class Vector {
  private final int x;
  private final int y;
  private final int z;
  
  Vector(int x, int y, int z){
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public double length(){
    return Math.sqrt(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2));
  }
  public int scalar_pro(Vector vec){
    return x*vec.x + y*vec.y + z*vec.z;
  }
  public Vector vector_pro(Vector vec){
    return new Vector(y*vec.z - z*vec.y, z*vec.x - x*vec.z, x*vec.y - y*vec.x);
  } 
  
  public double cosine(Vector vec){
    return this.scalar_pro(vec)/(vec.length()*this.length());
  }
  
  public Vector sum(Vector vec){
    return new Vector(x + vec.x, y + vec.y, z + vec.z);
  }
  
  public Vector difference(Vector vec){
    return new Vector(x-vec.x, y - vec.y, z - vec.z);
  }
  
  public static Vector [] vector_array(int n){
    Vector [] array = new Vector[n];
    Random random = new Random();
    for (int i = 0; i < array.length; i++){
      array[i] =  new Vector(random.nextInt(100), random.nextInt(100), random.nextInt(100));
    }
      return array;
  }
  
  @Override
  public String toString(){
    return "Vector ("+ x + ", " + y + ", " + z + ")";
  }
}