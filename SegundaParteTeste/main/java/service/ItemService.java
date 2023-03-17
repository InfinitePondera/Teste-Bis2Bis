package service;

import domain.ItemDAO;
import entity.Item;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApplicationScoped
@Named
@Getter
@Setter
public class ItemService {

    @Inject
    private ItemDAO itemDAO;

    public List<Item> listarProdutos() {
        return itemDAO.listarItens();
    }

}
