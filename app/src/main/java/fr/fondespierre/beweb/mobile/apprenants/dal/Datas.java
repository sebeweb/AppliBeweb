package fr.fondespierre.beweb.mobile.apprenants.dal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.fondespierre.beweb.mobile.apprenants.dal.enumerations.Status;

/**
 * Created by sebastien on 05/07/17.
 */

public class Datas {

    public static JSONObject apprenant;

    public static JSONArray getApprenants() throws JSONException {
        JSONArray apprenants = new JSONArray();
        JSONObject seb = new JSONObject();
        JSONObject jc = new JSONObject();
        JSONObject marie = new JSONObject();

        seb.putOpt("id",1);
        seb.putOpt("nom","Javary");
        seb.putOpt("prenom","Sebastien");
        seb.putOpt("age",26);
        seb.putOpt("promotion",1);
        seb.putOpt("session",1);
        seb.putOpt("status", Status.Formation);

        jc.putOpt("id",2);
        jc.putOpt("nom","Petetin");
        jc.putOpt("prenom","Jean Christian");
        jc.putOpt("age", 12);
        jc.putOpt("promotion",1);
        jc.putOpt("session",2);
        jc.putOpt("status",Status.Formation);

        marie.putOpt("id",3);
        marie.putOpt("nom","Urbano");
        marie.putOpt("prenom","Marie");
        marie.putOpt("age", 26);
        marie.putOpt("promotion",2);
        marie.putOpt("session",1);
        marie.putOpt("status",Status.Formation);

        apprenants.put(seb);
        apprenants.put(jc);
        apprenants.put(marie);

        return apprenants;
    }

    /**
     * l'objet retourné contient la liste des projets et la liste des skills
     *
     * @param id
     * @return
     */
    public static JSONObject getApprenant(int id) throws JSONException {
        JSONObject apprenant = new JSONObject();
        JSONArray getApp = Datas.getApprenants();
        for (int i = 0; i < getApp.length(); i++){
            if (getApp.getJSONObject(i).getInt("id") == id){
                apprenant = getApp.getJSONObject(i);
                apprenant.putOpt("skills" ,Datas.getSkills(id) );
                apprenant.putOpt("projets", Datas.getProjets(id));
            }
        }

        return apprenant;
    }

    public static JSONArray getPromotions() throws JSONException {
        JSONArray promotions = new JSONArray();
        JSONObject beziers = new JSONObject();
        JSONObject lunel1 = new JSONObject();
        JSONObject lunel2 = new JSONObject();

        beziers.putOpt("id",1);
        beziers.putOpt("session",1);
        beziers.putOpt("ville","Beziers");

        lunel1.putOpt("id",2);
        lunel1.putOpt("session",1);
        lunel1.putOpt("ville","Lunel");

        lunel2.putOpt("id",3);
        lunel2.putOpt("session",2);
        lunel2.putOpt("ville","Lunel");

        promotions.put(lunel1);
        promotions.put(lunel2);
        promotions.put(beziers);

        return promotions;
    }

    /**
     * 4 projet
     * @return
     */
    private static JSONArray getProjets() throws JSONException {
        JSONArray projet = new JSONArray();
        JSONObject karism = new JSONObject();
        JSONObject findMyCoop = new JSONObject();
        JSONObject choseYourBoss = new JSONObject();
        JSONObject musive = new JSONObject();

        karism.putOpt("id",1);
        karism.putOpt("nom","Karism");
        karism.putOpt("apprenant",3);

        findMyCoop.putOpt("id",1);
        findMyCoop.putOpt("nom","findMyCoop");
        findMyCoop.putOpt("apprenant",1);

        choseYourBoss.putOpt("id",1);
        choseYourBoss.putOpt("nom","choseYourBoss");
        choseYourBoss.putOpt("apprenant",2);

        musive.putOpt("id",1);
        musive.putOpt("nom","musive");
        musive.putOpt("apprenant",1);

        projet.put(karism);
        projet.put(musive);
        projet.put(findMyCoop);
        projet.put(choseYourBoss);

        return projet;
    }

    private static JSONArray getProjets(int apprenantID) throws JSONException {
        JSONArray projets = Datas.getProjets();
        JSONArray projetsApprenant = new JSONArray();
        for (int i = 0; i < projets.length(); i++){
            if (projets.getJSONObject(i).getInt("apprenant") == apprenantID){
                projetsApprenant.put(projets.getJSONObject(i));
            }
        }
        return projetsApprenant;
    }

    /**
     * 6 skill
     * @return
     */
    private static JSONArray getSkills() throws JSONException {
        JSONArray skill = new JSONArray();
        JSONObject s1 = new JSONObject();
        JSONObject s2 = new JSONObject();
        JSONObject s3 = new JSONObject();
        JSONObject s4 = new JSONObject();
        JSONObject s5 = new JSONObject();
        JSONObject s6 = new JSONObject();


        s1.putOpt("id",1);
        s1.putOpt("nom","js");
        s1.putOpt("valeur",0.2);
        s1.putOpt("apprenant",3);

        s2.putOpt("id",2);
        s2.putOpt("nom","php");
        s2.putOpt("valeur",0.5);
        s2.putOpt("apprenant",3);

        s3.putOpt("id",3);
        s3.putOpt("nom","js");
        s3.putOpt("valeur",0.9);
        s3.putOpt("apprenant",2);

        s4.putOpt("id",4);
        s4.putOpt("nom","php");
        s4.putOpt("valeur",0.4);
        s4.putOpt("apprenant",2);

        s5.putOpt("id",5);
        s5.putOpt("nom","js");
        s5.putOpt("valeur",0.2);
        s5.putOpt("apprenant",1);

        s6.putOpt("id",6);
        s6.putOpt("nom","php");
        s6.putOpt("valeur",0.3);
        s6.putOpt("apprenant",1);

        skill.put(s1);
        skill.put(s2);
        skill.put(s3);
        skill.put(s4);
        skill.put(s5);
        skill.put(s6);

        return skill;
    }

    private static JSONArray getSkills(int apprenantID) throws JSONException {
        JSONArray skills = Datas.getSkills();
        JSONArray skillApprenant = new JSONArray();
        for (int i = 0; i < skills.length(); i++){
            if (skills.getJSONObject(i).getInt("apprenant") == apprenantID){
                skillApprenant.put(skills.getJSONObject(i));
            }
        }
        return skillApprenant;
    }
}
