package fr.fondespierre.beweb.mobile.apprenants.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.fondespierre.beweb.mobile.apprenants.DetailApprenantActivity;
import fr.fondespierre.beweb.mobile.apprenants.R;

/**
 * Created by sebastien on 05/07/17.
 */

public class SkillApprenantAdapter extends ArrayAdapter {

    private final Activity activity;
    private  final int resource = R.layout.skill_item;
    private final JSONObject apprenant;
    private final JSONArray skills;

    public SkillApprenantAdapter(@NonNull Activity activity, @LayoutRes int resource, JSONObject liste) throws JSONException {
        super(activity.getApplicationContext(), resource);
        this.activity = activity;
        this.apprenant = liste;
        this.skills = apprenant.getJSONArray("skills");
    }

    @Override
    public int getCount() {
        return skills.length();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final int index = position;
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(resource, null);

        TextView skillNom = convertView.findViewById(R.id.skill_item_nom);
        TextView skillLevel = convertView.findViewById(R.id.skill_item_niv);

        try {
            skillNom.setText(skills.getJSONObject(position).getString("name"));
            skillLevel.setText(skills.getJSONObject(position).getString("level"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView ;
    }
}

