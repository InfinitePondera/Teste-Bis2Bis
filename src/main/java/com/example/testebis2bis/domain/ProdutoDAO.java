package com.example.testebis2bis.domain;

import com.example.testebis2bis.entity.Produto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Named
public class ProdutoDAO {
    private static String url = "jdbc:postgresql://localhost:5432/teste_bis2bis";
    private static String user = "postgres";
    private static String senha = "lightmyfire";

    public ProdutoDAO() {
        try {
            Class.forName("org.postgresql.Driver");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    private List<Produto> parseResultSet(ResultSet rs, List<Produto> produtos) throws SQLException {
        while (rs.next()) {
            Produto produto = new Produto();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setMercadoAlvo(rs.getString("mercado_alvo"));

            String tecnologiasStr = rs.getString("tecnologias");
            produto.setTecnologias(tecnologiasStr.split(","));

            produtos.add(produto);
        }
        return produtos;
    }

    public List<Produto> listarProdutos() {
        String sql = "SELECT * FROM produto";
        List<Produto> produtos = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, senha); PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                return parseResultSet(rs, produtos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public List<Produto> filtrarProdutos(String[] tecnologias) {
        String sql = "SELECT * FROM produto WHERE ";
        List<Produto> produtos = new ArrayList<>();

        if (tecnologias.length > 0) {
            for (int i = 0; i < tecnologias.length; i++) {
                if (i > 0) {
                    sql += " OR ";
                }
                sql += "array_to_string(tecnologias, ',') LIKE '%" + tecnologias[i] + "%'";
            }
        }

        try (Connection connection = DriverManager.getConnection(url, user, senha); PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                return parseResultSet(rs, produtos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

}
