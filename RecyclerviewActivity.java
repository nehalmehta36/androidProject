package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewActivity extends AppCompatActivity {
    //private List<RegisterData> moviesList1 = new ArrayList<>();
    public List<RegisterData> moviesList1;
    private RecyclerView recyclerView;
    private MAdapter mAdapter;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        db = new DatabaseHandler(this, null, null, 2);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        moviesList1 = new ArrayList<>();
        moviesList1 = db.listContacts();
        for(int i = 0;i < moviesList1.size();i++)
            Log.d("!@#","Activity: "+moviesList1.get(i).getUsername()+" "+moviesList1.get(i).getEmail()+" "+moviesList1.get(i).getPassword());
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        Log.d("sed",db.listContacts().get(3).username);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MAdapter(moviesList1);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // Row is swiped from recycler view
                // remove it from adapter

            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                // view the background view
            }
        };

// attaching the touch helper to recycler view
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

    }
    private void prepareMovieData() {

        Log.d("sed","prepared");
        //mAdapter.moviesList=db.listContacts();
        moviesList1 = db.listContacts();


        mAdapter.notifyDataSetChanged();
    }
}
