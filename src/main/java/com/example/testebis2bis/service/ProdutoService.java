package com.example.testebis2bis.service;

import com.example.testebis2bis.domain.ProdutoDAO;
import com.example.testebis2bis.entity.Produto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
@Named
@Getter
@Setter
public class ProdutoService {

    @Inject
    private ProdutoDAO produtoDAO;

    public List<Produto> listarProdutos() {
        return produtoDAO.listarProdutos();
    }

    public List<Produto> filterProduto(String tecnologias, String mercadoAlvo) {
        List<Produto> produtosUnfiltered = produtoDAO.filtrarProdutos(tecnologias.split(","));
        List<Produto> produtosFiltered = new ArrayList<>();

        if (mercadoAlvo != null) {
            produtosFiltered = produtosUnfiltered
                    .stream()
                    .filter(p -> Objects.equals(p.getMercadoAlvo(), mercadoAlvo))
                    .collect(Collectors.toList());
        }

        return produtosFiltered;
    }
}
