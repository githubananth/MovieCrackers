package com.android.moviecrackers.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.moviecrackers.R;
import com.android.moviecrackers.databinding.AdapterProductionCompanyLayoutBinding;
import com.android.moviecrackers.model.moviedetails.ProductionCompany;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private Context mContext;
    private List<ProductionCompany> productionCompanyList;
    private AdapterProductionCompanyLayoutBinding productionCompanyLayoutBinding;
    private ViewHolder viewHolder;
    private ProductionCompany productionCompany;

    public CompanyAdapter(Context mContext, List<ProductionCompany> productionCompanyList) {
        this.mContext = mContext;
        this.productionCompanyList = productionCompanyList;
        this.layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        productionCompanyLayoutBinding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_production_company_layout, parent, false);
        viewHolder = new ViewHolder(productionCompanyLayoutBinding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        productionCompany = productionCompanyList.get(position);
        holder.productionCompanyLayoutBinding.setCompany(productionCompany);
    }

    @Override
    public int getItemCount() {
        return productionCompanyList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private AdapterProductionCompanyLayoutBinding productionCompanyLayoutBinding;

        ViewHolder(@NonNull AdapterProductionCompanyLayoutBinding productionCompanyLayoutBinding) {
            super(productionCompanyLayoutBinding.getRoot());
            this.productionCompanyLayoutBinding = productionCompanyLayoutBinding;
            this.productionCompanyLayoutBinding.executePendingBindings();

        }

    }
}
