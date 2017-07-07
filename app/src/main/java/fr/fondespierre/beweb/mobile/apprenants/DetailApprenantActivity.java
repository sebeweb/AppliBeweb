package fr.fondespierre.beweb.mobile.apprenants;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.fondespierre.beweb.mobile.apprenants.adapters.ListeApprenantAtapter;
import fr.fondespierre.beweb.mobile.apprenants.adapters.ProjetApprenantAdapter;
import fr.fondespierre.beweb.mobile.apprenants.adapters.SkillApprenantAdapter;
import fr.fondespierre.beweb.mobile.apprenants.dal.Datas;

/**
 * Created by sebastien on 04/07/17.
 */

public class DetailApprenantActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_apprenant);
        ListView listeSkill = (ListView)findViewById(R.id.detailApp_liste_skill);
        ListView listeProjet = (ListView)findViewById(R.id.detailApp_liste_projets);

//       int id = getIntent().getExtras().getInt("id");

        JSONObject data = null;
        try {
            data = Datas.apprenant;

            TextView textNom = (TextView)findViewById(R.id.detailApp_nom);
            TextView textPrenom = (TextView)findViewById(R.id.detailApp_prenom);
            TextView textAge = (TextView)findViewById(R.id.detailApp_age);
            textNom.setText(data.getString("nom"));
            textPrenom.setText(data.getString("prenom"));
            textAge.setText(data.getString("age"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ProjetApprenantAdapter projetAdapter = null;
        try {
            SkillApprenantAdapter skillAdapter = new SkillApprenantAdapter(this, 0, data);
            listeSkill.setAdapter(skillAdapter);

            projetAdapter = new ProjetApprenantAdapter(this, 0, data);
            listeProjet.setAdapter(projetAdapter);
            LinearLayout projet = (LinearLayout) findViewById(R.id.projet_item);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
