package exceptions;

public class DureeException extends Exception {

    private String erreur;

    public  DureeException(String a){
        erreur = a;
    }

    public String getErreur(){
        return erreur;
    }
}
