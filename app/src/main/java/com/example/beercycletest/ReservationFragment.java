package com.example.beercycletest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class ReservationFragment extends Fragment {

    private Spinner dropdown;
    private ImageView bicycleimage;
    private LockableScrollView scrollView;

    private LinearLayout reservationMenu;

    private Button scroll1;
    private ListView listViewBasket;
    private List<Basket> menuList = new ArrayList<>();
    private List<MenuBeer> menuBeers = new ArrayList<>();
    private String url = "http://10.0.2.2:3000/basket";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_reservation, container, false);

        init(view);


        String[] items = new String[]{"Small", "Medium", "Large"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        bicycleimage.setImageResource(R.drawable.small);
                        break;

                    case 1:
                        bicycleimage.setImageResource(R.drawable.mediumbike);
                        break;

                    case 2:
                        bicycleimage.setImageResource(R.drawable.largebike);

                    default:
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        scroll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollToContent(v);
                RequestTask requestTask = new RequestTask(url, "GET");
                requestTask.execute();
            }
        });




        return view;

    }
    public void scrollToContent(View view) {
        int scrollToY = reservationMenu.getTop(); // A megfelelő hely Y koordinátája
        Log.d("valami1:",""+reservationMenu.getTop());
        scrollView.smoothScrollTo(1, scrollToY);
    }


    public void init(View view){
        listViewBasket = view.findViewById(R.id.reservationListMenu);
        scrollView = view.findViewById(R.id.reservationscroll);
        scrollView.setScrollingEnabled(false);
        reservationMenu = view.findViewById(R.id.reservationMenu);
        scroll1 = view.findViewById(R.id.scroll1);
        dropdown = view.findViewById(R.id.dropdown);
        bicycleimage = view.findViewById(R.id.imagebicycle);
    }
    private class BasketAdapter extends ArrayAdapter<MenuBeer> {
        public BasketAdapter(Context context, List<MenuBeer> menuBeers) {
            super(getActivity(), R.layout.list_item_menu, menuBeers);
        }
        public BasketAdapter() {
            super(getActivity(), R.layout.list_item_menu, menuBeers);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //inflater létrehozása
            LayoutInflater inflater = getLayoutInflater();
            //view létrehozása a list_item_comments.xml-ből
            View view = inflater.inflate(R.layout.list_item_menu, parent, false);
            //list_item_comments.xml-ben lévő elemek inicializálása
            TextView textViewName = view.findViewById(R.id.menuName);
            TextView textViewType = view.findViewById(R.id.menuType);
            TextView textViewPrice = view.findViewById(R.id.menuPrice);
            ImageView imageViewMenu = view.findViewById(R.id.imagetomenu);
            //actualComment létrehozása a commentsList listából
            MenuBeer actualbasket = getItem(position);
            textViewName.setText(actualbasket.getMenuName());
            textViewType.setText(actualbasket.getMenuType());
            textViewPrice.setText(String.valueOf(actualbasket.getMenuPrice()));

            String menuName = actualbasket.getMenuName();
            int imageResourceId = getResources().getIdentifier(menuName.toLowerCase().replace(" ","_").replace("(","").replace(")",""), "drawable", getActivity().getPackageName());
            imageViewMenu.setImageResource(imageResourceId);
            int id = actualbasket.getId();

            Gson gson = new Gson();


            return view;
        }
    }

    private class RequestTask extends AsyncTask<Void, Void, Response> {
        String requestUrl;
        String requestType;
        String requestParams;

        public RequestTask(String requestUrl, String requestType, String requestParams) {
            this.requestUrl = requestUrl;
            this.requestType = requestType;
            this.requestParams = requestParams;
        }


        public RequestTask(String requestUrl, String requestType) {
            this.requestUrl = requestUrl;
            this.requestType = requestType;
        }

        @Override
        protected Response doInBackground(Void... voids) {
            Response response = null;
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
            String token = sharedPreferences.getString("token", "");
            try {
                switch (requestType) {
                    case "GET":
                        response = RequestHandler.get(requestUrl, token);
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestParams, token);
                        break;
                    case "PATCH":
                        response = RequestHandler.patch(requestUrl, requestParams, token);
                        break;
                    case "DELETE":
                        //  response = RequestHandler.delete(requestUrl + "/" + requestParams);
                        break;
                }
            } catch (IOException e) {
                //Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
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
            if (response.getResponseCode() == 400) {
                Toast.makeText(getActivity(),
                        "Hiba történt a kérés feldolgozása során", Toast.LENGTH_SHORT).show();
                Log.d("onPostExecuteError:", response.getResponseMessage());
            } else if (response.getResponseCode() == 401) {
                Toast.makeText(getActivity(), "Kérlek jelenkez be újra!", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();

            } else if (requestType.equals("GET")) {
                //válasz feldolgozása

                Basket[] menuArray = converter.fromJson(
                        response.getResponseMessage(), Basket[].class);
                menuBeers.clear();
                for (Basket basket : menuArray) {
                    menuBeers.addAll(basket.getMenu());
                }


                listViewBasket.setAdapter(new BasketAdapter(getActivity(), menuBeers));
                //cars lista feltöltése a válasz elemeivel
                menuList.clear();
                menuList.addAll(Arrays.asList(menuArray));
                Log.d("valami1:",""+menuBeers.size());
                //adapter értesítése az adatváltozásról (újra kell rajzolni a listát)


                listViewBasket.invalidateViews();
            }
        }
    }
}
