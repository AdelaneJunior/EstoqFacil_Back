package br.ueg.cons.soft.estoqfacil.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class EmailSender {

    private static final String REMETENTE = "ademirodostestes@gmail.com"; //email de testes
    private static final String SENHA = "drva pvud kekk uapp"; //senha de app do google
    private static final String PATH = "listaProdutos.pdf"; //com esse caminho o pdf é salvo dentro do projeto


    private static MultiPartEmail setEmail(String destinatario){
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(REMETENTE, SENHA));
        email.setSSLOnConnect(true);

        try {
            email.setFrom(REMETENTE);
            email.setSubject("Lista de produtos");
            email.setMsg("PDF com a lista dos produtos");
            email.addTo(destinatario);
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
        return email;
    }

    private static MultiPartEmail anexaPdf(MultiPartEmail email){
        EmailAttachment anexo = new EmailAttachment();
        anexo.setPath(PATH);
        anexo.setName("lista_de_produtos.pdf");
        try {
            email.attach(anexo);
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
        return email;
    }

    public static void enviaEmail(String destinatario) throws EmailException{
        MultiPartEmail email = setEmail(destinatario);
        email = anexaPdf(email);
        email.send();

    }
}
