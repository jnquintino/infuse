package br.com.infuse.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_controle")
    private Integer numeroControle;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro = LocalDate.now();

    private String nome;

    private double valor;

    private Integer quantidade = 1;

    @Column(name = "codigo_cliente")
    private Integer codigoCliente;

    @Column(name = "valor_total")
    private double valorTotal;
}

