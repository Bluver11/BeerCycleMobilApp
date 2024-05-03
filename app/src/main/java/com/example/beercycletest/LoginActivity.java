package com.example.beercycletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
 * The type Login activity.
 */
public class LoginActivity extends AppCompatActivity {

    private Button register;
    private EditText loginUsername;
    private EditText loginPassword;

    private Button login;
    private String url = "http://10.0.2.2:3000/auth/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        init();

        register.setOnClickListener(view ->{
            Intent intent = new Intent(LoginActivity.this,registerActivity.class);
            startActivity(intent);
            finish();
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = loginUsername.getText().toString();
                String password = loginPassword.getText().toString();

                UserLogin user = new UserLogin(username,password);

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

        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        loginUsername = findViewById(R.id.loginUsername);
        loginPassword = findViewById(R.id.loginPassword);
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
                       // response = RequestHandler.put(requestUrl, requestParams);
                        break;
                    case "DELETE":
                        //response = RequestHandler.delete(requestUrl + "/" + requestParams);
                        break;
                }
            } catch (IOException e) {
                Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(LoginActivity.this, "Hiba történt a kérés feldolgozása során", Toast.LENGTH_SHORT).show();
                Log.d("onPostExecuteError: ", response.getResponseMessage());
            }
            switch (requestType) {
              /*  case "GET":
                    User[] peopleArray = converter.fromJson(response.getResponseMessage(), Person[].class);
                    people.clear();
                    people.addAll(Arrays.asList(peopleArray));
                    break;*/
                case "POST":
                    Toast.makeText(LoginActivity.this, "Sikeres bejelentkezés", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    Gson gson = new Gson();
                    Login login1 = gson.fromJson(response.getResponseMessage(),Login.class);

                    editor.putString("token",login1.getToken());
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, MainPageActivity.class);
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