package fr.fondespierre.beweb.mobile.apprenants.dal.enumerations;

/**
 * Created by sebastien on 05/07/17.
 */

public enum Status {
    Chomage("chomage"), En_poste("en poste"), Formation("formation");

    private String name = "";

    Status(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name ;
    }
}
