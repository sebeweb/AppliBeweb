package fr.fondespierre.beweb.mobile.apprenants.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.fondespierre.beweb.mobile.apprenants.DetailApprenantActivity;
import fr.fondespierre.beweb.mobile.apprenants.R;
import fr.fondespierre.beweb.mobile.apprenants.dal.Datas;

/**
 * Created by sebastien on 05/07/17.
 */

public class ListeApprenantAtapter extends ArrayAdapter {

    private final Activity activity;
    private  final int resource = R.layout.liste_apprenant_item;
    private final JSONArray apprenants;
    private final JSONArray filterApprenants;

    public ListeApprenantAtapter(@NonNull Activity activity, @LayoutRes int resource, JSONArray liste) {
        super(activity.getApplicationContext(), resource);
        this.activity = activity;
        this.apprenants = liste;
        this.filterApprenants = liste;
    }

    @Override
    public int getCount() {
        return apprenants.length();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final int index = position;
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(resource, null);


        TextView textNom = convertView.findViewById(R.id.listeAppItem_nom);
        TextView textPrenom = convertView.findViewById(R.id.listeAppItem_prenom);
        TextView textSkill = convertView.findViewById(R.id.listeAppItem_skill);
        ImageButton detail = (ImageButton) convertView.findViewById(R.id.listeAppItem_imageArrowRight);

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(activity.getApplicationContext(), DetailApprenantActivity.class);
                    Datas.apprenant = apprenants.getJSONObject(index);
                    activity.startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



        try {
            textNom.setText(apprenants.getJSONObject(position).getString("nom"));
            textPrenom.setText(apprenants.getJSONObject(position).getString("prenom"));
            textSkill.setText(getHighSkillName(apprenants.getJSONObject(position).getJSONArray("skills")));
//            apprenantFilter();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView ;
    }

    private static String getHighSkillName(JSONArray skill) throws JSONException {
        int position = 0;
        float highLevel = 0;
        for (int i = 0; i < skill.length(); i++){
            float level = Float.parseFloat(skill.getJSONObject(i).getString("level"));
            if (level > highLevel){
                highLevel = level;
                position = i;
            }
        }
        return skill.getJSONObject(position).getString("name");
    }

//    public void getApprenantByFilter(){
//
//    }
}