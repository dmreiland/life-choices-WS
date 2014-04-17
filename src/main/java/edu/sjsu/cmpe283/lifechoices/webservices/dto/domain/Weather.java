package edu.sjsu.cmpe283.lifechoices.webservices.dto.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {
    
    Response response;
    Forecast forecast;
    
    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forcast) {
        this.forecast = forcast;
    }

    
    
    
    
    

    public static class Response {
        String version;
        @JsonProperty("termsofService")
        String termsOfService;
        Features features;
        
        public String getVersion() {
            return version;
        }
        
        public void setVersion(String version) {
            this.version = version;
        }
        
        public String getTermsOfService() {
            return termsOfService;
        }
        
        public void setTermsOfService(String termsOfService) {
            this.termsOfService = termsOfService;
        }
        
        public Features getFeatures() {
            return features;
        }
        
        public void setFeatures(Features features) {
            this.features = features;
        }
        
    }
    
    
    public static class Features {
        Long forecast;

        public Long getForecast() {
            return forecast;
        }

        public void setForecast(Long forecast) {
            this.forecast = forecast;
        }
        
    }
    
    
    
    
    
    
    public static class Forecast {
        @JsonProperty("txt_forecast")
        TextForecast textForecast;
        @JsonProperty("simpleforecast")
        SimpleForecast simpleForecast;
        
        public TextForecast getTextForecast() {
            return textForecast;
        }
        
        public void setTextForecast(TextForecast textForecast) {
            this.textForecast = textForecast;
        }
        
        public SimpleForecast getSimpleForecast() {
            return simpleForecast;
        }
        
        public void setSimpleForecast(SimpleForecast simpleForecast) {
            this.simpleForecast = simpleForecast;
        }
    }
    
    
    public static class TextForecast {
        String date;
        @JsonProperty("forecastday")
        List<TextForecastDay> forecastDay;
        
        public String getDate() {
            return date;
        }
        
        public void setDate(String date) {
            this.date = date;
        }
        
        public List<TextForecastDay> getForecastDay() {
            return forecastDay;
        }
        
        public void setForecastDay(List<TextForecastDay> forecastDay) {
            this.forecastDay = forecastDay;
        }
        
    }
    
    
    public static class TextForecastDay {
        Integer period;
        String icon;
        @JsonProperty("icon_url")
        String iconURL;
        String title;
        @JsonProperty("fcttext")
        String forecastText;
        
        public Integer getPeriod() {
            return period;
        }
        
        public void setPeriod(Integer period) {
            this.period = period;
        }
        
        public String getIcon() {
            return icon;
        }
        
        public void setIcon(String icon) {
            this.icon = icon;
        }
        
        public String getIconURL() {
            return iconURL;
        }
        
        public void setIconURL(String iconURL) {
            this.iconURL = iconURL;
        }
        
        public String getTitle() {
            return title;
        }
        
        public void setTitle(String title) {
            this.title = title;
        }
        
        public String getForecastText() {
            return forecastText;
        }
        
        public void setForecastText(String forecastText) {
            this.forecastText = forecastText;
        }
        
    }
    


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static class SimpleForecast {
        @JsonProperty("forecastday")
        List<SimpleForecastDay> forecastDay;

        
        public List<SimpleForecastDay> getForecastDay() {
            return forecastDay;
        }

        
        public void setForecastDay(List<SimpleForecastDay> forecastDay) {
            this.forecastDay = forecastDay;
        }
        
    }
    
    
    
    
    public static class SimpleForecastDay {
        Date date;
        Long period;
        Temperature high;
        Temperature low;
        String conditions;
        String icon;
        @JsonProperty("icon_url")
        String iconURL;
        String skyicon;
        @JsonProperty("avewind")
        Wind averageWind;
        Integer avehumidity;
        Integer maxhumidity;
        Integer minhumidity;
        
        public Date getDate() {
            return date;
        }
        
        public void setDate(Date date) {
            this.date = date;
        }
        
        public Long getPeriod() {
            return period;
        }
        
        public void setPeriod(Long period) {
            this.period = period;
        }
        
        public Temperature getHigh() {
            return high;
        }
        
        public void setHigh(Temperature high) {
            this.high = high;
        }
        
        public Temperature getLow() {
            return low;
        }
        
        public void setLow(Temperature low) {
            this.low = low;
        }
        
        public String getConditions() {
            return conditions;
        }
        
        public void setConditions(String conditions) {
            this.conditions = conditions;
        }
        
        public String getIcon() {
            return icon;
        }
        
        public void setIcon(String icon) {
            this.icon = icon;
        }
        
        public String getIconURL() {
            return iconURL;
        }
        
        public void setIconURL(String iconURL) {
            this.iconURL = iconURL;
        }
        
        public String getSkyicon() {
            return skyicon;
        }
        
        public void setSkyicon(String skyicon) {
            this.skyicon = skyicon;
        }
        
        public Wind getAverageWind() {
            return averageWind;
        }
        
        public void setAverageWind(Wind averageWind) {
            this.averageWind = averageWind;
        }
        
        public Integer getAvehumidity() {
            return avehumidity;
        }
        
        public void setAvehumidity(Integer avehumidity) {
            this.avehumidity = avehumidity;
        }
        
        public Integer getMaxhumidity() {
            return maxhumidity;
        }
        
        public void setMaxhumidity(Integer maxhumidity) {
            this.maxhumidity = maxhumidity;
        }
        
        public Integer getMinhumidity() {
            return minhumidity;
        }
        
        public void setMinhumidity(Integer minhumidity) {
            this.minhumidity = minhumidity;
        }
        
    }
    
    
    
    
    
    
    public static class Date {
        String epoch;
        Integer day;
        Integer month;
        Integer year;
        Integer hour;
        String min;
        
        @JsonProperty("tz_short")
        String timezone;
        
        public String getEpoch() {
            return epoch;
        }
        
        public void setEpoch(String epoch) {
            this.epoch = epoch;
        }
        
        public Integer getDay() {
            return day;
        }
        
        public void setDay(Integer day) {
            this.day = day;
        }
        
        public Integer getMonth() {
            return month;
        }
        
        public void setMonth(Integer month) {
            this.month = month;
        }
        
        public Integer getYear() {
            return year;
        }
        
        public void setYear(Integer year) {
            this.year = year;
        }
        
        public Integer getHour() {
            return hour;
        }
        
        public void setHour(Integer hour) {
            this.hour = hour;
        }
        
        public String getMin() {
            return min;
        }
        
        public void setMin(String min) {
            this.min = min;
        }
        
        public String getTimezone() {
            return timezone;
        }
        
        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }
        
    }
    
    
    
    
    
    public static class Temperature {
        String fahrenheit;
        String celsius;
        
        public String getFahrenheit() {
            return fahrenheit;
        }
        
        public void setFahrenheit(String fahrenheit) {
            this.fahrenheit = fahrenheit;
        }
        
        public String getCelsius() {
            return celsius;
        }
        
        public void setCelsius(String celsius) {
            this.celsius = celsius;
        }
        
    }
    
    
    
    
    public static class Wind {
        Integer mph;
        Integer kph;
        String dir;
        Integer degrees;
        
        public Integer getMph() {
            return mph;
        }
        
        public void setMph(Integer mph) {
            this.mph = mph;
        }
        
        public Integer getKph() {
            return kph;
        }
        
        public void setKph(Integer kph) {
            this.kph = kph;
        }
        
        public String getDir() {
            return dir;
        }
        
        public void setDir(String dir) {
            this.dir = dir;
        }
        
        public Integer getDegrees() {
            return degrees;
        }
        
        public void setDegrees(Integer degrees) {
            this.degrees = degrees;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}