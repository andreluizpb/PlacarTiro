/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andre.bean;

/**
 *
 * @author André Luiz
 */
public class BeanJogador {
    
    String nome;
    int assassinatos;
    int mortes;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAssassinatos() {
        return assassinatos;
    }

    public void setAssassinatos(int assassinatos) {
        this.assassinatos = assassinatos;
    }

    public int getMortes() {
        return mortes;
    }

    public void setMortes(int mortes) {
        this.mortes = mortes;
    }
    
    
}
