/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andre.bean;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author André Luiz
 */
public class BeanPlacarTest {
    
    /**
     * Teste da extração de arquivo de log
     * Principal método da construção da classe
     * Irá alimentar os demais objetos do sistema
     */
    @Test
    public void testExtracaoLog() {
        System.out.println("extração do arquivo de log");
        BeanPlacar instance = new BeanPlacar();
        List<String> listaLog = new ArrayList<String>();
        listaLog.add("23/04/2013 15:36:04 - TesteAssassino killed TesteMorto using TesteArma");
        instance.setListaLog(listaLog);
        instance.geraPlacar();
        List<BeanAssassinato> listaBeanAssassinatos = instance.getListaAssassinatos();
        for(BeanAssassinato testBeanAssassinato : listaBeanAssassinatos){
            assertEquals("TesteAssassino", testBeanAssassinato.getAssassino());
            assertEquals("TesteMorto", testBeanAssassinato.getMorto());
            assertEquals("TesteArma", testBeanAssassinato.getArma());
        }
    }

    /**
     * Teste da extração do número da partida
     */
    @Test
    public void testNumPartida() {
        System.out.println("extração do extração do número da partida");
        BeanPlacar instance = new BeanPlacar();
        String log = "23/04/2013 15:34:22 - New match 12345678 has started";
        assertEquals("12345678", instance.numPartida(log));
    }

    /**
     * Teste da extração da data da partida
     */
    @Test
    public void testDtPartida() {
        System.out.println("extração do extração da data da partida");
        BeanPlacar instance = new BeanPlacar();
        String log = "12/12/2012 15:34:22 - New match 12345678 has started";
        assertEquals("12/12/2012", instance.dtPartida(log));
    }

}
