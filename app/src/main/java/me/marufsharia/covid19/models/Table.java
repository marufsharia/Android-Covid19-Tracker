package me.marufsharia.covid19.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table {
    
    @SerializedName("TotalCases")
    @Expose
    private String totalCases;
    @SerializedName("NewCases")
    @Expose
    private String newCases;
    @SerializedName("TotalDeaths")
    @Expose
    private String totalDeaths;
    @SerializedName("NewDeaths")
    @Expose
    private String newDeaths;
    @SerializedName("TotalRecovered")
    @Expose
    private String totalRecovered;
    @SerializedName("ActiveCases")
    @Expose
    private String activeCases;
    @SerializedName("TotalTests")
    @Expose
    private String totalTests;
    @SerializedName("Continent")
    @Expose
    private String continent;
    @SerializedName("Deaths_1M_pop")
    @Expose
    private String deaths1MPop;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("Serious_Critical")
    @Expose
    private String seriousCritical;
    @SerializedName("Tests_1M_Pop")
    @Expose
    private String tests1MPop;
    @SerializedName("TotCases_1M_Pop")
    @Expose
    private String totCases1MPop;
    
    // State of the item
    private boolean expanded;
    
    public String getTotalCases() {
        return totalCases;
    }
    
    public void setTotalCases(String totalCases) {
        this.totalCases = totalCases;
    }
    
    public String getNewCases() {
        return newCases;
    }
    
    public void setNewCases(String newCases) {
        this.newCases = newCases;
    }
    
    public String getTotalDeaths() {
        return totalDeaths;
    }
    
    public void setTotalDeaths(String totalDeaths) {
        this.totalDeaths = totalDeaths;
    }
    
    public String getNewDeaths() {
        return newDeaths;
    }
    
    public void setNewDeaths(String newDeaths) {
        this.newDeaths = newDeaths;
    }
    
    public String getTotalRecovered() {
        return totalRecovered;
    }
    
    public void setTotalRecovered(String totalRecovered) {
        this.totalRecovered = totalRecovered;
    }
    
    public String getActiveCases() {
        return activeCases;
    }
    
    public void setActiveCases(String activeCases) {
        this.activeCases = activeCases;
    }
    
    public String getTotalTests() {
        return totalTests;
    }
    
    public void setTotalTests(String totalTests) {
        this.totalTests = totalTests;
    }
    
    public String getContinent() {
        return continent;
    }
    
    public void setContinent(String continent) {
        this.continent = continent;
    }
    
    public String getDeaths1MPop() {
        return deaths1MPop;
    }
    
    public void setDeaths1MPop(String deaths1MPop) {
        this.deaths1MPop = deaths1MPop;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getSeriousCritical() {
        return seriousCritical;
    }
    
    public void setSeriousCritical(String seriousCritical) {
        this.seriousCritical = seriousCritical;
    }
    
    public String getTests1MPop() {
        return tests1MPop;
    }
    
    public void setTests1MPop(String tests1MPop) {
        this.tests1MPop = tests1MPop;
    }
    
    public String getTotCases1MPop() {
        return totCases1MPop;
    }
    
    public void setTotCases1MPop(String totCases1MPop) {
        this.totCases1MPop = totCases1MPop;
    }
    
    public boolean isExpanded() {
        return expanded;
    }
    
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
