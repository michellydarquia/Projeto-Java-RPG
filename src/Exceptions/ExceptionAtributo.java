package Exceptions;

public class ExceptionAtributo extends RuntimeException{

    private String mensagem ;

    public ExceptionAtributo(String atributo){
        super();
        this. mensagem = VerificarQualAtributoParaMensagem(atributo);

    }

    public String VerificarQualAtributoParaMensagem(String atributo){
        if (atributo.equals("saude")){
            mensagem = "SAUDE INVALIDA";
        }else if(atributo.equals("energia")){
            mensagem = "ENERGIA INVALIDA";
        }else if(atributo.equals("defesa")){
            mensagem = "DEFESA INVALIDA";
        }else if(atributo.equals("ataque")){
            mensagem = "ATAQUE INVALIDA";
        }else if(atributo.equals("experiencia")){
            mensagem = "EXPERIENCIA INVALIDA";
        }else{
            mensagem = "ATRIBUTO INVALIDO";
        }
        return mensagem;

    }

    @Override
    public String getMessage() {
        return mensagem;
    }


}
