package me.marufsharia.covid19.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Report {
    
    @SerializedName("cases")
    @Expose
    private Integer cases;
    @SerializedName("deaths")
    @Expose
    private Integer deaths;
    @SerializedName("recovered")
    @Expose
    private Integer recovered;
    @SerializedName("active_cases")
    @Expose
    private List<Object> activeCases = null;
    @SerializedName("closed_cases")
    @Expose
    private List<Object> closedCases = null;
    @SerializedName("table")
    @Expose
    private List<List<Table>> table = null;
    
    public Integer getCases() {
        return cases;
    }
    
    public void setCases(Integer cases) {
        this.cases = cases;
    }
    
    public Integer getDeaths() {
        return deaths;
    }
    
    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }
    
    public Integer getRecovered() {
        return recovered;
    }
    
    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }
    
    public List<Object> getActiveCases() {
        return activeCases;
    }
    
    public void setActiveCases(List<Object> activeCases) {
        this.activeCases = activeCases;
    }
    
    public List<Object> getClosedCases() {
        return closedCases;
    }
    
    public void setClosedCases(List<Object> closedCases) {
        this.closedCases = closedCases;
    }
    
    public List<List<Table>> getTable() {
        return table;
    }
    
    public void setTable(List<List<Table>> table) {
        this.table = table;
    }
    
}