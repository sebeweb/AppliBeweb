package fr.fondespierre.beweb.mobile.apprenants.adapters;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.fondespierre.beweb.mobile.apprenants.R;

/**
 * Created by sebastien on 05/07/17.
 */

public class ProjetApprenantAdapter extends ArrayAdapter {
    private final Activity activity;
    private  final int resource = R.layout.projet_item;
    private final JSONObject apprenant;
    private final JSONArray projets;

    public ProjetApprenantAdapter(@NonNull Activity activity, @LayoutRes int resource, JSONObject liste) throws JSONException {
        super(activity.getApplicationContext(), resource);
        this.activity = activity;
        this.apprenant = liste;
        this.projets = apprenant.getJSONArray("projets");
    }

    @Override
    public int getCount() {
        return projets.length();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final int index = position;
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(resource, null);

        TextView projetNom = convertView.findViewById(R.id.projet_item_nom);

        try {
            projetNom.setText(projets.getJSONObject(position).getString("titre"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView ;
    }
}
