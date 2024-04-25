package com.example.beercycletest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BasketFragment extends Fragment {

    private TextView empty;
    private ListView listViewBasket;
    private List<Basket> menuList = new ArrayList<>();
    private List<MenuBeer> menuBeers = new ArrayList<>();


    private String url = "http://10.0.2.2:3000/basket";
    private String basicurl = "http://10.0.2.2:3000/basket";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basket, container, false);
        init(view);
        RequestTask requestTask = new RequestTask(url, "GET");
        requestTask.execute();
        return view;
    }

    public void init(View view) {
        listViewBasket = view.findViewById(R.id.basketlist);
        empty = view.findViewById(R.id.basketempty);
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
            ImageButton outbasket = view.findViewById(R.id.tobasketbutton);
            TextView textViewName = view.findViewById(R.id.menuName);
            TextView textViewType = view.findViewById(R.id.menuType);
            TextView textViewPrice = view.findViewById(R.id.menuPrice);
            //actualComment létrehozása a commentsList listából
            MenuBeer actualbasket = getItem(position);
            textViewName.setText(actualbasket.getMenuName());
            textViewType.setText(actualbasket.getMenuType());
            textViewPrice.setText(String.valueOf(actualbasket.getMenuPrice()));
            int id = actualbasket.getId();

            outbasket.setImageResource(R.drawable.baseline_cancel_24);
            Gson gson = new Gson();

            outbasket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getContext(),"Megnyomtad a gombot",Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Megerősítés")
                            .setMessage("Tényleg kiszeretnéd törölni az adott elemet a kosaradból??")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                
                                public void onClick(DialogInterface dialog, int whichButton) {



                                    Log.d("menuFo.menu", ""+menuList.size());
                                    Log.d("menuFo.cart", ""+menuBeers.size());
                                    Basket basket = menuList.get(0);
                                    int id = actualbasket.getId();
                                    MenuId menu = new MenuId(id);
                                    Log.d("valami2:",""+menu);
                                    String basketid = String.valueOf(basket.getId());
                                    String url1 = basicurl + "/" + basketid + "/removeitems";
                                    RequestTask requestTask = new RequestTask(url1, "PATCH", gson.toJson(menu));
                                    requestTask.execute();

                                }
                            })
                            .setNegativeButton(android.R.string.no, null).create().show();
                }
            });

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
                if (menuList.isEmpty()) {
                    empty.setVisibility(View.VISIBLE);}
                else{
                    empty.setVisibility(View.GONE);
                }
            } else if (requestType.equals("PATCH")) {
                    Toast.makeText(getActivity(), "Siker", Toast.LENGTH_SHORT).show();
                    RequestTask task = new RequestTask(url,"GET");
                    task.execute();
                }
            }
        }
    }





