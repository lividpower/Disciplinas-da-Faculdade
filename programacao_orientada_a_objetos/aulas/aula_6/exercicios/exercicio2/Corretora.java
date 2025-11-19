import java.util.List;
import java.util.ArrayList;

public class Corretora {

    public static void calculaMediaValorImoveis(List arrayImoveis, List arrayMedias) {
        int numImoveisVenda = 0, numImoveisAluguel = 0;
        double mediaAluguel = 0, mediaVenda = 0;
        for(int i = 0; i < arrayImoveis.size(); i++) {
            //.get() retorna um Object, caso eu não defina o tipo genérico para a classe List
            //como o objeto que está sendo manipulado é do tipo Imovel, é possível eu converter este Object para Imovel!
            Imovel imovel = (Imovel) arrayImoveis.get(i); //como estou definindo uma variável do tipo Imovel, devo converter o Object antes de atribuí-lo, posto que nem todo Object é um tipo Imovel...a hierarquia apenas funciona de baixo para cima!!! 
            if(imovel.getTipoNegocio().equals("venda")) {
                numImoveisVenda++;
                mediaVenda += imovel.getValorComercial(); //estamos calculando a média dos imóveis que estão para venda!
            }
            else if(imovel.getTipoNegocio().equals("aluguel")) {
                numImoveisAluguel++;
                mediaAluguel += imovel.getValorComercial();
            }
        }
        arrayMedias.add("Média do preço de venda dos imóveis disponíveis para venda: " + (mediaVenda / numImoveisVenda)); //irei retornar uma String já pronta para ser exibida!
        arrayMedias.add("Média do preço de aluguel dos imóveis disponíveis para aluguel: " + (mediaAluguel / numImoveisAluguel));
    }


    public static void main(String args[]) {
        Imovel imovel_1 = new Casa(360, true);
        Imovel imovel_2 = new Apto(424, 5, 1);
        Imovel imovel_3 = new Casa(361, false);
        Imovel imovel_4 = new Apto(101, 4, 1);
        imovel_1.setTipoNegocio("aluguel");
        imovel_2.setTipoNegocio("venda");
        imovel_3.setTipoNegocio("venda");
        imovel_4.setTipoNegocio("aluguel");
        imovel_1.setValorComercial(5000); //preço de aluguel
        imovel_2.setValorComercial(1000000); //preço de compra
        imovel_3.setValorComercial(800000);
        imovel_4.setValorComercial(7000);
        List arrayImoveis = new ArrayList();
        Imovel aux[] = {imovel_1, imovel_2, imovel_3, imovel_4};
        for(int i = 0; i < aux.length; i++) { //este "i" é local a estrutura for()
            arrayImoveis.add(aux[i]);
        }
        System.out.println("Lista de Imóveis da Corretora:");
        for(int i = 0; i < arrayImoveis.size(); i++) {
            System.out.println("Imovel " + (i + 1) + ": " + ((Imovel) arrayImoveis.get(i)).getID());
        }
        List arrayMedias = new ArrayList(); //tentativa de passar esta variável como referência para o método abaixo!
        calculaMediaValorImoveis(arrayImoveis, arrayMedias);
        System.out.println(arrayMedias.get(0));
        System.out.println(arrayMedias.get(1));
    }

}
        

