package br.com.aline.LiterAlura.model;

public enum Idioma {
    ALEMAO ("de", "Alemão"),
    ESPANHOL("es", "Espanhol"),
    INGLES("en", "Inglês"),
    ITALIANO ("it", "Italiano"),
    PORTUGUES ("pt", "Português");

    private final String idiomaCodigo;
    private final String idiomaPortugues;

    Idioma(String idiomaCodigo, String idiomaPortugues){
        this.idiomaCodigo = idiomaCodigo;
        this.idiomaPortugues = idiomaPortugues;
    }

    public static Idioma fromCodigo(String text) {
       try {
           for (Idioma idioma : Idioma.values()) {
               if (idioma.idiomaCodigo.equalsIgnoreCase(text)) {
                   return idioma;
               }
           }
           return null;
       }
       catch (IllegalArgumentException e){
           return null;
       }
    }

    public static Idioma fromPortugues(String text) {
        try {
            for (Idioma idioma : Idioma.values()) {
                if (idioma.idiomaPortugues.equalsIgnoreCase(text)) {
                    return idioma;
                }
            }
            return Idioma.valueOf(text.toUpperCase());
        }catch (IllegalArgumentException e){
            return null;
        }
    }
}
