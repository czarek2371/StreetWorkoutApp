package com.example.ccc.streetworkoutapp.Helper;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.ccc.streetworkoutapp.Interface.RecyclerItemTouchHelperListener;
import com.example.ccc.streetworkoutapp.ViewHolder.FavoritesViewHolder;
import com.example.ccc.streetworkoutapp.ViewHolder.PlanViewHolder;

/**
 * Created by ccc on 25.03.2018.
 */

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private RecyclerItemTouchHelperListener listener;

    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (listener != null)
            listener.onSwipe(viewHolder, direction, viewHolder.getAdapterPosition());
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof PlanViewHolder) {
            View foregroundView = ((PlanViewHolder) viewHolder).view_foreground;
            getDefaultUIUtil().clearView(foregroundView);
        } else if (viewHolder instanceof FavoritesViewHolder)
        {
            View foregroundView = ((FavoritesViewHolder) viewHolder).view_foreground;
            getDefaultUIUtil().clearView(foregroundView);
        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (viewHolder instanceof PlanViewHolder) {
            View foregroundView = ((PlanViewHolder) viewHolder).view_foreground;
            getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
        }
        else if (viewHolder instanceof FavoritesViewHolder)
        {
            View foregroundView = ((FavoritesViewHolder) viewHolder).view_foreground;
            getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX,dY,actionState,isCurrentlyActive);
        }
    }


    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null)
        {
          if (viewHolder instanceof PlanViewHolder)
          {
              View foregroundView = ((PlanViewHolder) viewHolder).view_foreground;
              getDefaultUIUtil().onSelected(foregroundView);
          }
          else if (viewHolder instanceof FavoritesViewHolder)
          {
              View foregroundView = ((FavoritesViewHolder) viewHolder).view_foreground;
              getDefaultUIUtil().onSelected(foregroundView);
          }
        }

    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (viewHolder instanceof PlanViewHolder)
        {
            View foregroundView = ((PlanViewHolder) viewHolder).view_foreground;
            getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
        }
        else if (viewHolder instanceof FavoritesViewHolder)
        {
            View foregroundView = ((FavoritesViewHolder) viewHolder).view_foreground;
            getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
        }
        }

    }


