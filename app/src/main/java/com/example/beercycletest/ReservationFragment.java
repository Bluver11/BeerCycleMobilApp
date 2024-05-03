package com.example.beercycletest;

import static com.google.gson.internal.$Gson$Types.arrayOf;

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

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;


/**
 * The type Reservation fragment.
 */
public class ReservationFragment extends Fragment {

    private Spinner dropdown;
    private Spinner reservationTimeSpinner;
    private ImageView bicycleimage;
    private LockableScrollView scrollView;

    private LinearLayout reservationMenu;
    private CalendarView calendarView;
    private Spinner timeSlotSpinner;
    private Map<Integer, List<String>> timeSlotsByDay;

    private TextView reservationText;

    private Button scroll1;
    private Button scroll1back;
    private Button scroll2;
    private Button scroll3;
    private Button reservationbutton;
    private Button backtomainpage;
    private ListView listViewBasket;
    private List<Basket> menuList = new ArrayList<>();
    private List<MenuBeer> menuBeers = new ArrayList<>();
    private String url = "http://10.0.2.2:3000/basket";
    private String reservationurl="http://10.0.2.2:3000/reservation";
    private LinearLayout calendarLinear;
    private EditText actualstreet;
    private EditText city;
    private EditText postalcode;
    private LinearLayout mapLinear;
    private LinearLayout endlinear;
    private String selectedday;
    private String start_time;
    private int bicycle_id;
    /**
     * The Tobasket.
     */
    ImageButton tobasket;



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
                        bicycle_id=1;
                        break;


                    case 1:
                        bicycleimage.setImageResource(R.drawable.mediumbike);
                        bicycle_id=2;
                        break;


