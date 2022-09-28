package com.zoom.zoomIntegration.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZoomMeetingSettingsDTO {

    private boolean join_before_host;
    private boolean participant_video;
    private boolean host_video;
    private String auto_recording;
    private boolean mute_upon_entry;
}
