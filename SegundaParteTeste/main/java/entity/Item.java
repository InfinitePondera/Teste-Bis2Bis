package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private int id;
    private String nome;
    private String descricao;
    private String mercadoAlvo;
    private String[] tecnologias;
}
