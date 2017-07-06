package fr.fondespierre.beweb.mobile.apprenants.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import fr.fondespierre.beweb.mobile.apprenants.DetailApprenantActivity;
import fr.fondespierre.beweb.mobile.apprenants.R;

/**
 * Created by sebastien on 05/07/17.
 */

public class ListeApprenantAtapter extends ArrayAdapter {

    private final Activity activity;
    private  final int resource = R.layout.liste_apprenant_item;
    private final JSONArray apprenants;

    public ListeApprenantAtapter(@NonNull Activity activity, @LayoutRes int resource, JSONArray liste) {
        super(activity.getApplicationContext(), resource);
        this.activity = activity;
        this.apprenants = liste;
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
        ImageButton detail = (ImageButton) convertView.findViewById(R.id.listeAppItem_imageArrowRight);

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(activity.getApplicationContext(), DetailApprenantActivity.class);
                    intent.putExtra("id", apprenants.getJSONObject(index).getInt("id"));
                    activity.startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            textNom.setText(apprenants.getJSONObject(position).getString("nom"));
            textPrenom.setText(apprenants.getJSONObject(position).getString("prenom"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView ;
    }
}