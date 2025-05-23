package com.puas.serverapp.models.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentResponse {
    private Integer id;
    private String fileName;
    private String fileType;
    @JsonIgnore
    private byte[] data;
}
