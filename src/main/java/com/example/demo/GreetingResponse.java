package com.example.demo;
public class GreetingResponse {
    private String client_ip;
    private String location;
    private String greeting;

    public GreetingResponse(String client_ip,String location, String greeting){
        this.client_ip = client_ip;
        this.location = location;
        this.greeting = greeting;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
