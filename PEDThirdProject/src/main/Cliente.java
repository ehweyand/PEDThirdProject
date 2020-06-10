/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author evand
 */
public class Cliente {
    private String nome;
    private Coordinates cordenada;

    public Cliente() {
    }

    public Cliente(String nome, Coordinates cordenada) {
        this.nome = nome;
        this.cordenada = cordenada;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Coordinates getCordenada() {
        return cordenada;
    }

    public void setCordenada(Coordinates cordenada) {
        this.cordenada = cordenada;
    }
    
}
