package com.example.ccc.streetworkoutapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.ccc.streetworkoutapp.Database.Database;
import com.example.ccc.streetworkoutapp.Helper.RecyclerItemTouchHelper;
import com.example.ccc.streetworkoutapp.Interface.RecyclerItemTouchHelperListener;
import com.example.ccc.streetworkoutapp.Model.Favorites;
import com.example.ccc.streetworkoutapp.ViewHolder.FavoritesAdapter;
import com.example.ccc.streetworkoutapp.ViewHolder.FavoritesViewHolder;
import com.google.firebase.auth.FirebaseAuth;

public class FavoritesActivity extends AppCompatActivity implements RecyclerItemTouchHelperListener {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseAuth auth;

    FavoritesAdapter adapter;

    RelativeLayout rootLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        rootLayout = (RelativeLayout) findViewById(R.id.root_layout);
        auth = FirebaseAuth.getInstance();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_fav);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    /*    LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(recyclerView.getContext(),
               R.anim.layout_from_left);
        recyclerView.setHasFixedSize(true);*/

        //swipe to delete
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0,ItemTouchHelper.LEFT,this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        loadFavorites();
    }

    private void loadFavorites() {
        adapter = new FavoritesAdapter(this,new Database(this).getAllFavorites(auth.getCurrentUser().getEmail()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSwipe(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof FavoritesViewHolder)
        {
            String name = ((FavoritesAdapter)recyclerView.getAdapter()).getItem(position).getExerciseName();

            final Favorites deleteItem = ((FavoritesAdapter)recyclerView.getAdapter()).getItem(viewHolder.getAdapterPosition());
            final int deleteIndex = viewHolder.getAdapterPosition();

            adapter.removeItem(viewHolder.getAdapterPosition());
            new Database(getBaseContext()).removeFromFavorities(deleteItem.getExerciseId(), auth.getCurrentUser().getEmail());


            //make Snackbar
            Snackbar snackbar = Snackbar.make(rootLayout,name + " usuniete z ulubionych!",Snackbar.LENGTH_LONG);
            snackbar.setAction("Cofnij", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.restoreItem(deleteItem,deleteIndex);
                    new Database(getBaseContext()).addToFavorities(deleteItem);

                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
}
