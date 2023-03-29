package com.example.civiladvocacy;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class CivicDataDownloader {
    private static RequestQueue queue;
    private static final String TAG = "CivicDataDownloader";
    private String address;
    private String urlString;
    private ArrayList<Official> officialArrayList;

    CivicDataDownloader(String address){
        this.address = address;
        this.urlString = "https://www.googleapis.com/civicinfo/v2/representatives?key=AIzaSyARoQnJ3KTol4F4A3wscTadssFObsInSN4&address=" + address;
    }

    public void downloadGoogleCivicData(MainActivity mainActivity){
        queue = Volley.newRequestQueue(mainActivity);
        Response.Listener<JSONObject> listener = response -> {
            try{
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder stringBuilderOfficialAddress = new StringBuilder();
                ArrayList<Official> daysData = new ArrayList<>();
                JSONArray offices = new JSONArray();
                JSONArray officials = new JSONArray();
                officialArrayList = new ArrayList<>();

                JSONObject normalizedInput = new JSONObject();
                normalizedInput = response.getJSONObject("normalizedInput");
                stringBuilder.append(String.format(Locale.getDefault(), "%s %s,%s,%s", normalizedInput.getString("line1"),normalizedInput.getString("city"),normalizedInput.getString("state"),normalizedInput.getString("zip")));
                mainActivity.setAddressBar(stringBuilder.toString());

                offices = response.getJSONArray("offices");
                officials = response.getJSONArray("officials");

                for(int index = 0; index < offices.length(); index++){
                    JSONObject office = offices.getJSONObject(index);
                    JSONArray officialIndices = office.getJSONArray("officialIndices");
                    for(int officialsIndex = 0; officialsIndex < officialIndices.length(); officialsIndex++){
                        Official official = new Official();
                        official.setOfficial_office(office.getString("name"));
                        JSONObject officialDetails = officials.getJSONObject(officialIndices.getInt(officialsIndex));
                        official.setOfficial_name(officialDetails.getString("name"));
                        if(officialDetails.has("address")){
                            JSONArray address = officialDetails.getJSONArray("address");
                            if(address.length() > 0){
                                JSONObject address1 = address.getJSONObject(0);
                                stringBuilderOfficialAddress.setLength(0);
                                stringBuilderOfficialAddress.append(String.format(Locale.getDefault(), "%s %s,%s,%s,%s",
                                        address1.has("line1")?address1.getString("line1"):"",
                                        address1.has("line2")?address1.getString("line2"):"",
                                        address1.has("city")?address1.getString("city"):"",
                                        address1.has("state")?address1.getString("state"):"",
                                        address1.has("zip")?address1.getString("zip"):""));
                                official.setOfficial_address(stringBuilderOfficialAddress.toString());
                            }

                        }
                        if(officialDetails.has("party")){
                            official.setOfficial_party(officialDetails.getString("party"));
                        }
                        if(officialDetails.has("phones")){
                            JSONArray phones = officialDetails.getJSONArray("phones");
                            if(phones.length() > 0){
                                String phone1 = phones.getString(0);

                                official.setPhone_number(phone1);
                            }
                        }
                        if(officialDetails.has("urls")){
                            JSONArray urls = officialDetails.getJSONArray("urls");
                            if(urls.length() > 0){
                                String url1 = urls.getString(0);

                                official.setOfficial_website(url1);
                            }
                        }
                        if(officialDetails.has("emails")){
                            JSONArray emails = officialDetails.getJSONArray("emails");
                            if(emails.length() > 0){
                                String email1 = emails.getString(0);

                                official.setOfficial_email_id(email1);
                            }
                        }
                        if(officialDetails.has("photoUrl")){
                            official.setOfficial_photo_url(officialDetails.getString("photoUrl"));
                        }
                        if(officialDetails.has("channels")){
                            JSONArray channels = officialDetails.getJSONArray("channels");
                            if(channels.length() > 0){
                                for(int i = 0;i<channels.length();i++){
                                    JSONObject obj = channels.getJSONObject(i);
                                    if(obj.getString("type").equals("Twitter")){
                                        official.setTwitter(obj.getString("id"));
                                    }
                                    if(obj.getString("type").equals("Facebook")){
                                        official.setFacebook(obj.getString("id"));
                                    }
                                    if(obj.getString("type").equals("Youtube")){
                                        official.setYoutube(obj.getString("id"));
                                    }
                                }
                            }


                        }
                        officialArrayList.add(official);


                    }
                }
                mainActivity.updateRecyclerView(officialArrayList);
            }catch (Exception e) {
                throw new RuntimeException(e);
            }



        };
        Response.ErrorListener error = error_msg ->
                Log.d(TAG, "downloadGoogleCivicData Error: " + error_msg.getMessage());
        Uri.Builder urlBuildObj = Uri.parse(urlString).buildUpon();
        //urlBuildObj.appendPath(city);


        String finalUrl = urlBuildObj.build().toString();

        try {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, finalUrl, null, listener, error);
            queue.add(jsonObjectRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
        //return officialArrayList;
    }

}
