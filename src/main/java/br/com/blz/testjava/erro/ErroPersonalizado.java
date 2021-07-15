package br.com.blz.testjava.erro;

import java.util.ArrayList;
import java.util.List;

public final class ErroPersonalizado {

    public static ErroData erro(String code, String message) {
        //gerar um erro como resposta ( criar uma exception personalizada )
        ErroData erroData = new ErroData();
        Erro erro = new Erro();
        erro.setCode(code);
        erro.setMessage(message);
        List<Erro> lista = new ArrayList<Erro>();
        lista.add(erro);
        erroData.setError(lista);
        return erroData;

    }
}