                    case 2:
                        bicycleimage.setImageResource(R.drawable.largebike);
                        bicycle_id=3;

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
                RequestTask requestTask = new RequestTask(url, "GET");
                requestTask.execute();
                scrollToContent(v);
            }
        });
        scroll1back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollToContentBack(v);
            }
        });

        scroll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollToContent2(v);
            }
        });
        String[] times = new String[]{"One", "Three", "Five"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, times);
        reservationTimeSpinner.setAdapter(adapter1);


        timeSlotsByDay = new HashMap<>();
        timeSlotsByDay.put(Calendar.SUNDAY,new ArrayList<>(Arrays.asList("Closed")));
        timeSlotsByDay.put(Calendar.MONDAY, new ArrayList<>(Arrays.asList("08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
                "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30","19:00","20:00","20:30","21:00","21:30")));
        timeSlotsByDay.put(Calendar.TUESDAY, new ArrayList<>(Arrays.asList("08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
                "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00")));
        timeSlotsByDay.put(Calendar.WEDNESDAY,new ArrayList<>(Arrays.asList("10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
                "13:00", "13:30", "14:00")));
        timeSlotsByDay.put(Calendar.THURSDAY,new ArrayList<>(Arrays.asList("08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
                "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00")));
        timeSlotsByDay.put(Calendar.FRIDAY,new ArrayList<>(Arrays.asList("08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
                "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00")));
        timeSlotsByDay.put(Calendar.SATURDAY,new ArrayList<>(Arrays.asList("08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
                "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30","19:00","20:00","20:30","21:00","21:30")));



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int selectedDayofWeek = getDayOfWeek(year,month,dayOfMonth);
                List<String> selectedTimeSlots = timeSlotsByDay.get(selectedDayofWeek);
                ArrayAdapter<String> timeSlotAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,selectedTimeSlots);
                timeSlotAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                timeSlotSpinner.setAdapter(timeSlotAdapter);
                selectedday = year+"-"+(month+1)+"-"+dayOfMonth;
                selectedday = String.format("%04d-%02d-%02d",year,month+1,dayOfMonth);
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);
                if(selectedDate.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
                    Toast.makeText(getContext(), "A vasárnapi nap zárva vagyunk emiatt azt nem válaszhatod!",Toast.LENGTH_SHORT).show();
                }
            }
        });







        scroll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if  (reservationTimeSpinner.getSelectedItem()==null) {
                    Toast.makeText(getContext(),"Kérlek válaszd ki hogy hány órát szeretnéd használni a biciklit.",Toast.LENGTH_SHORT).show();

                }else if
                (timeSlotSpinner.getSelectedItem() == null) {
                    Toast.makeText(getContext(), "Kérlek válassz ki egy időpontot!", Toast.LENGTH_SHORT).show();
                } else {
                    start_time = selectedday + "T" + timeSlotSpinner.getSelectedItem().toString() + ":00.000Z";
                    Toast.makeText(getContext(), "" + start_time, Toast.LENGTH_SHORT).show();
                    Log.d("valami1", ""+start_time);
                    scrollToContent3(v);
                }
            }
        });



        reservationbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reservation_time = reservationTimeSpinner.getSelectedItem().toString();
                String street= actualstreet.getText().toString();
                String citystring = city.getText().toString();
                String postcode = postalcode.getText().toString();





                if(citystring.isEmpty()){
                    Toast.makeText(getContext(),"Várost megadni kötelező!",Toast.LENGTH_SHORT).show();
                } else if (postcode.isEmpty()) {
                    Toast.makeText(getContext(),"Iránmyítószámot megadni kötelező!",Toast.LENGTH_SHORT).show();

                } else if (street.isEmpty()) {
                    Toast.makeText(getContext(),"Utcát,házszámot megadni kötelező!", Toast.LENGTH_SHORT).show();
                } else {

                    Basket basket = menuList.get(0);
                    int basket_id=basket.getId();
                    String location = citystring+", "+postcode+", "+street;
                    Reservation reservation = new Reservation(0,start_time,location,reservation_time,0,bicycle_id,basket_id);
                    Gson gson = new Gson();
                    RequestTask requestTask = new RequestTask(reservationurl,"POST", gson.toJson(reservation));
                    requestTask.execute();
                    scrollToContent4(v);
                }

            }
        });


        backtomainpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainPageActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }
    private int getDayOfWeek(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Scroll to content.
     *
     * @param view the view
     */
    public void scrollToContent(View view) {
        int scrollToY = reservationMenu.getTop(); // A megfelelő hely Y koordinátája
        Log.d("valami1:",""+reservationMenu.getTop());
        scrollView.smoothScrollTo(0, scrollToY);
    }

    /**
     * Scroll to content back.
     *
     * @param view the view
     */
    public void scrollToContentBack(View view) {
        int scrollToY = reservationText.getTop(); // A megfelelő hely Y koordinátája
        scrollView.smoothScrollTo(0, scrollToY);
    }

    /**
     * Scroll to content 2.
     *
     * @param view the view
     */
    public void scrollToContent2(View view) {
        int scrollToY = calendarLinear.getTop(); // A megfelelő hely Y koordinátája
        scrollView.smoothScrollTo(0, scrollToY);
    }

    /**
     * Scroll to content 3.
     *
     * @param view the view
     */
    public void scrollToContent3(View view) {
        int scrollToY = mapLinear.getTop(); // A megfelelő hely Y koordinátája
        scrollView.smoothScrollTo(0, scrollToY);
    }

    /**
     * Scroll to content 4.
     *
     * @param view the view
     */
    public void scrollToContent4(View view) {
        int scrollToY = endlinear.getTop(); // A megfelelő hely Y koordinátája
        scrollView.smoothScrollTo(0, scrollToY);
    }

    /**
     * Init.
     *
     * @param view the view
     */
    public void init(View view){

        listViewBasket = view.findViewById(R.id.reservationListMenu);
        scrollView = view.findViewById(R.id.reservationscroll);
        scrollView.setScrollingEnabled(false);
        reservationMenu = view.findViewById(R.id.reservationMenu);
        scroll1 = view.findViewById(R.id.scroll1);
        dropdown = view.findViewById(R.id.dropdown);
        bicycleimage = view.findViewById(R.id.imagebicycle);
        scroll1back = view.findViewById(R.id.scroll1back);
        reservationText = view.findViewById(R.id.reservationText);
        scroll2 = view.findViewById(R.id.scroll2);
        scroll3 = view.findViewById(R.id.scroll3);
        calendarView = view.findViewById(R.id.calendar);
        calendarLinear = view.findViewById(R.id.calendarLinear);
        mapLinear = view.findViewById(R.id.maplinear);
        timeSlotSpinner = view.findViewById(R.id.timeSlotSpinner);
        reservationTimeSpinner = view.findViewById(R.id.reservationTimeSpinner);
        reservationbutton= view.findViewById(R.id.reservationbutton);
        actualstreet = view.findViewById(R.id.actaulstreet);
        city = view.findViewById(R.id.city);
        postalcode = view.findViewById(R.id.postalcode);
        endlinear = view.findViewById(R.id.endpage);
        backtomainpage = view.findViewById(R.id.backtomainpage);
    }
    private class BasketAdapter extends ArrayAdapter<MenuBeer> {
        /**
         * Instantiates a new Basket adapter.
         *
         * @param context   the context
         * @param menuBeers the menu beers
         */
        public BasketAdapter(Context context, List<MenuBeer> menuBeers) {
            super(getActivity(), R.layout.list_item_menu, menuBeers);
        }

        /**
         * Instantiates a new Basket adapter.
         */
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
            ImageButton tobasket = view.findViewById(R.id.tobasketbutton);
            ImageView imageViewMenu = view.findViewById(R.id.imagetomenu);
            //actualComment létrehozása a commentsList listából
            MenuBeer actualbasket = getItem(position);
            textViewName.setText(actualbasket.getMenuName());
            textViewType.setText(actualbasket.getMenuType());
            textViewPrice.setText(String.valueOf(actualbasket.getMenuPrice()));
            tobasket.setVisibility(View.GONE);

            String menuName = actualbasket.getMenuName();
            int imageResourceId = getResources().getIdentifier(menuName.toLowerCase().replace(" ","_").replace("(","").replace(")",""), "drawable", getActivity().getPackageName());
            imageViewMenu.setImageResource(imageResourceId);
            int id = actualbasket.getId();

            Gson gson = new Gson();


            return view;
        }
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
