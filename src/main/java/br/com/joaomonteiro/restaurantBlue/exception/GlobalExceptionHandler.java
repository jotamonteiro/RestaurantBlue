package br.com.joaomonteiro.restaurantBlue.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import feign.FeignException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNaoEncontrado(EntidadeNaoEncontradaException ex) {
        log.warn("Entidade não encontrada: {}", ex.getMessage());
        return new ErrorResponse(404, "Não encontrado", ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidacao(MethodArgumentNotValidException ex) {
        String erros = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        log.warn("Erro de validação: {}", erros);
        return new ErrorResponse(400, "Erro de validação", erros, LocalDateTime.now());
    }

    @ExceptionHandler(FeignException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleFeignException(FeignException ex) {
        log.warn("Erro ao consultar serviço externo (CEP): {}", ex.getMessage());
        return new ErrorResponse(422, "Serviço externo indisponível",
            "Não foi possível consultar o CEP informado. Verifique o CEP ou tente novamente.", LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenerico(Exception ex) {
        log.error("Erro inesperado: {}", ex.getMessage(), ex);
        return new ErrorResponse(500, "Erro interno", "Ocorreu um erro inesperado.", LocalDateTime.now());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMensagemIlegivel(HttpMessageNotReadableException ex) {
        log.warn("Erro de desserialização: {}", ex.getMessage());
        String mensagem = "Formato de dado inválido.";
        if (ex.getCause() instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException ife) {
            mensagem = String.format("Valor '%s' inválido para o campo '%s'. Verifique o formato esperado.",
                    ife.getValue(), ife.getPath().isEmpty() ? "desconhecido" : ife.getPath().get(0).getFieldName());
        }
        return new ErrorResponse(400, "Requisição inválida", mensagem, LocalDateTime.now());
    }
}