package br.com.aline.LiterAlura.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
