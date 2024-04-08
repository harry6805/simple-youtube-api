package com.inrech.jobs.youtube.database.dto;

import com.inrech.jobs.youtube.database.Video;
import com.inrech.jobs.youtube.database.dto.GetVideoDTO;

public class GetVideoMapper {
    public GetVideoDTO toDto(Video v){
        return new GetVideoDTO();
    }
}
