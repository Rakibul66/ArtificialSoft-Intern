package com.dailycalorie.cast.Forecast;
 //model class for weather
class Weather {

    private Long date; //define variable and datatype
    private String timeZone;
    private Double temp;
    private String icon;
  //generate getter and setter
    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Weather(Double temp, String icon) {
        this.temp = temp;
        this.icon = icon;
    }

    public Weather(Long date, String timeZone, Double temp, String icon) {
        this.date = date;
        this.timeZone = timeZone;
        this.temp = temp;
        this.icon = icon;
    }
}
