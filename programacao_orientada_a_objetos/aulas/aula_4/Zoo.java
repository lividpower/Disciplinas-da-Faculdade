public class Zoo {
    public static void main(String []args) {
        Animal w [] =  new Leao[3]; // também é possível atribuir Leao[3] a essa variável!!!
        w[0] = new Leao();
        w[1] = new Urso();
        w[2] = new Elefante();
        Object o = new Object(); //funciona!!!
        System.out.println(o);
        for(int i = 0; i < w.length; i++) {
            w[i].correr();
        }

    }
    public static void teste(Animal A) {
        //...        
    }
}