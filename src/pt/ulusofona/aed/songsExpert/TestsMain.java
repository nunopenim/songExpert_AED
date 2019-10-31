package pt.ulusofona.aed.songsExpert;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import pt.ulusofona.aed.songsExpert.*;
import pt.ulusofona.aed.songsExpert.Main;

import java.util.ArrayList;

//Main.lerFicheiros("test-files/deisi_artists.csv","test-files/deisi_songs.csv","test-files/deisi_artist_similarity.csv", "test-files/deisi_artist_location.csv");



public class TestsMain {

    @Test
    public void testarCountSongs(){
        Main.ThemeList = new ArrayList<>();
        Main.ArtistsArray = new ArrayList<>();
        Artista artista = new Artista("1234", "Johnny Cash", "San Quentin");
        ArrayList<Artista> contribs = new ArrayList<>();
        contribs.add(artista);
        TemaMusical tema = new TemaMusical("1", "Ring of Fire", contribs, 1975);
        Main.ThemeList.add(tema);
        String text = Main.askTheExpert("COUNT_SONGS");
        String expected = "1";
        assertEquals("Esperado não igual ao obtido", expected, text);
    }

    @Test
    public void testarSongsWithoutYear(){
        Main.ThemeList = new ArrayList<>();
        Main.ArtistsArray = new ArrayList<>();
        Artista artist = new Artista("1238", "Johnny Cash", "Folsom Prison");
        ArrayList<Artista> contrib = new ArrayList<>();
        contrib.add(artist);
        TemaMusical tema = new TemaMusical("1", "Ring of Fire", contrib, 1975);
        Main.ThemeList.add(tema);
        tema = new TemaMusical("2", "Ring of Fire", contrib);
        Main.ThemeList.add(tema);
        String text = Main.askTheExpert("COUNT_SONGS_WITHOUT_YEAR");
        String expected = "1";
        assertEquals("Esperado não igual ao obtido", expected, text);
    }

    @Test
    public void testarArtistsManySongs(){
        Main.ThemeList = new ArrayList<>();
        Main.ArtistsArray = new ArrayList<>();
        Artista artist = new Artista("1438", "Johnny Cash", "San Quentin");
        artist.songCount = 1;
        Main.ArtistsArray.add(artist);
        artist = new Artista("1222", "Jose Santos", "Lisboa");
        artist.songCount = 0;
        Main.ArtistsArray.add(artist);
        artist = new Artista("1266", "Jose", "Lisboa");
        artist.songCount = 78;
        Main.ArtistsArray.add(artist);
        String text = Main.askTheExpert("COUNT_ARTISTS_MANY_SONGS 0");
        String expected = "2";
        assertEquals("Esperado não igual ao obtido", expected, text);
    }

    @Test
    public void testarSongsManyArtists(){
        Main.ThemeList = new ArrayList<>();
        Main.ArtistsArray = new ArrayList<>();
        Artista artista = new Artista("1284", "Johnny Cash", "San Quentin");
        ArrayList<Artista> contribs = new ArrayList<>();
        contribs.add(artista);
        TemaMusical tema = new TemaMusical("1", "Ring of Fire", contribs, 1975);
        Main.ThemeList.add(tema);
        String text = Main.askTheExpert("COUNT_SONGS_MANY_ARTISTS 0");
        String expected = "1";
        assertEquals("Esperado não igual ao obtido", expected, text);
    }

    @Test
    public void testeRandomInput(){
        Main.ThemeList = new ArrayList<>();
        Main.ArtistsArray = new ArrayList<>();
        String text = Main.askTheExpert("ASDDF");
        String expected = "Query com formato inválido. Tente novamente.";
        assertEquals("Esperado não igual ao obtido", expected, text);
    }

    @Test
    public void testarTemaMusicaltoString(){
        ArrayList<Artista> artistas = new ArrayList<>();
        Artista artista1 = new Artista("ID", "José", "espanha");
        Artista artista2 = new Artista("ID", "Pedro", "lisboa");
        artistas.add(artista1);
        artistas.add(artista2);
        String id = "ASD";
        String titulo = "titulo teste";
        int ano = 1974;
        TemaMusical temaATestar = new TemaMusical(id, titulo, artistas, ano);
        String expected = id + " | " + titulo + " | " + ano + " | " + artista1.nome + " (0 - 0)";
        assertEquals("Esperado não igual ao obtido", expected, temaATestar.toString());
    }

    @Test
    public void testarCountUniqueTitles(){
        Main.lerFicheiros("test-files/deisi_artists.csv","test-files/deisi_songs.csv","test-files/deisi_artist_similarity.csv", "test-files/deisi_artist_location.csv");
        String text = Main.askTheExpert("COUNT_UNIQUE_TITLES");
        String expected = "8";
        assertEquals("Esperado não igual ao obtido", expected, text);
    }

    @Test
    public void testarSongsWithArtists(){
        Main.lerFicheiros("test-files/deisi_artists.csv","test-files/deisi_songs.csv","test-files/deisi_artist_similarity.csv", "test-files/deisi_artist_location.csv");
        String text = Main.askTheExpert("GET_SONGS_WITH_ARTISTS Johnny Cash");
        String expected = "Train Of Love|Train Of Love";
        assertEquals("Esperado não igual ao obtido", expected, text);
    }

    @Test
    public void testarSongsWithArtists2(){
        Main.lerFicheiros("test-files/deisi_artists.csv","test-files/deisi_songs.csv","test-files/deisi_artist_similarity.csv", "test-files/deisi_artist_location.csv");
        String text = Main.askTheExpert("GET_SONGS_WITH_ARTISTS Nuno Penim");
        String expected = "N/A";
        assertEquals("Esperado não igual ao obtido", expected, text);
    }

