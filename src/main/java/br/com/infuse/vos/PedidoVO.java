package br.com.infuse.vos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PedidoVO {
    private Integer numeroControle;
    private LocalDate dataCadastro = LocalDate.now();
    private String nome;
    private double valor;
    private Integer quantidade = 1;
    private Integer codigoCliente;
}

