package com.puas.clientapp.models.dto.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryRequest {
    private Date date;
    private String dateString;
    private String note;
    private Integer complaintId;
    private Integer statusId;
}
