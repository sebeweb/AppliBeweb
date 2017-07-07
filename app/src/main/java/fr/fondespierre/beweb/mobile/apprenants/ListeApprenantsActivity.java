package fr.fondespierre.beweb.mobile.apprenants;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import fr.fondespierre.beweb.mobile.apprenants.adapters.ListeApprenantAtapter;
//import fr.fondespierre.beweb.mobile.apprenants.adapters.SpinnerPromotionAdapter;
import fr.fondespierre.beweb.mobile.apprenants.dal.Datas;

/**
 * Created by sebastien on 04/07/17.
 */

public class ListeApprenantsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_apprenants);

        final ListView listeApprenant = (ListView)findViewById(R.id.listeApprenant);
        final Spinner spinnerPromo = (Spinner) findViewById(R.id.listeApp_promo);
        final Activity activity = this;

        final RequestQueue request = Volley.newRequestQueue(getApplicationContext());
        final String url = "http://192.168.1.48/beweb_api/index.php/";

        JSONArray listeDatas = null;

        /**
         * send request to beweb API
         * set in liste_apprenants layout list of apprenants
         */
        JsonArrayRequest jr = new JsonArrayRequest(Request.Method.GET, url + "apprenants", listeDatas, new Response.Listener<JSONArray>() {
            //success of request
            @Override
            public void onResponse(JSONArray response) {
                ListeApprenantAtapter adapter = new ListeApprenantAtapter(activity, 0, response);
                listeApprenant.setAdapter(adapter);
            }
            //Error of request
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
        request.add(jr);

        final String ville = "";

        /**
        * send request to beweb API
        * set in Spinner list of villes
        */
        JsonArrayRequest rPromo = new JsonArrayRequest(Request.Method.GET, url + "villes", listeDatas, new Response.Listener<JSONArray>() {
            //success of request
            @Override
            public void onResponse(final JSONArray response) {
                ArrayList<String> listVilles = new ArrayList<String>();
                listVilles.add("promotion");

                for (int i = 0; i < response.length(); i++){
                    try {
                        listVilles.add(response.getString(i));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                spinnerPromo.setAdapter(new ArrayAdapter<String>(ListeApprenantsActivity.this, android.R.layout.simple_spinner_dropdown_item, listVilles));
                // listen an event on spinner
                spinnerPromo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    /**
                     * When one ville is selected get list of apprenant by villes and the session will change accordingly to the ville
                     *
                     * @param adapterView
                     * @param view
                     * @param i
                     * @param l
                     */
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        CheckedTextView ville = (CheckedTextView)view;
                        getSessionByPromotion(ville.getText().toString(), url);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
            //Error of request
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
        request.add(rPromo);
        getSkill();
    }

    private void getSessionByPromotion (String ville, String url){
        final Spinner spinnerSession = (Spinner) findViewById(R.id.listeApp_session);
        final JSONArray listeDatas = null;
        final RequestQueue request = Volley.newRequestQueue(getApplicationContext());

         /**
          * send request to beweb API
          * set in Spinner list of session
          */
        JsonArrayRequest sessionSpinner = new JsonArrayRequest(Request.Method.GET, url + "/sessions/" + ville, listeDatas, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<String> listSessions = new ArrayList<String>();
                listSessions.add("sessions");
                for (int i = 0; i < response.length(); i++){
                    try {
                        listSessions.add(response.getString(i));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                spinnerSession.setAdapter(new ArrayAdapter<String>(ListeApprenantsActivity.this, android.R.layout.simple_spinner_dropdown_item, listSessions));
                spinnerSession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    //Success of request
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
            //Error of request
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
        request.add(sessionSpinner);
    }

    /**
     * send request to beweb API
     * set in Spinner list of session
     */
    private void getSkill (){
        final String url = "http://192.168.1.48/beweb_api/index.php/";
        final Spinner spinnerSkill = (Spinner) findViewById(R.id.listeApp_skill);
        final JSONArray listeDatas = null;
        final RequestQueue request = Volley.newRequestQueue(getApplicationContext());

        JsonArrayRequest skillSpinner = new JsonArrayRequest(Request.Method.GET, url + "/skills", listeDatas, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<String> listSkill = new ArrayList<String>();
                listSkill.add("skills");
                for (int i = 0; i < response.length(); i++){
                    try {
                        listSkill.add(response.getString(i));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                spinnerSkill.setAdapter(new ArrayAdapter<String>(ListeApprenantsActivity.this, android.R.layout.simple_spinner_dropdown_item, listSkill));
                spinnerSkill.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    //Success of request
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        CheckedTextView skill = (CheckedTextView) view ;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
            //Error of request
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
        request.add(skillSpinner);
    }

}
