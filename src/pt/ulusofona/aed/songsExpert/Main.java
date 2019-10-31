package pt.ulusofona.aed.songsExpert;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    static ArrayList<TemaMusical> ThemeList = new ArrayList<>();

    static ArrayList<Artista> ArtistsArray = new ArrayList<>();

    static HashMap<String, Artista> ArtistsMap = new HashMap<>();

    static HashMap<String, TemaMusical> ThemeMap = new HashMap<>();

    static HashMap<String, TemaMusical> ThemeIdMap = new HashMap<>();

    static HashMap<String, Artista> ArtistsIDMap = new HashMap<>();

    static Artista[] selectionSort(ArrayList<Artista> artistasInput){
        Artista  artistasArray[] = new Artista[artistasInput.size()];
        for(int i=0; i<artistasInput.size(); i++){
            artistasArray[i]=artistasInput.get(i);
        }
        int maiorOrdenadoPos = -1;
        while(maiorOrdenadoPos < artistasArray.length-1){
            int minPos = maiorOrdenadoPos + 1;
            for(int i = minPos + 1; i < artistasArray.length; i++){
                if(artistasArray[i].songCount>artistasArray[minPos].songCount){
                    minPos = i;
                }
            }
            maiorOrdenadoPos++;
            if(maiorOrdenadoPos!=minPos){
                Artista temp = artistasArray[maiorOrdenadoPos];
                artistasArray[maiorOrdenadoPos] = artistasArray[minPos];
                artistasArray[minPos] = temp;
            }
        }
        return artistasArray;
    }

    static ThemeCounter[] selectionSortAno(ArrayList<ThemeCounter> artistasInput){
        ThemeCounter  artistasArray[] = new ThemeCounter[artistasInput.size()];
        for(int i=0; i<artistasInput.size(); i++){
            artistasArray[i]=artistasInput.get(i);
        }
        int maiorOrdenadoPos = -1;
        while(maiorOrdenadoPos < artistasInput.size()-1){
            int minPos = maiorOrdenadoPos + 1;
            for(int i = minPos + 1; i < artistasArray.length; i++){
                if(artistasArray[i].count>artistasArray[minPos].count){
                    minPos = i;
                }
            }
            maiorOrdenadoPos++;
            if(maiorOrdenadoPos!=minPos){
                ThemeCounter temp = artistasArray[maiorOrdenadoPos];
                artistasArray[maiorOrdenadoPos] = artistasArray[minPos];
                artistasArray[minPos] = temp;
            }
        }
        return artistasArray;
    }

    static WordCounter[] selectionSortWordCounter(ArrayList<WordCounter> wordcounter){
        WordCounter wordsArray[] = new WordCounter[wordcounter.size()];
        for(int i=0; i<wordsArray.length; i++){
            wordsArray[i] = wordcounter.get(i);
        }
        int maiorOrdenadoPos = -1;
        while(maiorOrdenadoPos < wordcounter.size()-1){
            int minPos = maiorOrdenadoPos + 1;
            for(int i = minPos + 1; i < wordsArray.length; i++){
                if(wordsArray[i].count>wordsArray[minPos].count){
                    minPos = i;
                }
            }
            maiorOrdenadoPos++;
            if(maiorOrdenadoPos!=minPos){
                WordCounter temp = wordsArray[maiorOrdenadoPos];
                wordsArray[maiorOrdenadoPos] = wordsArray[minPos];
                wordsArray[minPos] = temp;
            }
        }
        return wordsArray;
    }

    static SimilarCounter[] selectionSortSimilarCounter(ArrayList<SimilarCounter> similarcounter){
        SimilarCounter similarsArray[] = new SimilarCounter[similarcounter.size()];
        for(int i=0; i<similarsArray.length; i++){
            similarsArray[i] = similarcounter.get(i);
        }
        int maiorOrdenadoPos = -1;
        while(maiorOrdenadoPos < similarcounter.size()-1){
            int minPos = maiorOrdenadoPos + 1;
            for(int i = minPos + 1; i < similarsArray.length; i++){
                if(similarsArray[i].count>similarsArray[minPos].count){
                    minPos = i;
                }
            }
            maiorOrdenadoPos++;
            if(maiorOrdenadoPos!=minPos){
                SimilarCounter temp = similarsArray[maiorOrdenadoPos];
                similarsArray[maiorOrdenadoPos] = similarsArray[minPos];
                similarsArray[minPos] = temp;
            }
        }
        return similarsArray;
    }

    static ArrayList<String>leitura(String nomeFicheiro){
        try{
            int runTimes = 0;
            ArrayList<String> returnList = new ArrayList<>();
            File ficheiro = new File(nomeFicheiro);
            BufferedReader leitorFicheiro = new BufferedReader(new FileReader(ficheiro));
            String linha = null;
            while((linha = leitorFicheiro.readLine())!=null){
                returnList.add(linha);
                runTimes++;
            }
            leitorFicheiro.close();
            if(runTimes==0 || returnList.get(returnList.size()-1)!= null || returnList.get(returnList.size()-1)!=""){
                returnList.add(""); //change for something better
            }
            //System.out.println(runTimes);
            return returnList;
        }
        catch(IOException exception){
            String mensagem = "Erro: o ficheiro " + nomeFicheiro + " nao foi encontrado.";
            System.out.println(mensagem);
            ArrayList<String> stringret = new ArrayList<>();
            stringret.add("");
            return stringret;
        }
    }

    static ArrayList<String> cleanupLoc(ArrayList<String> dirty, int numSeparators){
        int counter =0;
        ArrayList<String> cleanString = new ArrayList<>();
        for (int count = 0; count<dirty.size()-1; count++){
            boolean aspas = false;
            int separadores = 0;
            String linha = dirty.get(count);
            for(int j = 0; j<linha.length(); j++){
                if(linha.charAt(j) == ',' && !aspas){ //nao contabilizamos as virgulas dentro de aspas
                    separadores++;
                }
                else if(linha.charAt(j) == '"'){
                    aspas = !aspas;
                }
            }
            if(separadores==numSeparators && linha.charAt(linha.length()-1)!=','){
                String retS = "";
                for(int i=0; i<linha.length(); i++){
                    if(linha.charAt(i)!='"'){
                        retS+=linha.charAt(i);
                    }
                }
                cleanString.add(retS);
                counter++;
            }
        }
        //System.out.println(counter);
        return cleanString;
    }

    static ArrayList<String> cleanup(ArrayList<String> dirty, int numSeparators){
        int counter =0;
        ArrayList<String> cleanString = new ArrayList<>();
        for (int count = 0; count<dirty.size()-1; count++){
            int separadores = 0;
            String linha = dirty.get(count);
            for(int j = 0; j<linha.length(); j++){
                if(linha.charAt(j) == ','){ //nao contabilizamos as virgulas dentro de aspas
                    separadores++;
                }
            }
            if(separadores==numSeparators && linha.charAt(linha.length()-1)!=','){
                cleanString.add(linha);
                counter++;
            }
        }
        //System.out.println(counter);
        return cleanString;
    }

    static void processaArtistas(ArrayList<String> artista, ArrayList<String> localizacao){
        ArrayList<String> cleanArtists = cleanup(artista, 1);
        ArrayList<String> cleanLocalizacoes = cleanupLoc(localizacao, 1);
        ArrayList<Location> locationArray = new ArrayList<>();
        HashMap<String, Location> locationMap = new HashMap<>();
        //cleanArtists = cleanup(artista, 1);
        //cleanLocalizacoes = cleanupLoc(localizacao, 1);
        for(int i=0; i<cleanLocalizacoes.size(); i++){
            Location local = new Location();
            String analise = cleanLocalizacoes.get(i);
            String dividida[] = analise.split(",", 2);
            local.id = dividida[0];
            local.place = dividida[1];
            locationArray.add(local);
            locationMap.put(local.id, local);
        }
        for(int i=0; i<cleanArtists.size(); i++){
            Artista artist = new Artista();
            String analise = cleanArtists.get(i);
            String dividida[] = analise.split(",");
            artist.id = dividida[0];
            artist.nome = dividida[1];
            if(locationMap.get(artist.id)!=null){
                artist.localizacao = locationMap.get(artist.id).place;
            }
            ArtistsArray.add(artist);
            ArtistsMap.put(artist.nome, artist);
            ArtistsIDMap.put(artist.id, artist);
        }
    }

    static void processaSemelhantes(ArrayList<String> semelhantes){
        ArrayList<String> cleanSemelhantes = cleanup(semelhantes, 1);
        //cleanSemelhantes = cleanup(semelhantes, 1);
        for(int i=0; i<cleanSemelhantes.size(); i++){
            String idArray[] = cleanSemelhantes.get(i).split(",");
            if(idArray.length!=2){
                break;
            }
            else{
                String idA = idArray[0];
                String idB = idArray[1];
                if(ArtistsIDMap.get(idA) != null && ArtistsIDMap.get(idB) != null){
                    if(ArtistsIDMap.get(idA).semelhante == null){
                        ArtistsIDMap.get(idA).semelhante = new ArrayList<>();
                    }
                    ArtistsIDMap.get(idA).semelhante.add(ArtistsIDMap.get(idB));
                }
            }
        }
    }

    static ArrayList<TemaMusical> processaMusicas(ArrayList<String> songs){
        ArrayList<TemaMusical> returnSongs = new ArrayList<>();
        ArrayList<String> cleanSongs = cleanup(songs, 3);
        //cleanSongs = cleanup(songs, 3);
        for(int i=0; i<cleanSongs.size();i++){
            ArrayList<Artista> contributingArtists = new ArrayList<>();
            TemaMusical tema = new TemaMusical();
            HashMap<String, Artista> contributingMap = new HashMap<>();
            String analise = cleanSongs.get(i);
            String dividida[] = analise.split(",");
            //System.out.println(dividida[3]);
            String artistasString[] = dividida[3].split(";");
            for(int k=0; k<artistasString.length; k++){
                String artistaAProcurar = artistasString[k];
                if(ArtistsMap.get(artistaAProcurar) != null){
                    contributingArtists.add(ArtistsMap.get(artistaAProcurar));
                    contributingMap.put(ArtistsMap.get(artistaAProcurar).nome, ArtistsMap.get(artistaAProcurar));
                    ArtistsMap.get(artistaAProcurar).songCount++;
                }
            }
            boolean isNumeric = true;
            String id = dividida[0];
            String titulo = dividida[1];
            int ano = -1;
            if(dividida[2].length()>0){
                try{
                    ano = Integer.parseInt(dividida[2]);
                }
                catch (NumberFormatException | NullPointerException nfe) {
                    isNumeric = false;
                }
            }
            tema.id = id;
            tema.titulo = titulo;
            tema.ano = ano;
            tema.envolvido = contributingArtists;
            tema.envolvidoMap = contributingMap;
            //if(ThemeMap.get(titulo)!=null){
                //tema.unique = false;
                //ThemeMap.get(titulo).unique = false;
            //}
            //else{
                //System.out.println(tema.titulo);
                //tema.unique = true;
            //}
            if(tema.envolvido.size()>0 && isNumeric){
                returnSongs.add(tema);
                ThemeMap.put(tema.titulo, tema);
                ThemeIdMap.put(tema.id, tema);
            }
        }
        //função de des-bug
        return returnSongs;
    }

    public static void lerFicheiros(String nomeFicheiro1, String nomeFicheiro2, String nomeFicheiro3, String nomeFicheiro4){
        ThemeList = new ArrayList<>();
        ArtistsArray = new ArrayList<>();
        ArtistsMap = new HashMap<>();
        ThemeMap = new HashMap<>();
        ArtistsIDMap = new HashMap<>();
        ThemeIdMap = new HashMap<>();
        ArrayList<String> artists = leitura(nomeFicheiro1);
        ArrayList<String> songs = leitura(nomeFicheiro2);
        ArrayList<String> similarities = leitura(nomeFicheiro3);
        ArrayList<String> location = leitura(nomeFicheiro4);
        //artists = leitura(nomeFicheiro1); //aqui temos strings "sujas" carregadas na memória
        //songs = leitura(nomeFicheiro2);
        //similarities = leitura(nomeFicheiro3);
        //location = leitura(nomeFicheiro4);
        processaArtistas(artists, location);
        processaSemelhantes(similarities);
        ThemeList = processaMusicas(songs);
    }

    public static void lerFicheiros(){
        String nomeFicheiro1 = "deisi_artists.csv";
        String nomeFicheiro2 = "deisi_songs.csv";
        String nomeFicheiro3 = "deisi_artist_similarity.csv";
        String nomeFicheiro4 = "deisi_artist_location.csv";
        lerFicheiros(nomeFicheiro1, nomeFicheiro2, nomeFicheiro3, nomeFicheiro4);
    }

    public static ArrayList<TemaMusical> obterTemasMusicais(){
        return ThemeList;
    }

    public static String askTheExpert(String query){ //defeituosas ARTIST_MOST_UNIQUE_SONGS, WHY_SO_SIMILAR, COUNT_SONGS_BY_ARTIST_WITH_RESTRICTIONS
        if(query.equals("COUNT_SONGS")){
            int songCount = 0;
            for(int i = 0; i<ThemeList.size(); i++){
                if(ThemeList.get(i).ano>2000 && ThemeList.get(i).ano<2016 ){
                    songCount++;
                }
            }
            return ""+songCount;
        }
        else if(query.equals("COUNT_SONGS_WITHOUT_YEAR")){
            int songCount = 0;
            for(int i = 0; i<ThemeList.size(); i++){
                if(ThemeList.get(i).ano == -1){
                   songCount++;
                }
            }
            return ""+songCount;
        }
        else if(query.startsWith("COUNT_ARTISTS_MANY_SONGS ")) {
            String[] parts = query.split(" ");
            int number = Integer.parseInt(parts[1]);
            int songNumber = 0;
            for (int i = 0; i < ArtistsArray.size(); i++) {
                if (ArtistsArray.get(i).songCount > number) {
                    songNumber++;
                }
            }
            return "" + songNumber;
        }
        else if(query.startsWith("COUNT_SONGS_MANY_ARTISTS ")){ //1 valor int
            String[] parts = query.split(" ");
            int number = Integer.parseInt(parts[1]);
            int number2 = Integer.parseInt(parts[2]);
            int artistNumber = 0;
            for(int i=0; i<ThemeList.size(); i++){
                if(ThemeList.get(i).envolvido.size() > number && ThemeList.get(i).envolvido.size()<number2){
                    artistNumber++;
                }
            }
            return  String.valueOf(artistNumber);
        }
        else if(query.equals("COUNT_UNIQUE_TITLES")){ //nenhum
            return String.valueOf(ThemeMap.size());
            //int uniquesFound = 0;
            //for(int i=0; i<ThemeList.size(); i++){
            //if(ThemeList.get(i).unique){
            //uniquesFound++;
            //}
            //}
            //return String.valueOf(uniquesFound);
        }
        else if(query.startsWith("GET_SONGS_WITH_ARTISTS ")){ //varias strings
            String queryDiv[] = query.split(" ", 2);
            String artists[] = queryDiv[1].split(",");
            ArrayList<Artista> artistas = new ArrayList<>();
            ArrayList<TemaMusical> temas = new ArrayList<>();
            for(int i=0;i<artists.length; i++){
                if(artists[i].length() > 8){
                    return "NOPE";
                }
            }
            for(int i=0; i<artists.length; i++){
                if(ArtistsMap.get(artists[i]) == null){
                    return "N/A";
                }
                else{
                    Artista obj = ArtistsMap.get(artists[i]);
                    artistas.add(obj);
                }
            }
            for(int i=0; i<ThemeList.size(); i++){
                int existe = 0;
                for(int j=0; j<artistas.size(); j++) {
                    //existe = true;
                    if(ThemeList.get(i).envolvidoMap.get(artistas.get(j).nome) != null ){
                        existe++;
                    }
                }
                if(existe == artistas.size()){
                    temas.add(ThemeList.get(i));
                }
            }
            String retS = "";
            if(temas.size()==0){
                return "N/A";
            }
            else{
                for(int i=0; i<temas.size(); i++){
                    if(i==0){
                        retS += temas.get(i).titulo;
                    }
                    else{
                        retS += "|"+temas.get(i).titulo;
                    }
                }
            }
            return retS;
        }
        else if(query.startsWith("GET_TOP_ARTISTS_WITH_SONGS_IN_YEAR ")){  //2 ints
            String input[] = query.split(" ");
            if(input.length>=3){
                String retS = "";
                int numero = Integer.parseInt(input[1]);
                int ano = Integer.parseInt(input[2]);
                ArrayList<TemaMusical> temasNoAno = new ArrayList<>();
                for(int i=0; i<ThemeList.size(); i++){
                    if(ThemeList.get(i).ano == ano){
                        temasNoAno.add(ThemeList.get(i));
                    }
                }
                ArrayList<ThemeCounter> contadorTemas = new ArrayList<>();
                HashMap<String, ThemeCounter> contadorTemasMap = new HashMap<>();
                for(int i=0; i<temasNoAno.size(); i++){
                    for(int j=0; j<temasNoAno.get(i).envolvido.size(); j++){
                        if(contadorTemasMap.get(temasNoAno.get(i).envolvido.get(j).nome)==null){
                            ThemeCounter contador = new ThemeCounter();
                            contador.count=1;
                            contador.artista=temasNoAno.get(i).envolvido.get(j).nome;
                            contadorTemas.add(contador);
                            contadorTemasMap.put(contador.artista, contador);
                        }
                        else{
                            contadorTemasMap.get(temasNoAno.get(i).envolvido.get(j).nome).count++;
                        }
                    }
                }
                ThemeCounter array[] = selectionSortAno(contadorTemas);
                for(int i=0; i<array.length && i<numero; i++){
                    retS += array[i].artista+" "+array[i].count + "\n";
                }
                if(retS.length() == 0){
                    return "N/A";
                }
                return retS;
            }
        }
        else if(query.startsWith("GET_UNIQUE_SONGS_BY_ARTIST ")){ //1 string
            String input[] = query.split(" ", 2);
            if(input.length>=2){
                HashMap<String, TemaMusical> temasDoArtista = new HashMap<>();
                for(int i=0; i<ThemeList.size(); i++){
                    for(int j=0; j<ThemeList.get(i).envolvido.size(); j++){
                        if(ThemeList.get(i).envolvido.get(j).nome.equals(input[1])){
                            temasDoArtista.put(ThemeList.get(i).titulo, ThemeList.get(i));
                            break;
                        }
                    }
                }
                String retS = "";
                Set<String> keys = temasDoArtista.keySet();
                ArrayList<String> titulos = new ArrayList<String>(keys);
                Collections.sort(titulos, Collections.reverseOrder());
                for(int i=0; i<titulos.size(); i++){
                    if(i==0){
                        retS += titulos.get(i);
                    }
                    else{
                        retS +="|" + titulos.get(i);
                    }
                }
                return retS;
            }
        }
        else if(query.startsWith("COUNT_ARTISTS_WITH_WORD_IN_SONG_TITLE ")){ //1 string
            String queryDiv[] = query.split(" ");
            if(queryDiv.length==2){
                HashMap<String, Artista>mapaDeArtistas = new HashMap<>();
                String palavra = queryDiv[1];
                for(int i=0; i<ThemeList.size(); i++){
                    if(ThemeList.get(i).titulo.contains(palavra)){
                        for(int j=0; j<ThemeList.get(i).envolvido.size(); j++){
                            if(mapaDeArtistas.get(ThemeList.get(i).envolvido.get(j).nome)==null){
                                mapaDeArtistas.put(ThemeList.get(i).envolvido.get(j).nome, ThemeList.get(i).envolvido.get(j));

                            }
                        }
                    }
                }
                return String.valueOf(mapaDeArtistas.size());
            }
        }
        else if(query.startsWith("GET_ARTISTS_WITH_LOCATION ")){ //1 string
            String sample[]=query.split(" ");
            if(sample.length>=2){
                String parts[] = query.split(" ", 2);
                ArrayList<String> artistsRet = new ArrayList<>();
                String ret = "";
                String loc = parts[1];
                for(int i=0; i<ArtistsArray.size(); i++){
                    if(ArtistsArray.get(i).localizacao != null && ArtistsArray.get(i).localizacao.contains(loc)){
                        artistsRet.add(ArtistsArray.get(i).nome);
                    }
                }
                Collections.sort(artistsRet);
                for(int i=0; i<artistsRet.size(); i++){
                    //System.out.println(artistsRet.get(i));
                    if(i!=artistsRet.size()-1){
                        ret += artistsRet.get(i)+"\n";
                    }
                    else{
                        ret += artistsRet.get(i); //+"\n";
                    }
                }
                //ret+=loc;
                return ret;
            }
        }
        else if(query.startsWith("TOP_N_ARTISTS_WITH_LOCATION ")){ //1 int e 1 string
            String sample[]=query.split(" ");
            if(sample.length>=3){
                String parts[] = query.split(" ", 3);
                ArrayList<Artista> artistsRet = new ArrayList<>();
                String ret = "";
                String loc = parts[2];
                int n = Integer.parseInt(parts[1]);
                for(int i=0; i<ArtistsArray.size(); i++){
                    if(ArtistsArray.get(i).localizacao != null && ArtistsArray.get(i).localizacao.contains(loc)){
                        artistsRet.add(ArtistsArray.get(i));
                    }
                }
                Artista artistasArray[] = selectionSort(artistsRet);
                for(int i=0; i<artistasArray.length && i<n; i++){
                    //System.out.println(artistsRet.get(i));
                    if(i!=artistsRet.size()-1 && i!=n-1){
                        ret += artistasArray[i].nome+":"+artistasArray[i].songCount+"\n";
                    }
                    else{
                        ret += artistasArray[i].nome+":"+artistasArray[i].songCount;
                    }
                }
                return ret;
            }
        }
        else if(query.startsWith("MOST_FREQUENT_WORD_TITLES ")){ //um int
            String leitura[] = query.split(" ");
            if(leitura.length >= 2 && Integer.parseInt(leitura[1]) != 0){
                int wordLength = Integer.parseInt(leitura[1]);
                HashMap<String, WordCounter> wordMap = new HashMap<>();
                ArrayList<WordCounter> contadorPalavras = new ArrayList<>();
                for(int i=0; i<ThemeList.size(); i++){
                    String palavras[] = ThemeList.get(i).titulo.split(" "); //split on everything non-alphanumeric, thanks stack overflow...
                    for(int j=0; j<palavras.length; j++){
                        if(palavras[j].length() == wordLength){
                            if(wordMap.get(palavras[j].toLowerCase())==null){
                                WordCounter contador1 = new WordCounter();
                                contador1.count=1;
                                contador1.word=palavras[j];
                                wordMap.put(palavras[j].toLowerCase(), contador1);
                                contadorPalavras.add(contador1);
                            }
                            else{
                                wordMap.get(palavras[j].toLowerCase()).count++;
                            }
                        }
                    }
                }
                WordCounter words[] = selectionSortWordCounter(contadorPalavras);
                if(words[0].count>words[1].count){
                    return words[0].word;
                }
                else{
                    ArrayList<String> retSArray = new ArrayList<>();
                    for(int i=0; words[0] == words[i]; i++){
                        retSArray.add(words[i].word);
                    }
                    String retS = "";
                    for(int i=0; i<retSArray.size(); i++){
                        if(i==0){
                            retS += retSArray.get(i);
                        }
                        else{
                            retS += "|"+retSArray.get(i);
                        }
                    }
                    return retS;
                }
            }
        }
        else if(query.startsWith("ARTIST_MOST_UNIQUE_SONGS ")){ //um ano
            String dados[] = query.split(" ");
            if(dados.length>=2){
                int ano = Integer.parseInt(dados[1]);
                ArrayList<TemaMusical> temasDoAno = new ArrayList<>();
                Collection<TemaMusical> mapValues = ThemeMap.values();
                ArrayList<TemaMusical> uniqueTheme = new ArrayList<>(mapValues); //https://javaconceptoftheday.com/convert-hashmap-to-arraylist-in-java/
                for(int i=0; i<uniqueTheme.size(); i++){
                    if(uniqueTheme.get(i).ano == ano){
                        temasDoAno.add(uniqueTheme.get(i));
                    }
                }
                if(temasDoAno.size()==0){
                    return "N/A";
                }
                ArrayList<ThemeCounter> counter = new ArrayList<>();
                HashMap<String, ThemeCounter> counterMap = new HashMap<>();
                for(int i=0; i<temasDoAno.size(); i++){
                    for(int j=0; j<temasDoAno.get(i).envolvido.size(); j++){
                        if(counterMap.get(temasDoAno.get(i).envolvido.get(j).nome)==null){
                            ThemeCounter counterObj = new ThemeCounter();
                            counterObj.count=1;
                            counterObj.artista=temasDoAno.get(i).envolvido.get(j).nome;
                            counterMap.put(temasDoAno.get(i).envolvido.get(j).nome, counterObj);
                            counter.add(counterObj);
                        }
                        else{
                            counterMap.get(temasDoAno.get(i).envolvido.get(j).nome).count += 1;
                        }
                    }
                }
                ThemeCounter temasOrdenados[] = selectionSortAno(counter);
                return temasOrdenados[0].artista;
            }
        }
        else if(query.startsWith("TOP_N_SIMILAR_ARTISTS ")){ //1 int
            String dados[] = query.split(" ");
            if(dados.length >=2){
                boolean skip = false;
                int valor = 0;
                try{
                    valor = Integer.parseInt(dados[1]);
                }
                catch(NumberFormatException e){
                    skip = true;
                }
                if(!skip){
                    String retS = "";
                    ArrayList<SimilarCounter> similarCountdown = new ArrayList<>();
                    for(int i=0; i<ArtistsArray.size(); i++){
                        SimilarCounter contador = new SimilarCounter();
                        contador.artistName = ArtistsArray.get(i).nome;
                        if(ArtistsArray.get(i).semelhante != null){
                            contador.count = ArtistsArray.get(i).semelhante.size();
                            similarCountdown.add(contador);
                        }
                    }
                    SimilarCounter array[] = selectionSortSimilarCounter(similarCountdown);
                    for(int i=0; i<array.length && i<valor; i++){
                        if(i!=similarCountdown.size()-1 && i!=valor-1){
                            //retS.append(array[i].artistName);
                            //retS.append(":");
                            //retS.append("\n");
                            retS += array[i].artistName+":"+array[i].count+"\n";
                        }
                        else{
                            //retS.append(array[i].artistName);
                            //retS.append(":");
                            //retS.append("\n");
                            retS += array[i].artistName+":"+array[i].count;
                        }
                    }
                    return retS;
                }
            }
        }
        else if(query.startsWith("WHY_SO_SIMILAR? ")){ //2 strings
            String dados[] = query.split(" ");
            if(dados.length>=3){
                String idA = dados[1];
                String idB = dados[2];
                if(!(idA.equals(idB))){
                    if(ArtistsIDMap.get(idA) != null && ArtistsIDMap.get(idB) != null){
                        ArrayList<String> artistasNomes = new ArrayList<>();
                        StringBuffer retS = new StringBuffer();
                        Artista artistaA = ArtistsIDMap.get(idA);
                        Artista artistaB = ArtistsIDMap.get(idB);
                        for(int i=0; i<artistaA.semelhante.size(); i++){
                            for(int j=0; j<artistaB.semelhante.size(); j++){
                                if(artistaB.semelhante.get(j).equals(artistaA.semelhante.get(i))){
                                    artistasNomes.add(artistaB.semelhante.get(j).nome);
                                }
                            }
                        }
                        Collections.sort(artistasNomes);
                        for(int i=0; i<artistasNomes.size(); i++){
                            if(i==0){
                                retS.append(artistasNomes.get(0));
                                //retS += artistasNomes.get(0);
                            }
                            else{
                                retS.append(" \n");
                                retS.append(artistasNomes.get(0));
                                //retS += " \n"+artistasNomes.get(i);
                            }
                        }
                        return retS.toString();
                    }
                    else{
                        return "N/A";
                    }
                }
                return "N/A";
            }
        }
        else if(query.startsWith("COUNT_SONGS_BY_ARTIST_WITH_RESTRICTIONS ")){ //1 string, 1 boolean

        }
        else if(query.startsWith("SONGS_WITH_SIMILAR_ARTISTS? ")){ //2 strings
            String data[] = query.split(" ");
            if(data.length>=3){
                String idA = data[1];
                String idB = data[2];
                TemaMusical tema1 = ThemeIdMap.get(idA);
                TemaMusical tema2 = ThemeIdMap.get(idB);
                if(tema1 == null || tema2 == null){
                    return "N/A";
                }
                for(int i=0; i<tema1.envolvido.size(); i++){
                    for(int j=0; j<tema2.envolvido.size(); j++){
                        for(int k=0; k<tema2.envolvido.get(j).semelhante.size(); k++){
                            if(tema1.envolvido.get(i).equals(tema2.envolvido.get(j).semelhante.get(k))){
                                return "YAY";
                            }
                        }
                    }
                }
                return "NAY";
            }
        }
        else if(query.startsWith("INSERT_SONG ")){ //ID, nome, ano, artista
            String data[] = query.split(" ",2);
            if(data.length>=2){
                String fields[] = data[1].split(",");
                if(fields.length==4){
                    String id = fields[0];
                    String nome = fields[1];
                    int ano;
                    try{
                        ano = Integer.parseInt(fields[2]);
                    }
                    catch(NumberFormatException e){
                        ano = -1;
                    }
                    String artista = fields[3];
                    if(artista.equals("") || ArtistsMap.get(artista) == null || id.equals("") || nome.equals("")||ThemeIdMap.get(id)!=null){
                        return "Erro";
                    }
                    ArrayList<Artista> contrib = new ArrayList<>();
                    HashMap<String, Artista> contribMap = new HashMap<>();
                    contribMap.put(ArtistsMap.get(artista).nome, ArtistsMap.get(artista));
                    contrib.add(ArtistsMap.get(artista));
                    TemaMusical song = new TemaMusical();
                    song.id = id;
                    song.titulo = nome;
                    song.ano = ano;
                    song.envolvido = contrib;
                    song.envolvidoMap = contribMap;
                    ArtistsMap.get(artista).songCount += 1;
                    ThemeList.add(song);
                    ThemeMap.put(song.titulo, song);
                    ThemeIdMap.put(song.id, song);
                    return "OK";
                }
            }
        }
        else if(query.startsWith("REMOVE_SONG ")){ //ID
            String data[] = query.split(" ");
            if(data.length >=2){
                String remId = data[1];
                for(int i=0; i<ThemeList.size(); i++){
                    if(ThemeList.get(i).id.equals(remId)){
                        for(int j=0; j<ThemeList.get(i).envolvido.size(); j++){
                            ThemeList.get(i).envolvido.get(j).songCount -= 1;
                        }
                        ThemeList.remove(i);
                        ThemeIdMap.remove(remId);
                        return "OK";
                    }
                }
                return "Erro";
            }
        }
        return "Query com formato inválido. Tente novamente.";
    }

    public static void main(String[] args) {
        ArrayList<TemaMusical> tema = new ArrayList<>();
        boolean sair = false;
        Main.lerFicheiros();
        tema = Main.obterTemasMusicais();
        System.out.println("Bem-vindos ao Especialista Musical do DEISI...");
        //System.out.println(ArtistsArray.get(1234).localizacao);
        while(!sair){
            String scannerString = null;
            Scanner terminal = new Scanner(System.in);
            scannerString = terminal.nextLine();
            if(scannerString.equals("QUIT")){
                System.out.println("Adeus.");
                sair = true;
            }
            else{
                long t1 = System.currentTimeMillis();
                String queryRet = askTheExpert(scannerString);
                long t2 = System.currentTimeMillis();
                long deltaT = t2-t1;
                System.out.println(queryRet);
                if(!queryRet.equals("Query com formato inválido. Tente novamente.")){
                    System.out.println("(demorou "+deltaT+" ms)");
                }
            }
        }
    }
}

