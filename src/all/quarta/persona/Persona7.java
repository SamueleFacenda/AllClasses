package all.quarta.persona;

import java.util.regex.Pattern;

public class Persona7 extends Persona6 {

    public Persona7(double altezza, int eta, String nome, Data dataDiNascita, String email, String password) throws Exception {
        super(altezza, eta, nome, dataDiNascita, email, password);
    }
    @Override
    public void setNome(String nome)throws Exception{
        if(!Pattern.compile("[a-zA-Z]").matcher(nome).matches())
            throw new Exception("nome non valido");
        super.setNome(nome);
    }
    @Override
    public void setEmail(String email)throws Exception{
        if(!Pattern.compile("[a-zA-Z0-9]+@[a-zA-Z0-9.]+\\.[a-z]{3,7}+").matcher(email).matches())
            throw new Exception("email non valida");
        super.setEmail(email);
    }
    @Override
    public void setPassword(String in) throws Exception{
        if(!Pattern.compile("((?=.*\\p{Lower})(?=.*\\p{Upper})(?=.*\\d)(?=.*\\W)).{8,}").matcher(in).matches())
            throw new Exception("password non valida");
    }
}
