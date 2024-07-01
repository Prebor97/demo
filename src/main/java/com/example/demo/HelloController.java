package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@RestController
public class HelloController {
    private static final Logger logger = Logger.getLogger(HelloController.class.getName());
    private final RestTemplate template = new RestTemplate();
    private final String ipInfoToken = "ca5980614fe4f2";
    private final String openWeatherApiKey = "a2542c927f797501a1d9fd16dc6430e2";

    @GetMapping("/api/hello")
    public GreetingResponse sayHello(@RequestParam String visitor_name, HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        if (clientIp.equals("0:0:0:0:0:0:0:1")) {
            clientIp = "105.113.97.131";
        }
        String location = getLocationFromIp(clientIp);
        int temperature = getTemperatureForLocation(location);
        String greeting = String.format("Hello, %s! The temperature is %d degrees Celsius in %s" +
                "", visitor_name, temperature, location);
        return new GreetingResponse(clientIp, location, greeting);
    }
        private String getLocationFromIp(String ip){
        String apiUrl = "https://ipinfo.io/"+ip+"?token="+ipInfoToken;
        IpinfoResponse response = template.getForObject(apiUrl,IpinfoResponse.class);
            if (response == null || response.getCity() == null || response.getCity().isEmpty()) {
                logger.warning("Invalid response from IP info API or city not found for IP: " + ip);
                return "Unknown Location";
            }

            String city = response.getCity();
            logger.info("Location for IP " + ip + " is " + city);
            return city;
        }
        private int getTemperatureForLocation(String location){
        String apiUrl = String.format("http://api.openweathermap.org/data/2.5/weather?" +
                "q=%s&units=metric&appid=%s", location,openWeatherApiKey);
        WeatherResponse response = template.getForObject(apiUrl, WeatherResponse.class);

        return (int)response.getMain().getTemp();
        }
}
