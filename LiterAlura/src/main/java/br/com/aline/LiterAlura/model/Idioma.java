package br.com.aline.LiterAlura.model;

public enum Idioma {
    ALEMAO ("de"),
    ESPANHOL("es"),
    INGLES("en"),
    ITALIANO ("it"),
    PORTUGUES ("pt");

    private final String idiomaCodigo;

    Idioma(String idiomaCodigo){
        this.idiomaCodigo = idiomaCodigo;
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
}
