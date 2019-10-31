package pt.ulusofona.aed.songsExpert;

public class AuxObjects {
}

class Similars{
    String idA;
    String idB;

    Similars(){
    }

    Similars(String a, String b){
        this.idA = a;
        this.idB = b;
    }
}

class Location{
    String id;
    String place;

    Location(){
    }

    Location(String id, String place){
        this.id = id;
        this.place = place;
    }
}

class WordCounter{
    String word;
    Integer count;
}

class ThemeCounter{
    String artista;
    Integer count;
}

class SimilarCounter{
    String artistName;
    Integer count;
}
