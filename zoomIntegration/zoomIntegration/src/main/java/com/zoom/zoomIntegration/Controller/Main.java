package com.zoom.zoomIntegration.Controller;

import com.zoom.zoomIntegration.Entity.ZoomMeetingObjectDTO;
import com.zoom.zoomIntegration.Entity.ZoomMeetingSettingsDTO;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/zoom")
public class Main {
//    @GetMapping("/hello")
//    public String m1() {
//       return "testing_controller ";
//    }

    @PostMapping ("/create")
    public ZoomMeetingObjectDTO createMeeting(ZoomMeetingObjectDTO zoomMeetingObjectDTO) {
        String zoomUserId = "sahilaminbuhroo@gmail.com";
        String yourPass ="Buhroo@123";

        String apiUrl = "https://api.zoom.us/v2/users/" + zoomUserId + "/meetings";

        zoomMeetingObjectDTO.setPassword(yourPass);

        zoomMeetingObjectDTO.setHost_email(zoomUserId);

        ZoomMeetingSettingsDTO settingsDTO = new ZoomMeetingSettingsDTO();
        settingsDTO.setJoin_before_host(false);
        settingsDTO.setParticipant_video(true);
        settingsDTO.setHost_video(false);
        settingsDTO.setMute_upon_entry(true);
        zoomMeetingObjectDTO.setSettings(settingsDTO);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + generateZoomJWTTOken());
        headers.add("content-type", "application/json");
        HttpEntity<ZoomMeetingObjectDTO> httpEntity = new HttpEntity<ZoomMeetingObjectDTO>(zoomMeetingObjectDTO, headers);
        ResponseEntity<ZoomMeetingObjectDTO> zEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, ZoomMeetingObjectDTO.class);
        if(zEntity.getStatusCodeValue() == 201) {
            return zEntity.getBody();
        } else {
            System.out.println("unable to create zoom meeting");
        }
        return zoomMeetingObjectDTO;
    }


    private String generateZoomJWTTOken() {
        String zoomApiSecret = "yntqxRt6mi7y9Bzr71uo83uTEOuqfWWwhK7J";
        String zoomApiKey = "BE1RTW6QR12HmENyIKkWZQ";
        String id = UUID.randomUUID().toString().replace("-", "");
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        Date creation = new Date(System.currentTimeMillis());
        Date tokenExpiry = new Date(System.currentTimeMillis() + (10000 * 60));
        System.out.println(creation + " " +tokenExpiry);


        Key key = Keys
                .hmacShaKeyFor(zoomApiSecret.getBytes());
        return Jwts.builder()
                .setId(id)
                .setIssuer(zoomApiKey)
                .setIssuedAt(creation)
                .setSubject("")
                .setExpiration(tokenExpiry)
                .signWith(key, signatureAlgorithm)
                .compact();
    }
}
