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
    private Fornecedor fornProximo;
    private double dist;

    public Cliente() {
    }

    public Cliente(String nome, Coordinates cordenada) {
        this.nome = nome;
        this.cordenada = cordenada;
    }
// Still not usable for now.   
//    public Cliente(Cliente cli, Fornecedor fornProximo, double dist) {
//        this.nome = cli.getNome();
//        this.cordenada = cli.getCordenada();
//        this.fornProximo = fornProximo;
//        this.dist = dist;
//    }

    public Cliente(String nome, Coordinates cordenada, Fornecedor fornProximo, double dist) {
        this.nome = nome;
        this.cordenada = cordenada;
        this.fornProximo = fornProximo;
        this.dist = dist;
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

    public Fornecedor getFornProximo() {
        return fornProximo;
    }

    public void setFornProximo(Fornecedor fornProximo) {
        this.fornProximo = fornProximo;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

}
