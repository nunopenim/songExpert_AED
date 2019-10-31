package pt.ulusofona.aed.songsExpert;
import java.util.ArrayList;
import java.util.HashMap;

public class Artista {
    public String id;
    public String nome;
    public String localizacao;
    public ArrayList<Artista> semelhante; //array list
    public int songCount;

    public Artista(){
    }

    public Artista(String id, String nome, String local, ArrayList<Artista> semelhante) {
        this.id = id;
        this.nome = nome;
        this.localizacao = local;
        this.semelhante= semelhante;
    }
    public Artista(String id, String nome, String local) {
        this.id = id;
        this.nome = nome;
        this.localizacao = local;
    }
    public String toString () {
        return nome;
        //return id + "," + nome + "," + semelhante;
    }
    public Artista compareTo(Artista o){
        if(o.songCount<this.songCount){
            return o;
        }
        else{
            return this;
        }
    }

}
