package br.com.joaomonteiro.restaurantBlue.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidationValidator implements ConstraintValidator<CpfValidation, String> {

    @Override
    public void initialize(CpfValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Se o valor é nulo ou vazio, consideramos válido (outras validações podem tratar isso)
        if (value == null || value.trim().isEmpty()) {
            return true;
        }

        // Remove caracteres especiais (pontos, hífens, espaços)
        String cpf = value.replaceAll("[^0-9]", "");

        // Verifica se tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se não é uma sequência repetida (ex: 111.111.111-11)
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int digit1 = 11 - (sum % 11);
        digit1 = (digit1 >= 10) ? 0 : digit1;

        // Calcula o segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int digit2 = 11 - (sum % 11);
        digit2 = (digit2 >= 10) ? 0 : digit2;

        // Verifica se os dígitos verificadores estão corretos
        return cpf.charAt(9) == Character.forDigit(digit1, 10) &&
               cpf.charAt(10) == Character.forDigit(digit2, 10);
    }
}
