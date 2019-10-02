package com.example.mvvm_rxjava_realm.activities.detailsActivity.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mvvm_rxjava_realm.R;
import com.example.mvvm_rxjava_realm.activities.detailsActivity.model.DetailsModel;
import com.example.mvvm_rxjava_realm.databinding.ItemRatingsBinding;

import java.util.ArrayList;

public class RatingsAdapter extends RecyclerView.Adapter<RatingsAdapter.ViewHolder> {

    private ArrayList<DetailsModel.RatingsBean> arrayList = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public RatingsAdapter(ArrayList<DetailsModel.RatingsBean> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemRatingsBinding itemRatingsBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_ratings, parent, false);

        return new ViewHolder(itemRatingsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DetailsModel.RatingsBean ratingsBean= arrayList.get(position);
        holder.bind(ratingsBean);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemRatingsBinding itemRatingsBinding;

        public ViewHolder(ItemRatingsBinding ratingsBean) {
            super(ratingsBean.getRoot());
            this.itemRatingsBinding = ratingsBean;
        }

        private void bind(DetailsModel.RatingsBean ratingsBean) {
            this.itemRatingsBinding.setItem(ratingsBean);
            this.itemRatingsBinding.executePendingBindings();
        }

        public ItemRatingsBinding getItemRatingsBinding() {
            return itemRatingsBinding;
        }
    }
}
