package all;

public class Appartamento {
    private int millesimi,numero;
    private String proprietario;

    public Appartamento(int millesimi, int numero, String proprietario) {
        this.millesimi = millesimi;
        this.numero = numero;
        this.proprietario = proprietario;
    }

    public int getMillesimi() {
        return millesimi;
    }

    public int getNumero() {
        return numero;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setMillesimi(int millesimi) {
        this.millesimi = millesimi;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }
    public Appartamento getCopy(){
        return new Appartamento(millesimi,numero,proprietario);
    }
    @Override
    public String toString(){
        return "\nnumero: "+numero+"\nmillesimi: "+millesimi+"\nproprietario: "+proprietario;
    }
}
