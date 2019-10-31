package pt.ulusofona.aed.songsExpert;
import java.util.ArrayList;
import java.util.HashMap;

public class TemaMusical {
    public String id;
    public String titulo;
    public ArrayList<Artista> envolvido; //outra lista
    public int ano;
    //public boolean unique;
    public HashMap<String, Artista> envolvidoMap;
    public TemaMusical(){
    }

    public TemaMusical(String id, String titulo, ArrayList<Artista> envolvido, int ano) {
        this.id = id;
        this.titulo = titulo;
        this.envolvido = envolvido;
        this.ano = ano;
    }
    public TemaMusical(String id, String titulo, ArrayList<Artista> envolvido){
        this.id = id;
        this.titulo = titulo;
        this.envolvido = envolvido;
        this.ano = -1;
    }

    public String toString() {
        String envolvidos = "";
        int nSemelhantes;
        if(envolvido.size()==1){
            Artista musician = envolvido.get(0);
            if(musician.semelhante==null){
                nSemelhantes=0;
            }
            else{
                nSemelhantes = musician.semelhante.size();
            }
            String localizacao = musician.localizacao;
            if(localizacao == null){
                localizacao = "Desconhecida";
            }
            //envolvidos=envolvidos+musician.nome+" "+"("+musician.songCount+" - "+nSemelhantes+")";
            envolvidos=envolvidos+musician.nome+" "+"("+localizacao+")";
        }
        else{
            for (int i=0; i<envolvido.size(); i++){
                Artista musician = envolvido.get(i);
                if(musician.semelhante==null){
                    nSemelhantes=0;
                }
                else{
                    nSemelhantes = musician.semelhante.size();
                }
                String localizacao = musician.localizacao;
                if(localizacao == null){
                    localizacao = "Desconhecida";
                }
                //envolvidos=envolvidos+musician.nome+" ("+musician.songCount+" - "+nSemelhantes+")";
                envolvidos=envolvidos+musician.nome+" "+"("+localizacao+")";
                if(i<envolvido.size()-1){
                    envolvidos=envolvidos+" / ";
                }
            }
        }
        if (ano == -1){
            return id + " | " + titulo + " | " + "Ano desconhecido" + " | " + envolvidos;
        }
        return id + " | " + titulo + " | " + ano + " | " + envolvidos;
    }
}
