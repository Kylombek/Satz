package kg.neobis.satz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import kg.neobis.satz.MyApplication;
import kg.neobis.satz.Model;
import kg.neobis.satz.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static kg.neobis.satz.MyApplication.api;
import static kg.neobis.satz.MyApplication.getRetrofit;
import static kg.neobis.satz.MyApplication.retrofit;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public EditText eText;
    public TextView textView;
    public Button button;
    public Spinner spinner1;
    public String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        //texts and buttons
        button = (Button) findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        eText = findViewById(R.id.editText);
        spinner1 = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.LANGUAGES,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

        final String key = this.getResources().getString(R.string.API_KEY);
        //RETROFIT API
        final String url = this.getResources().getString(R.string.BASE_URL);
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        //CLICK
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String txtToTrnslt = (String) eText.getText().toString();//text to be translated

                api.getTranslation(key,txtToTrnslt,language).enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        if(response.body()!= null){
                        Log.d("TRANSLATE", "Server response"+ response.body().toString() );
                        if(response.body().getList()!= null) {
                            textView.setText(response.body().getList().get(0));
                        }}
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Log.d("TRANSLATE", "Failure");
                    }
                });
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            String spinnerText = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), spinnerText,Toast.LENGTH_SHORT).show();
        switch (spinnerText) {
            case "Russian":
                language = "ru";
                break;
            case "English":
                language = "en";
                break;
            case "German":
                language = "de";
                break;
            case "French":
                language = "fr";
                break;
            case "Korean":
                language = "ko";
                break;
            case "Spanish":
                language = "es";
                break;
            case "Kyrgyz":
                language = "ky";
                break;
                default:
                    break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



