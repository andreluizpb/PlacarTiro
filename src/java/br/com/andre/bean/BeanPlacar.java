/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andre.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author André Luiz
 */
@ManagedBean(name = "placar")
@ApplicationScoped
public class BeanPlacar {
    
    private List<String> listaLog = new ArrayList<>();
    private List<BeanAssassinato> listaAssassinatos  = new ArrayList<>();
    private List<BeanJogador> listaJogadores  = new ArrayList<>();
    private List<String> jogadores  = new ArrayList<>();
    private String numeroPartida = "";
    private String dataPartida = "";
    
    public BeanPlacar(){
        
        this.listaLog.add("23/04/2013 15:34:22 - New match 11348965 has started");
        this.listaLog.add("23/04/2013 15:36:04 - Roman killed Nick using M16");
        this.listaLog.add("23/04/2013 15:36:33 - <WORLD> killed Nick by DROWN");
        this.listaLog.add("23/04/2013 15:39:22 - Match 11348965 has ended");

        for(String log : listaLog){
            BeanAssassinato assassinato = new BeanAssassinato();
            if(log.contains("killed")){
                assassinato.setAssassino(nomeAssassino(log));
                assassinato.setMorto(nomeMorto(log));
                assassinato.setArma(nomeArma(log));
                listaAssassinatos.add(assassinato);
                assassinato = null;
            }else{
                if(log.contains("started")){
                    numeroPartida = numPartida(log);
                    dataPartida = dtPartida(log);                    
                }
            }
        }
        
        for(BeanAssassinato jogada : listaAssassinatos){
            jogadores.add(jogada.getAssassino());
            jogadores.add(jogada.getMorto());
        }
        
        Set<String> hs = new HashSet<>();
        hs.addAll(jogadores);
        jogadores.clear();
        jogadores.addAll(hs);        
        
        for(String jogador : jogadores){
            if(!jogador.equals("<WORLD>")){
                int assassinatos = 0;
                int mortes = 0;
                for(BeanAssassinato assassinato : listaAssassinatos){
                    if(jogador.equals(assassinato.getAssassino()))assassinatos++;
                    if(jogador.equals(assassinato.getMorto()))mortes++;
                }
                BeanJogador beanJogador = new BeanJogador();
                beanJogador.setNome(jogador);
                beanJogador.setAssassinatos(assassinatos);
                beanJogador.setMortes(mortes);
                listaJogadores.add(beanJogador);
                beanJogador = null;
            }
        }
    }

    private String numPartida (String log){
        int inicio = log.indexOf("match") + 7;
        int fim = log.indexOf("has") - 1;
        return log.substring(inicio, fim);
    }
    
    private String dtPartida (String log){
        return log.substring(0, 10);
    }
    
    private String nomeAssassino (String log){
        int inicio = log.indexOf("-") + 2;
        int fim = log.indexOf("killed") - 1;
        return log.substring(inicio, fim);
    }
    
    private String nomeMorto (String log){
            int inicio = log.indexOf("killed") + 7;
            String identificador = log.contains("using") ? "using" : "by";
            int fim = log.indexOf(identificador) - 1;;
            return log.substring(inicio, fim);
    }
    
    private String nomeArma (String log){
            String identificador = log.contains("using") ? "using" : "by";
            int inicio = log.indexOf(identificador) +  identificador.length() + 1;
            int fim = log.length();;
            return log.substring(inicio, fim);
    }
    
    public List<BeanAssassinato> getListaAssassinatos() {
        return listaAssassinatos;
    }

    public void setListaAssassinatos(List<BeanAssassinato> listaAssassinatos) {
        this.listaAssassinatos = listaAssassinatos;
    }

    public List<String> getListaLog() {
        return listaLog;
    }

    public void setListaLog(List<String> listaLog) {
        this.listaLog = listaLog;
    }

    public List<BeanJogador> getListaJogadores() {
        return listaJogadores;
    }

    public void setListaJogadores(List<BeanJogador> listaJogadores) {
        this.listaJogadores = listaJogadores;
    }

    public String getNumeroPartida() {
        return numeroPartida;
    }

    public void setNumeroPartida(String numeroPartida) {
        this.numeroPartida = numeroPartida;
    }

    public String getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(String dataPartida) {
        this.dataPartida = dataPartida;
    }
    
}