    @Test
    public void testarTopArtistsSongsInYear(){
        Main.lerFicheiros("test-files/deisi_artists.csv","test-files/deisi_songs.csv","test-files/deisi_artist_similarity.csv", "test-files/deisi_artist_location.csv");
        String text = Main.askTheExpert("GET_TOP_ARTISTS_WITH_SONGS_IN_YEAR 1 2000");
        String expected = "Eminem 2\n";
        assertEquals("Esperado não igual ao obtido", expected, text);
    }

    @Test
    public void testarUniqueSongsByArtists(){
        Main.lerFicheiros("test-files/deisi_artists.csv","test-files/deisi_songs.csv","test-files/deisi_artist_similarity.csv", "test-files/deisi_artist_location.csv");
        String text = Main.askTheExpert("GET_UNIQUE_SONGS_BY_ARTIST Eminem");
        String expected = "The Real Slim Shady";
        assertEquals("Esperado não igual ao obtido", expected, text);
    }

    @Test
    public void testarArtistsWordsInTitle(){
        Main.lerFicheiros("test-files/deisi_artists.csv","test-files/deisi_songs.csv","test-files/deisi_artist_similarity.csv", "test-files/deisi_artist_location.csv");
        String text = Main.askTheExpert("COUNT_ARTISTS_WITH_WORD_IN_SONG_TITLE The");
        String expected = "2";
        assertEquals("Esperado não igual ao obtido", expected, text);
    }

    @Test
    public void testarArtistsWithLocation(){
        Main.lerFicheiros("test-files/deisi_artists.csv","test-files/deisi_songs.csv","test-files/deisi_artist_similarity.csv", "test-files/deisi_artist_location.csv");
        String text = Main.askTheExpert("GET_ARTISTS_WITH_LOCATION Brighton");
        String expected = "Fatboy Slim";
        assertEquals("Esperado não igual ao obtido", expected, text);
    }

    @Test
    public void testarNArtistsWithLocation(){
        Main.lerFicheiros("test-files/deisi_artists.csv","test-files/deisi_songs.csv","test-files/deisi_artist_similarity.csv", "test-files/deisi_artist_location.csv");
        String text = Main.askTheExpert("TOP_N_ARTISTS_WITH_LOCATION 1 Brighton");
        String expected = "Fatboy Slim:2";
        assertEquals("Esperado não igual ao obtido", expected, text);
    }

    @Test
    public void testarNArtistsWithLocation2(){
        Main.lerFicheiros("test-files/deisi_artists.csv","test-files/deisi_songs.csv","test-files/deisi_artist_similarity.csv", "test-files/deisi_artist_location.csv");
        String text = Main.askTheExpert("TOP_N_ARTISTS_WITH_LOCATION 1 Lisboa");
        String expected = "";
        assertEquals("Esperado não igual ao obtido", expected, text);
    }

    @Test
    public void testarFrequentWordTitlesLength(){
        Main.lerFicheiros("test-files/deisi_artists.csv","test-files/deisi_songs.csv","test-files/deisi_artist_similarity.csv", "test-files/deisi_artist_location.csv");
        String text = Main.askTheExpert("MOST_FREQUENT_WORD_TITLES 2");
        String expected = "To";
        assertEquals("Esperado não igual ao obtido", expected, text);
    }

    @Test
    public void testarArtistMostUniqueSongsAno(){
        Main.lerFicheiros("test-files/deisi_artists.csv","test-files/deisi_songs.csv","test-files/deisi_artist_similarity.csv", "test-files/deisi_artist_location.csv");
        String text = Main.askTheExpert("ARTIST_MOST_UNIQUE_SONGS 2000");
        String expected = "Eminem";
        assertEquals("Esperado não igual ao obtido", expected, text);
    }

    @Test
    public void testarTopNSimilars(){
        Main.lerFicheiros("test-files/deisi_artists.csv","test-files/deisi_songs.csv","test-files/deisi_artist_similarity.csv", "test-files/deisi_artist_location.csv");
        String text = Main.askTheExpert("TOP_N_SIMILAR_ARTISTS 1");
        String expected = "Johnny Cash:1";
        assertEquals("Esperado não igual ao obtido", expected, text);
    }

    @Test
    public void testeWhySoSimilar(){
        Main.lerFicheiros("test-files/deisi_artists.csv","test-files/deisi_songs.csv","test-files/deisi_artist_similarity.csv", "test-files/deisi_artist_location.csv");
        String text = Main.askTheExpert("WHY_SO_SIMILAR? ARH861H1187B9B799E ASD");
        String expected = "N/A";
        assertEquals("Esperado não igual ao obtido", expected, text);
    }

    @Test
    public void testInsert(){
        Main.lerFicheiros("test-files/deisi_artists.csv","test-files/deisi_songs.csv","test-files/deisi_artist_similarity.csv", "test-files/deisi_artist_location.csv");
        String text = Main.askTheExpert("INSERT_SONG 1234,ASD,,Johnny Cash");
        String expected = "OK";
        assertEquals("Esperado não igual ao obtido", expected, text);
    }

    @Test
    public void testInsertTwice(){
        Main.lerFicheiros("test-files/deisi_artists.csv","test-files/deisi_songs.csv","test-files/deisi_artist_similarity.csv", "test-files/deisi_artist_location.csv");
        Main.askTheExpert("INSERT_SONG 1234,ASD,,Johnny Cash");
        String text = Main.askTheExpert("INSERT_SONG 1234,ASD,,Johnny Cash");
        String expected = "Erro";
        assertEquals("Esperado não igual ao obtido", expected, text);
    }

}
