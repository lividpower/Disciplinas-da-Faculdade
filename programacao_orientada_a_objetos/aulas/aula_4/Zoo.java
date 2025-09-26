public class Zoo {
    public static void main(String []args) {
        Animal w [] =  new Animal[3];
        w[0] = new Leao();
        w[1] = new Urso();
        w[2] = new Elefante();
        for(int i = 0; i < w.length; i++) {
            w[i].correr();
        }

    }
    public static void teste(Animal A) {
        //...        
    }
}