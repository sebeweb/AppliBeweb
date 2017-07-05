package fr.fondespierre.beweb.mobile.apprenants;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import fr.fondespierre.beweb.mobile.apprenants.adapters.ListeApprenantAtapter;
import fr.fondespierre.beweb.mobile.apprenants.dal.Datas;

/**
 * Created by sebastien on 04/07/17.
 */

public class ListeApprenantsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_apprenants);

        ListView listeApprenant = (ListView)findViewById(R.id.listeApprenant);

        JSONArray listeDatas = null;
        try {
            listeDatas = Datas.getApprenants();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListeApprenantAtapter adapter = new ListeApprenantAtapter(this, 0, listeDatas);

        listeApprenant.setAdapter(adapter);
    }
}
