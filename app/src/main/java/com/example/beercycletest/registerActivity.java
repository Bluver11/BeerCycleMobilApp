package com.example.beercycletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

/**
 * The type Register activity.
 */
public class registerActivity extends AppCompatActivity {

    private EditText editTextUserName;
    private EditText editTextTextEmail;
    private EditText editTextTextPassword;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private Button registerButton;
    private String url = "http://10.0.2.2:3000/user/register";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        init();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUserName.getText().toString();
                String email = editTextTextEmail.getText().toString();
                String password = editTextTextPassword.getText().toString();
                String firstname = editTextFirstName.getText().toString();
                String lastname = editTextLastName.getText().toString();

                User user = new User(username,email,password,firstname,lastname);

                Gson gson = new Gson();

                RequestTask requestTask = new RequestTask(url,"POST",gson.toJson(user));

                requestTask.execute();
            }
        });
    }

    /**
     * Init.
     */
    public void init(){

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextTextEmail = findViewById(R.id.editTextTextEmail);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        registerButton = findViewById(R.id.registerButton);
    }
    private class RequestTask extends AsyncTask<Void, Void, Response> {
        /**
         * The Request url.
         */
        String requestUrl;
        /**
         * The Request type.
         */
        String requestType;
        /**
         * The Request params.
         */
        String requestParams;

        /**
         * Instantiates a new Request task.
         *
         * @param requestUrl    the request url
         * @param requestType   the request type
         * @param requestParams the request params
         */
        public RequestTask(String requestUrl, String requestType, String requestParams) {
            this.requestUrl = requestUrl;
            this.requestType = requestType;
            this.requestParams = requestParams;
        }

        /**
         * Instantiates a new Request task.
         *
         * @param requestUrl  the request url
         * @param requestType the request type
         */
        public RequestTask(String requestUrl, String requestType) {
            this.requestUrl = requestUrl;
            this.requestType = requestType;
        }

        @Override
        protected Response doInBackground(Void... voids) {
            Response response = null;
            try {
                switch (requestType) {
                    case "GET":
                       // response = RequestHandler.get(requestUrl);
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestParams,null);
                        break;
                    case "PUT":
                      //  response = RequestHandler.put(requestUrl, requestParams);
                        break;
                    case "DELETE":
                       // response = RequestHandler.delete(requestUrl + "/" + requestParams);
                        break;
                }
            } catch (IOException e) {
                Toast.makeText(registerActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
            return response;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            Gson converter = new Gson();
            if (response.getResponseCode() >= 400) {
                Toast.makeText(registerActivity.this, "Hiba történt a kérés feldolgozása során", Toast.LENGTH_SHORT).show();
                Log.d("onPostExecuteError: ", response.getResponseMessage());
            }
            switch (requestType) {
              /*  case "GET":
                    User[] peopleArray = converter.fromJson(response.getResponseMessage(), Person[].class);
                    people.clear();
                    people.addAll(Arrays.asList(peopleArray));
                    break;*/
                case "POST":
                    Toast.makeText(registerActivity.this, "Sikeres regisztráció", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(registerActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                /*case "PUT":
                    Person updatePerson = converter.fromJson(response.getResponseMessage(), Person.class);
                    people.replaceAll(person1 -> person1.getId() == updatePerson.getId() ? updatePerson : person1);
                    urlapAlaphelyzetbe();
                    break;*/
                /*case "DELETE":
                    int id = Integer.parseInt(requestParams);
                    people.removeIf(person1 -> person1.getId() == id);
                    break;*/
            }
        }
    }




}