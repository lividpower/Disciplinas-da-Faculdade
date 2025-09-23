

public class Aluno {
    private String nome;
    private double nota1, nota2;

    public void setNome(String nome) {
    this.nome = nome;
    }

    public String getNome() { 
        return nome;
    }

    public void setNota1(double nota1) {
        if(nota1 < 0) {
            this.nota1 = 0;
        }
        else if (nota1 > 10) {
            this.nota1 = 10;
        }
        else {
            this.nota1 = nota1;
        }
    }

    public void setNota2(double nota2) {
        if(nota2 < 0) {
            this.nota2 = 0;
        }
        else if(nota2 > 10) {
            this.nota2 = 10;
        }
        else {
            this.nota2 = nota2;
        }
    }

    public double getNota1() {
        return nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public double getMediaAluno() {
        double media = (this.getNota1() + this.getNota2()) / 2;
        return media;
    }

    public String getSituacaoAluno(double media) {
        if(media >= 6) {
            return "Aprovado";
        }
        else if(media >= 4 & media <= 5.9) {
            return "Em recuperação";
        }
        else { //as notas estão entre 0 e 10, portanto, temos a garantia de que esse else funcionará corretamente!
            return "Reprovado";
        }
    }
}

