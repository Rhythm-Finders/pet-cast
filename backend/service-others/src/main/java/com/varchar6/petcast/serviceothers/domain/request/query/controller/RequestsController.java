package com.varchar6.petcast.serviceothers.domain.request.query.controller;


import com.varchar6.petcast.serviceothers.common.response.ResponseMessage;
import com.varchar6.petcast.serviceothers.domain.request.query.dto.*;
import com.varchar6.petcast.serviceothers.domain.request.query.mapper.RequestsMapper;
import com.varchar6.petcast.serviceothers.domain.request.query.service.RequestsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("queryRequestsController")
@RequestMapping("/api/v1/requests")
public class RequestsController {
    private final RequestsService requestsService;
    private RequestsMapper requestsMapper;

    @Autowired
    public RequestsController(RequestsService requestsService, RequestsMapper requestsMapper) {
        this.requestsService = requestsService;
        this.requestsMapper = requestsMapper;
    }

    @GetMapping("/categoryList")
    public ResponseEntity<ResponseMessage> findCategoryById(@RequestParam("categoryId") Integer categoryId) {
        String message = "카테고리 조회 성공!";
        RequestCategoryResponseDTO RequestCategoryResponseDTO = null;
        try {
            RequestCategoryResponseDTO = requestsService.findCategoryById(categoryId);
        }catch (Exception e){
            message = "카테고리 조회 실패!";
        }
        return ResponseEntity.ok(ResponseMessage.builder()
                .httpStatus(HttpStatus.OK.value())
                .message(message)
                .result(RequestCategoryResponseDTO)
                .build());
    }

    // 고객 요청서 목록 조회
    @GetMapping("/list/memberRequest/{userId}")
    public ResponseEntity<ResponseMessage> findRequestsByCustomer(@RequestHeader("X-Member-Id") String id) {

        int memberId = Integer.parseInt(id);

        String message = "고객이 작성한 요청서 목록 조회 성공!";
        List<MemberAndRequestDTO> memberAndRequestDTO = null;
        try {
            memberAndRequestDTO = requestsService.findAllRequestsByMemberId(memberId);
        } catch (Exception e){
            message = "고객이 작성한 요청서 목록 조회 실패!";
        }
        return ResponseEntity.ok()
                .body(ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message(message)
                        .result(memberAndRequestDTO)
                        .build());
    }

    // 업체 요청서 목록 조회
    @GetMapping("/list/companyRequest/{companyId}")
    public ResponseEntity<ResponseMessage> findRequestsForCompany(@PathVariable int companyId) {
        String message = "업체가 받은 요청서 목록 조회 성공!";
        List<CompanyAndRequestDTO> companyAndRequestDTO = null;
        try{
            companyAndRequestDTO = requestsService.findAllRequestsByCompanyId(companyId);
        }catch (Exception e){
            message = "업체가 받은 요청서 목록 조회 실패!";
        }
        return ResponseEntity.ok()
                .body(ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message(message)
                        .result(companyAndRequestDTO)
                        .build());
    }

    // 요청서 상세 조회
    @GetMapping("/list/detail/{requestId}")
    public ResponseEntity<ResponseMessage> getRequestById(@PathVariable int requestId) {
        String message = "요청서 상세 조회 성공!";
        RequestDTO requestDetail = null;
        try {
            requestDetail = requestsService.findRequestById(requestId);
        } catch (Exception e){
            message = "요청서 상세 조회 실패!";
        }
        return ResponseEntity.ok()
                .body(ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message(message)
                        .result(requestDetail)
                        .build());
    }

}