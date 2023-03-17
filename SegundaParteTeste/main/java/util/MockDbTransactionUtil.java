package util;

import annotation.Transaction;
import domain.ItemDAO;
import service.ItemService;

public class MockDbTransactionUtil {

    private ItemService itemService;

    public ItemService initService() {
        ItemDAO pDAO = new ItemDAO();
        ItemService pService = new ItemService();
        pService.setItemDAO(pDAO);

        return pService;
    }

    @Transaction
    public void mockDBTransaction() {
        itemService = initService();
        itemService.listarProdutos();
    }
}
