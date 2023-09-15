/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author João Pedro
 */
@Named(value = "Jogador")
@ManagedBean
@SessionScoped
public class Jogador implements Serializable {


    public Jogador() {

    }


    public void verificarJogador() {


    }
    public String entrar() {
        return "jogar.xhtml";
    }

}
