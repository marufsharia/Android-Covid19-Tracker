package me.marufsharia.covid19.adapters;

import android.graphics.Color;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blongho.country_data.World;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.marufsharia.covid19.R;
import me.marufsharia.covid19.models.Table;

public class CovidDataRecyclerViewAdapter extends RecyclerView.Adapter<CovidDataRecyclerViewAdapter.CovidDataViewHolder> {
    private static final String TAG = "Adapter";
    List<Table> tableList;
   
    public CovidDataRecyclerViewAdapter(List<Table> tableList) {
        this.tableList = tableList;
       
        
    }
    
    @NonNull
    @Override
    public CovidDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.covid_recycler_view_row_item, parent, false);
        return new CovidDataViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull CovidDataViewHolder holder, int position) {
        Table tableRow =tableList.get(position);
        
        if (tableRow.getCountry().equals("World")) {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
            holder.imageViewCountry.setBackgroundResource(World.getWorldFlag());
            holder.txtCountryName.setText(tableRow.getCountry());
            holder.txtNewCase.setText(tableRow.getNewCases());
            holder.txtNewDeath.setText(tableRow.getNewDeaths());
            holder.txtNewRecovery.setText(tableRow.getTotalRecovered());
           
            
        } else {
            holder.imageViewCountry.setBackgroundResource(World.getFlagOf(tableRow.getCountry()));
            holder.txtCountryName.setText(tableRow.getCountry());
            holder.txtNewCase.setText(tableRow.getNewCases());
            holder.txtNewDeath.setText(tableRow.getNewDeaths());
            holder.txtNewRecovery.setText(tableRow.getTotalRecovered());
        }
    
    
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
    
    public  class CovidDataViewHolder extends RecyclerView.ViewHolder {
        
        @BindView(R.id.iv_country_flag)
        ImageView imageViewCountry;
        
        @BindView(R.id.tv_country_name)
        TextView txtCountryName;
        
        @BindView(R.id.tv_new_case)
        TextView txtNewCase;
        
        @BindView(R.id.tv_new_death)
        TextView txtNewDeath;
        
        @BindView(R.id.tv_new_recovery)
        TextView txtNewRecovery;
        
        @BindView(R.id.expandableView)
        LinearLayout expandableView;
        
        @BindView(R.id.expanded_textview)
        TextView expanded_textview;
        
        public CovidDataViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void bind(Table tableRow) {
            boolean expanded = tableRow.isExpanded();
            expandableView.setVisibility(expanded ? View.VISIBLE : View.GONE);
            expanded_textview.setText(tableRow.getCountry());
        }
        
    }
}
