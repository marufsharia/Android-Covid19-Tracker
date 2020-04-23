package me.marufsharia.covid19.adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.blongho.country_data.World;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.marufsharia.covid19.R;
import me.marufsharia.covid19.models.Table;

public class CovidDataRecyclerViewAdapter extends
        RecyclerView.Adapter<CovidDataRecyclerViewAdapter.CovidDataViewHolder> implements Filterable {
    private static final String TAG = "Adapter";
    List<Table> tableList;
    List<Table> tableList2;
    
    public CovidDataRecyclerViewAdapter(List<Table> tableList) {
        this.tableList = tableList;
        this.tableList2 = tableList;
        
        
    }
    
    @NonNull
    @Override
    public CovidDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.covid_recycler_view_row_item, parent, false);
        return new CovidDataViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull CovidDataViewHolder holder, int position) {
        Table tableRow = tableList.get(position);
        holder.bind(tableRow);
        holder.itemView.setOnClickListener(v -> {
            boolean expanded = tableRow.isExpanded();
            tableRow.setExpanded(!expanded);
            notifyItemChanged(position);
        });
        
    }
    
    
    @Override
    public int getItemCount() {
        return tableList == null ? 0 : tableList.size();
    }
    
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if(charString.isEmpty())
                {
                    tableList=tableList2;
                }else
                {
                    List<Table> tableListFilter = new ArrayList<>();
                    for (Table t : tableList2) {
                        if (t.getCountry().toLowerCase().contains(charString.toLowerCase())){
                            tableListFilter.add(t);
                        }
                    }
                    tableList = tableListFilter;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = tableList;
                return filterResults;
            }
    
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                tableList = (List<Table>) results.values;
                notifyDataSetChanged();
            }
        };
    }
    
    public class CovidDataViewHolder extends RecyclerView.ViewHolder {
        
        @BindView(R.id.iv_country_flag)
        ImageView imageViewCountry;
        
        @BindView(R.id.tv_country_name)
        TextView txtCountryName;
        
        @BindView(R.id.tv_new_case)
        TextView txtNewCase;
        
        @BindView(R.id.tv_new_death)
        TextView txtNewDeath;
        
        @BindView(R.id.expandableView)
        CardView expandableView;
    
        @BindView(R.id.tv_active_case)
        TextView txtActiveCase;
    
        @BindView(R.id.tv_serious)
        TextView txtSerious;
    
        @BindView(R.id.tv_total_death)
        TextView txtTotalDeath;
    
        @BindView(R.id.tv_total_recovery)
        TextView txtTotalRecovery;
        
        @BindView(R.id.tv_total_test)
        TextView txtTotalTest;
    
        @BindView(R.id.total_case)
        TextView txtTotalCase;
        
        public CovidDataViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
    
            
        }
        
        public void bind(Table tableRow) {
            boolean expanded = tableRow.isExpanded();
            if(tableRow.getCountry().equals("World") || tableRow.getCountry().equals("Whole World"))
            {
                imageViewCountry.setBackgroundResource(R.drawable.covid_icon);
                expandableView.setVisibility(View.VISIBLE);
                tableRow.setCountry("Whole World");
            }else
            {
                imageViewCountry.setBackgroundResource(World.getFlagOf(tableRow.getCountry()));
                expandableView.setVisibility(expanded ? View.VISIBLE : View.GONE);
            }
            txtCountryName.setText(tableRow.getCountry());
            txtNewCase.setText(String.format(" New Cases : %s", (TextUtils.isEmpty(tableRow.getNewCases()) ? "-":tableRow.getNewCases() ) ));
            txtNewDeath.setText(String.format(" New Deaths : %s",(TextUtils.isEmpty(tableRow.getNewDeaths()) ? "-":tableRow.getNewDeaths() ) ));
    
           
            
            txtActiveCase.setText(String.format("Active Cases : %s",tableRow.getActiveCases()));
            txtSerious.setText(String.format("Serious : %s",tableRow.getSeriousCritical()));
            txtTotalDeath.setText(String.format("Total Deaths : %s",tableRow.getTotalDeaths()));
            txtTotalRecovery.setText(String.format("Total Recovered : %s",tableRow.getTotalRecovered()));
            txtTotalTest.setText(String.format("Total Tests : %s",tableRow.getTotalTests()));
            txtTotalCase.setText(String.format("Total Cases : %s",tableRow.getTotalCases()));
    
            txtNewCase.setSelected(true);
            txtNewDeath.setSelected(true);
            
        }
        
    }
}
