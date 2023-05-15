package br.dev.marcosvirgilio.mobile.sos4patas.model;
public class Pessoa {
    //atributos da pessoa
    private int id;
    private String nomeSolicitante;
    private String foneSolicitante;

    public Pessoa() {
        this.setId(0);
        this.nomeSolicitante = "";
        this.foneSolicitante = "+55";
    }

    public boolean setNomeSolicitante(String nome) {
        boolean valido = false;
        if (nome.matches("[a-zA-Z]+") && (nome.length() > 5)) {
            this.nomeSolicitante = nome;
            valido = true;
        } else {
            this.nomeSolicitante = "Anômimo";
        }
        return valido;
    }

    public String getFoneSolicitante() {
        return foneSolicitante;
    }

    public void setFoneSolicitante(String foneSolicitante) {
        this.foneSolicitante = foneSolicitante;
    }

    public String getNomeSolicitante() {
        return this.nomeSolicitante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

