package com.example.ccdbookingapp;
// Christos Giapitzakis 3200034
// Dimitris Louridas 3200281
//Christos Katsaros 3210070
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListActivity extends AppCompatActivity {
    private RetreiveTask retreiveTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        retreiveTask = new RetreiveTask(this);
        retreiveTask.area = intent.getStringExtra("area");
        retreiveTask.numberOfPersons = intent.getIntExtra("persons", 0);
        retreiveTask.stars = intent.getFloatExtra("rating", 0);
        retreiveTask.start = new Date(intent.getLongExtra("from", 0));
        retreiveTask.end = new Date(intent.getLongExtra("to", 0));
        retreiveTask.handler = handler;

        executor.execute(retreiveTask);
    }

    public void onRetreived() {
        ArrayList<Accommodation> accommodations = retreiveTask.accommodations;
        AccommodationsAdapter adapter = new AccommodationsAdapter(ListActivity.this, accommodations);

        ListView listView = (ListView) findViewById(R.id.custom_list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Accommodation a = retreiveTask.accommodations.get(i);
                AlertDialog.Builder alert = new AlertDialog.Builder(ListActivity.this);
                alert.setMessage("Make reservation at " + a.getRoomName());
                alert.setTitle("Reservation");
                alert.setPositiveButton("Yes", (dialogInterface, i1) -> {
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    Handler handler = new Handler(Looper.getMainLooper());

                    BookTask bookTask = new BookTask(retreiveTask.activity);
                    bookTask.accommodation = a;
                    bookTask.start = retreiveTask.start;
                    bookTask.end = retreiveTask.end;
                    bookTask.handler = handler;

                    executor.execute(bookTask);

                });
                alert.setNegativeButton("No", (dialogInterface, i1) -> {});
                alert.setCancelable(true);
                alert.create().show();

            }
        });
    }

    public void onBooked(int result) {
        if (result == 0) {
            Toast.makeText(ListActivity.this, "Reservation completed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ListActivity.this, "Reservation failed", Toast.LENGTH_SHORT).show();
        }
    }

    private class RetreiveTask implements Runnable {
        public ListActivity activity;
        public ArrayList<Accommodation> accommodations;
        public String area;
        public int numberOfPersons;
        public double stars;
        public Date start;
        public Date end;
        public Handler handler;

        public RetreiveTask(ListActivity activity) {
            this.activity = activity;
            accommodations = new ArrayList<>();
        }

        @Override
        public void run() {
            try {
                Socket socket = new Socket("10.0.2.2", 1230);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                out.writeInt(1);
                out.flush();
                out.writeUTF(area);
                out.writeObject(start);
                out.writeObject(end);
                out.writeInt(numberOfPersons);
                out.writeDouble(stars);
                out.flush();

                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                accommodations = (ArrayList<Accommodation>)in.readObject();

                socket.close();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        activity.onRetreived();
                    }
                });

            } catch (Exception e) {
                Log.e("CCDBookingApp", "exception", e);
            }
        }
    }

    private class BookTask implements Runnable {
        public ListActivity activity;
        public Accommodation accommodation;
        public Date start;
        public Date end;
        public Handler handler;

        public BookTask(ListActivity activity) {
            this.activity = activity;
        }

        @Override
        public void run() {
            try {
                Socket socket = new Socket("10.0.2.2", 1230);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                out.writeInt(2);
                out.writeUTF(accommodation.getRoomName());
                out.writeObject(start);
                out.writeObject(end);
                out.flush();

                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                int result = in.readInt();
                socket.close();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        activity.onBooked(result);
                    }
                });

            } catch (Exception e) {
                Log.e("CCDBookingApp", "exception", e);
            }
        }
    }
}