package com.puas.clientapp.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.puas.clientapp.models.History;
import com.puas.clientapp.models.dto.request.HistoryRequest;
import com.puas.clientapp.models.dto.response.ApiResponse;
import com.puas.clientapp.utils.BasicHeaderUtil;

@Service
public class HistoryService {

    @Value("${server.base.url}/history")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    public List<History> getAllHistoryByUser() {
        String url = baseUrl;
        ResponseEntity<ApiResponse<List<History>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<History>>>() {
                });
        return response.getBody().getData();
    }

    public History getByIdWithDTO(Integer id) {
        String url = baseUrl + "/" + id;
        ResponseEntity<ApiResponse<History>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<History>>() {
                });
        return response.getBody().getData();
    }

    public List<History> getAllHistoriesForAdmin() {
        String url = baseUrl + "/admin";
        ResponseEntity<ApiResponse<List<History>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<History>>>() {
                });
        return response.getBody().getData();
    }

    public History updateHistory(Integer id, HistoryRequest historyRequest) {
        String url = baseUrl + "/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + getAuthToken());

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(historyRequest.getDateString());

            // Set tanggal yang sudah di-parse ke dalam historyRequest
            historyRequest.setDate(date);

            historyRequest.setComplaintId(id);

            HttpEntity<HistoryRequest> requestEntity = new HttpEntity<>(historyRequest, headers);

            ResponseEntity<ApiResponse<History>> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<History>>() {
                    });
            return response.getBody().getData();
        } catch (ParseException  e) {
            System.err.println("Error: " + e.getMessage());
            throw new RuntimeException("Failed to update history: " + e.getMessage());
        }
    }

    public void deleteHistory(Integer id) {
        String url = baseUrl + "/" + id;
        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<ApiResponse<History>>() {
                });
    }

    public static String getAuthToken() {
        HttpHeaders headers = BasicHeaderUtil.createBasicHeader();
        return headers.getFirst(HttpHeaders.AUTHORIZATION);
    }
}
