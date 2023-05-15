package br.dev.marcosvirgilio.mobile.sos4patas.model;

import org.json.JSONException;
import org.json.JSONObject;

public class PedidoSOS {

    //atributos pedido
    private int id;
    private String data;
    private String hora;
    private String local;
    //objeto pessoa
    private Pessoa solicitante;
    //atributos animal
    private int tipoAnimal;
    private int porteAnimal;
    private int saudeAnimal;
    private String complemento;

    //construtor

    public PedidoSOS() {
        this.solicitante = new Pessoa();
        this.id = 0;
        this.data = "00-00-0000";
        this.hora = "00:00:00";
        this.local = "";
        this.complemento = "";
        this.porteAnimal = 0;
        this.tipoAnimal = 0;
        this.saudeAnimal = 0;
    }

    public PedidoSOS(JSONObject jo) {
       try {
           this.solicitante = null;
           this.id = jo.getInt("id");
           this.data = jo.getString("data");
           this.hora = jo.getString("hora");
           this.local = jo.getString("local");
           this.complemento = jo.getString("complemento");
           this.porteAnimal = jo.getInt("cdporteanimal");
           this.tipoAnimal = jo.getInt("cdtipoanimal");
           this.saudeAnimal = jo.getInt("cdsaudeanimal");
       }catch (JSONException je){
           je.printStackTrace();
       }
    }
    //metodos
    //Metodo retorna o objeto com dados no formato JSON
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("solicitante", null);
            json.put("id", this.id);
            json.put("data", this.data);
            json.put("hora", this.hora);
            json.put("local", this.local);
            json.put("complemento", this.complemento);
            json.put("cdporteanimal", this.porteAnimal);
            json.put("cdtipoanimal", this.tipoAnimal);
            json.put("cdsaudeanimal", this.saudeAnimal);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }


    public Pessoa getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Pessoa solicitante) {
        this.solicitante = solicitante;
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }



    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        if (hora.length() == 5) {
            this.hora = hora + ":00";
        }
        if (!this.hora.matches("([0-9]{2}):([0-9]{2}):([0-9]{2})")) {
            this.hora = "00:00:00";
        }
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(int tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public int getPorteAnimal() {
        return porteAnimal;
    }

    public void setPorteAnimal(int porteAnimal) {
        this.porteAnimal = porteAnimal;
    }

    public int getSaudeAnimal() {
        return saudeAnimal;
    }

    public void setSaudeAnimal(int saudeAnimal) {
        this.saudeAnimal = saudeAnimal;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

}

