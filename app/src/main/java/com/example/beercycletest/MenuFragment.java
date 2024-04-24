package com.example.beercycletest;

import static android.app.ProgressDialog.show;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MenuFragment extends Fragment {

    private ListView listViewMenu;
    private List<MenuBeer> menuList = new ArrayList<>();


    private String url = "http://10.0.2.2:3000/menu";

    private String url2 = "http://10.0.2.2:3000/basket";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        init(view);


        RequestTask task = new RequestTask(url, "GET");


        task.execute();


        return view;

    }


    public void init(View view) {
        listViewMenu = view.findViewById(R.id.listViewMenu);
        listViewMenu.setAdapter(new CommentsAdapter());
        //ArrayAdapter<MenuBeer> mAdapter = new ArrayAdapter<>(getActivity(),R.layout.list_item_menu,menuList);

    }


    private class CommentsAdapter extends ArrayAdapter<MenuBeer> {
        public CommentsAdapter() {
            super(getActivity(), R.layout.list_item_menu, menuList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //inflater létrehozása
            LayoutInflater inflater = getLayoutInflater();
            //view létrehozása a list_item_comments.xml-ből
            View view = inflater.inflate(R.layout.list_item_menu, parent, false);
            //list_item_comments.xml-ben lévő elemek inicializálása
            ImageButton tobasket = view.findViewById(R.id.tobasketbutton);
            TextView textViewName = view.findViewById(R.id.menuName);
            TextView textViewType = view.findViewById(R.id.menuType);
            TextView textViewPrice = view.findViewById(R.id.menuPrice);
            //actualComment létrehozása a commentsList listából
            MenuBeer actualMenu = menuList.get(position);

            //imageViewAvatar.setImageBitmap(getBitmapFromURL(actualComment.getAvatar()));
            textViewName.setText(actualMenu.getMenuName());
            textViewType.setText(actualMenu.getMenuType());
            textViewPrice.setText(String.valueOf(actualMenu.getMenuPrice()));
            Gson gson = new Gson();

            tobasket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getContext(),"Megnyomtad a gombot",Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Megerősítés")
                            .setMessage("Tényleg hozzá szeretnéd adni az adott elemet a kosaradhoz?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Toast.makeText(getActivity(), "Menü hozzá addva", Toast.LENGTH_SHORT).show();


                                    int id = actualMenu.getId();
                                    MenuId menuId = new MenuId(id);

                                    RequestTask requestTask = new RequestTask(url2, "POST", gson.toJson(menuId));

                                    requestTask.execute();

                                }
                            })
                            .setNegativeButton(android.R.string.no, null).show();
                }
            });

            return view;
        }
    }


    private class RequestTask extends AsyncTask<Void, Void, Response> {
        String requestUrl;
        String requestType;
        String requestParams;

        int requestId;

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
                        response = RequestHandler.get(requestUrl, null);
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestParams, token);
                        break;
                    case "PUT":
                        // response = RequestHandler.put(requestUrl, requestParams);
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

            } else if (requestType.equals("GET")) {
                //válasz feldolgozása
                MenuBeer[] menuArray = converter.fromJson(
                        response.getResponseMessage(), MenuBeer[].class);
                //cars lista feltöltése a válasz elemeivel
                menuList.clear();
                menuList.addAll(Arrays.asList(menuArray));
                //adapter értesítése az adatváltozásról (újra kell rajzolni a listát)
                listViewMenu.invalidateViews();
                Toast.makeText(getActivity(), response.getResponseMessage(), Toast.LENGTH_SHORT).show();

            } else if (requestType.equals("POST")) {
                Toast.makeText(getActivity(), "Siker", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


