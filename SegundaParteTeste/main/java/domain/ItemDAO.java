package domain;

import entity.Item;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named
public class ItemDAO {
    private static String url = "jdbc:postgresql://localhost:5432/teste_bis2bis";
    private static String user = "postgres";
    private static String senha = "lightmyfire";

    public ItemDAO() {
        try {
            Class.forName("org.postgresql.Driver");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    private List<Item> parseResultSet(ResultSet rs, List<Item> produtos) throws SQLException {
        while (rs.next()) {
            Item item = new Item();
            item.setId(rs.getInt("id"));
            item.setNome(rs.getString("nome"));
            item.setDescricao(rs.getString("descricao"));
            item.setMercadoAlvo(rs.getString("mercado_alvo"));

            String tecnologiasStr = rs.getString("tecnologias");
            item.setTecnologias(tecnologiasStr.split(","));

            produtos.add(item);
        }
        return produtos;
    }

    public List<Item> listarItens() {
        String sql = "SELECT * FROM produto";
        List<Item> itens = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, senha); PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                return parseResultSet(rs, itens);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itens;
    }

}
