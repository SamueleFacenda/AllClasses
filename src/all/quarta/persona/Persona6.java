package all.quarta.persona;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.DateValidator;

import java.util.GregorianCalendar;
import java.util.Objects;

public class Persona6 extends Persona {
    private String email,password;

    public Persona6(double altezza, int eta, String nome, Data dataDiNascita, String email, String password) throws Exception {
        super(altezza, eta, nome, dataDiNascita);
        setEmail(email);
        setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email)throws Exception{
        if(EmailValidator.getInstance().isValid(email))
            this.email=email;
        else
            throw new Exception("email non valida");
    }

    public void setPassword(String password)throws Exception {
        this.password=password;
    }
    public void setData(String in)throws Exception{
        if(DateValidator.getInstance().validate(in)!=null){
            String[] da=in.split("/");
            this.setDataDiNascita(new Data(Integer.parseInt(da[0]),Integer.parseInt(da[1]),Integer.parseInt(da[2])));
        }else
            throw new Exception("data invalida");
    }
    @Override
    public int getEta(){
        try{
            return getDataDiNascita().getDiffAnni(new Data(GregorianCalendar.getInstance().getTime()));
        }catch(Exception e){
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Persona6 persona6 = (Persona6) o;
        return email.equals(persona6.email) &&
                password.equals(persona6.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, password);
    }
}
