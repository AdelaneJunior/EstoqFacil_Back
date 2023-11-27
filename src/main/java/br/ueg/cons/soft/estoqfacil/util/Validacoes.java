package br.ueg.prog.webi.api.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacoes {
    @SuppressWarnings("RegExpRedundantEscape")
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    // Aceita formatos como (99) 9999-9999, (99) 99999-9999, 9999-9999, 99999-9999, 999999999, 9999999999
    private  static final String TELEFONE_PATTERN = "^(\\(?\\d{2}\\)?\\s?)?\\d{4,5}-?\\d{4}$";

    /**
     * Função para validar e-mail com expressão regular
     * @param email - email a ser validado
     * @return true se o texto passado é um e-mail válido, false caso contrário
     */
    public boolean isEmailValido(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Função para validar telefone com expressão regular
     * @param numero - telefone a ser validado
     * @return true se o texto passado é um telefone válido, false caso contrário
     */
    public boolean validarTelefone(String numero) {
        Pattern pattern = Pattern.compile(TELEFONE_PATTERN);
        Matcher matcher = pattern.matcher(numero);
        return matcher.matches();
    }

    /**
     * Função para validar cpf com calculo de digito verificador e tamanho
     * @param cpf - cpf a ser validado
     * @return true se o texto passado é um cpf válido, false caso contrário
     */

    public boolean validarCPF(String cpf){
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF possui 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Converte o CPF para um array de inteiros
        int[] digitos = new int[11];
        for (int i = 0; i < 11; i++) {
            digitos[i] = Integer.parseInt(String.valueOf(cpf.charAt(i)));
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += digitos[i] * (10 - i);
        }
        int resto = 11 - (soma % 11);
        int digitoVerificador1 = (resto == 10 || resto == 11) ? 0 : resto;

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += digitos[i] * (11 - i);
        }
        resto = 11 - (soma % 11);
        int digitoVerificador2 = (resto == 10 || resto == 11) ? 0 : resto;


        if (digitoVerificador1 == digitos[9] && digitoVerificador2 == digitos[10])
            return true;
        return false;
    }

}
