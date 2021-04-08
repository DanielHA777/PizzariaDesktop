package Servico;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailTeste {

  
    public static void main(String[] args) {
         
        
        Email email = new Email("Segue email teste pizzaria",  // assunto
        		"Seggue usuário e senha para acesso ao sistema de auditoria\n"+"Usuario:FelipeSoster\n"+"Senha: 123456", //mensagem
        		"d.henriq77@gmail.com");  // destinatario
        
        email.enviar();
        

    }
    
}
