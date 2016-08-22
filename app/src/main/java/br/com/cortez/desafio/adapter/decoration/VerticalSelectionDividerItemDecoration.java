package br.com.cortez.desafio.adapter.decoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.ref.WeakReference;

import br.com.cortez.desafio.ChallengeApplication;

public class VerticalSelectionDividerItemDecoration extends RecyclerView.ItemDecoration {


    WeakReference<Context> contextWeakReference;

    private int mSizeGridSpacingPx;
    private int mGridSize;

    private boolean mNeedLeftSpacing = false;

    public VerticalSelectionDividerItemDecoration(int resourceSize, int gridSize) {
        mSizeGridSpacingPx = getPixelSize(resourceSize);
        mGridSize = gridSize;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int frameWidth = (int) ((parent.getWidth() - (float) mSizeGridSpacingPx * (mGridSize - 1)) / mGridSize);

        int padding = parent.getWidth() / mGridSize - frameWidth;


        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewAdapterPosition();

        outRect.top = mSizeGridSpacingPx;

        if (itemPosition % mGridSize == 0) {

            outRect.left = 0;
            outRect.right = padding;
            mNeedLeftSpacing = true;


        } else if ((itemPosition + 1) % mGridSize == 0) {
            mNeedLeftSpacing = false;
            outRect.right = 0;
            outRect.left = padding;
        } else if (mNeedLeftSpacing) {
            mNeedLeftSpacing = false;
            outRect.left = mSizeGridSpacingPx - padding;
            if ((itemPosition + 2) % mGridSize == 0) {
                outRect.right = mSizeGridSpacingPx - padding;
            } else {
                outRect.right = mSizeGridSpacingPx / 2;
            }
        } else if ((itemPosition + 2) % mGridSize == 0) {
            mNeedLeftSpacing = false;
            outRect.left = mSizeGridSpacingPx / 2;
            outRect.right = mSizeGridSpacingPx - padding;
        } else {
            mNeedLeftSpacing = false;
            outRect.left = mSizeGridSpacingPx / 2;
            outRect.right = mSizeGridSpacingPx / 2;
        }
        outRect.bottom = 0;
    }


    private int getPixelSize(int resource) {
        return getContext().getResources().getDimensionPixelSize(resource);
    }

    private Context getContext() {

        if (contextWeakReference == null) {
            contextWeakReference = new WeakReference<Context>(ChallengeApplication.getInstance());
        }
        return contextWeakReference.get();
    }
}