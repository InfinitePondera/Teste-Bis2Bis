import annotation.Transaction;
import util.MockDbTransactionUtil;

import java.lang.reflect.Method;

public class SegundoTeste {

    public static void main(String[] args) {
        try {
            MockDbTransactionUtil mockDbTransactionUtil = new MockDbTransactionUtil();

            for (Method metodo : mockDbTransactionUtil.getClass().getDeclaredMethods()) {
                if (metodo.isAnnotationPresent(Transaction.class)) {
                    System.out.println("Iniciando execução do método " + metodo.getName() + "." + metodo.getDeclaringClass() + "...");
                    metodo.invoke(mockDbTransactionUtil);
                    System.out.println("Finalizando execução do método " + metodo.getName() + "." + metodo.getDeclaringClass() + " com sucesso.");
                }
            }

        } catch (Exception e) {
            System.out.println("Finalizando execução com erro: " + e.getMessage());
        }
    }
}