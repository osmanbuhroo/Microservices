package com.zoom.zoomIntegration.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZoomMeetingObjectDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String uuid;

    private String host_email;

    private String registration_url;

    private String topic = "testzoom";

    private Integer type;

    private String start_time;

    private Integer duration;

    private String schedule_for;

    private String timezone;

    private String created_at;

    private String password;

    private String agenda;

    private String start_url;

    private String join_url;

    private String h323_password;

    private Integer pmi;

    private ZoomMeetingSettingsDTO settings;

}