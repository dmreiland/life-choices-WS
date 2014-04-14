package edu.sjsu.cmpe283.lifechoices.webservices.dto.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleMapsDirections {
    
    @JsonProperty("status")
    String status;
    @JsonProperty("routes")
    List<Routes> routes;

    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public List<Routes> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Routes> routes) {
        this.routes = routes;
    }




    public static class Point {
        Double lat;
        Double lng;
        
        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLng() {
            return lng;
        }
        
        public void setLng(Double lng) {
            this.lng = lng;
        }


        @Override
        public String toString() {
            return "Point [lat=" + lat + ", lng=" + lng + "]";
        }
    }
    
    
    
    
    public static class PolyLine {
        String points;
        
        public String getPoints() {
            return points;
        }
        
        public void setPoints(String points) {
            this.points = points;
        }

        @Override
        public String toString() {
            return "PolyLine [points=" + points + "]";
        }
        
    }
    
    public static class Distance {
        String text;
        Long value;
        
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Distance [text=" + text + ", value=" + value + "]";
        }
        
        
    }
    
    public static class Duration {
        String text;
        Long value;
        
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }
        
        @Override
        public String toString() {
            return "Duration [text=" + text + ", value=" + value + "]";
        }
        
        
    }
    
    public static class Bounds {
        Point northeast;
        Point southwest;
        
        public Point getNortheast() {
            return northeast;
        }
        
        public void setNortheast(Point northeast) {
            this.northeast = northeast;
        }
        
        public Point getSouthwest() {
            return southwest;
        }
        
        public void setSouthwest(Point southwest) {
            this.southwest = southwest;
        }

        @Override
        public String toString() {
            return "Bounds [northeast=" + northeast + ", southwest=" + southwest + "]";
        }
        
        
    }
    
    public static class Steps {
        Distance distance;
        Duration duration;
        @JsonProperty("end_location")
        Point endLocation;
        @JsonProperty("start_location")
        Point startLocation;
        @JsonProperty("travel_mode")
        String travelMode;
        @JsonProperty("html_instructions")
        String instructions;
        PolyLine polyline;
        String maneuver;
        
        
        public Distance getDistance() {
            return distance;
        }


        public void setDistance(Distance distance) {
            this.distance = distance;
        }

        
        public Duration getDuration() {
            return duration;
        }


        public void setDuration(Duration duration) {
            this.duration = duration;
        }


        public Point getEndLocation() {
            return endLocation;
        }

        
        public void setEndLocation(Point endLocation) {
            this.endLocation = endLocation;
        }


        
        public Point getStartLocation() {
            return startLocation;
        }


        public void setStartLocation(Point startLocation) {
            this.startLocation = startLocation;
        }


        
        public String getTravelMode() {
            return travelMode;
        }


        
        public void setTravelMode(String travelMode) {
            this.travelMode = travelMode;
        }


        
        public String getInstructions() {
            return instructions;
        }


        
        public void setInstructions(String instructions) {
            this.instructions = instructions;
        }


        public PolyLine getPolyline() {
            return polyline;
        }

        
        public void setPolyline(PolyLine polyline) {
            this.polyline = polyline;
        }

        public String getManeuver() {
            return maneuver;
        }

        
        public void setManeuver(String maneuver) {
            this.maneuver = maneuver;
        }


        @Override
        public String toString() {
            return "Steps [distance=" + distance + ", duration=" + duration + ", endLocation=" + endLocation + ", startLocation=" + startLocation + ", travelMode=" + travelMode + ", instructions=" + instructions + ", polyline=" + polyline
                    + "]";
        }
        
        
    }
    
    public static class Legs {
        Distance distance;
        Duration duration;
        @JsonProperty("end_address")
        String endAddress;
        @JsonProperty("start_address")
        String startAddress;
        
        @JsonProperty("end_location")
        Point endLocation;
        @JsonProperty("start_location")
        Point startLocation;
        
        List<Steps> steps;
        
        public Distance getDistance() {
            return distance;
        }

        public void setDistance(Distance distance) {
            this.distance = distance;
        }

        
        public Duration getDuration() {
            return duration;
        }

        
        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        
        public String getEndAddress() {
            return endAddress;
        }

        
        public void setEndAddress(String endAddress) {
            this.endAddress = endAddress;
        }


        public String getStartAddress() {
            return startAddress;
        }

        
        public void setStartAddress(String startAddress) {
            this.startAddress = startAddress;
        }

        
        public Point getEndLocation() {
            return endLocation;
        }


        public void setEndLocation(Point endLocation) {
            this.endLocation = endLocation;
        }

        
        public Point getStartLocation() {
            return startLocation;
        }


        public void setStartLocation(Point startLocation) {
            this.startLocation = startLocation;
        }

        
        public List<Steps> getSteps() {
            return steps;
        }


        public void setSteps(List<Steps> steps) {
            this.steps = steps;
        }


        @Override
        public String toString() {
            return "Legs [distance=" + distance + ", duration=" + duration + ", endAddress=" + endAddress + ", startAddress=" + startAddress + ", endLocation=" + endLocation + ", startLocation=" + startLocation + ", steps=" + steps + "]";
        }
        
        
    }
    
    
    
    public static class Routes {
        Bounds bounds;
        
        String copyrights;
        
        List<Legs> legs;
        
        @JsonProperty("overview_polyline")
        PolyLine polyLine;
        
        String summary;
        
        public Bounds getBounds() {
            return bounds;
        }

        public void setBounds(Bounds bounds) {
            this.bounds = bounds;
        }


        public String getCopyrights() {
            return copyrights;
        }

        public void setCopyrights(String copyrights) {
            this.copyrights = copyrights;
        }

        
        public List<Legs> getLegs() {
            return legs;
        }


        public void setLegs(List<Legs> legs) {
            this.legs = legs;
        }

        
        public PolyLine getPolyLine() {
            return polyLine;
        }

        
        public void setPolyLine(PolyLine polyLine) {
            this.polyLine = polyLine;
        }
        
        public String getSummary() {
            return summary;
        }

        
        public void setSummary(String summary) {
            this.summary = summary;
        }

        @Override
        public String toString() {
            return "Routes [bounds=" + bounds + ", copyrights=" + copyrights + ", legs=" + legs + ", polyLine=" + polyLine + ", summary=" + summary + "]";
        }
        
    }

    
    
    
    @Override
    public String toString() {
        return "GoogleMapsDirections [status=" + status + ", routes=" + routes + "]";
    }
    
    
    
    
}
