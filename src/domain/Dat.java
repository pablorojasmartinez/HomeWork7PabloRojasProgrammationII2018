/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

/**
 * 
 * @author Pablo Rojas Martínez
 */
public class Dat {
    private int nivel;
    private String nombre;
public Dat(){
nivel=0;
nombre="";
}
    public Dat(int nivel, String nombre) {
        this.nivel = nivel;
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

}
