package com.varchar6.petcast.domain.request.command.application.controller;


import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.request.command.application.dto.request.CreateRequestsRequestDTO;
import com.varchar6.petcast.domain.request.command.application.dto.response.CreateRequestsResponseDTO;
import com.varchar6.petcast.domain.request.command.application.service.RequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value="commandRequestController")
@RequestMapping("/api/v1/requests")
public class RequestsController {
    private final RequestsService requestsService;

    @Autowired
    public RequestsController(RequestsService requestsService) {
        this.requestsService = requestsService;
    }

    // 요청서 작성
    @PostMapping
    public ResponseEntity<ResponseMessage> createRequest(@RequestBody CreateRequestsRequestDTO createRequestsRequestDTO,
                                                         @RequestAttribute("memberId") int memberId) {
        String message = "요청서 작성 성공!";
        try {
            requestsService.createRequest(createRequestsRequestDTO, memberId);
        } catch (Exception e) {
            message = "요청서 작성 실패!";
        }
        return ResponseEntity.ok(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.CREATED.value())
                                .message(message)
                                .build()
                );
    }

    // 요청서 삭제
    @PutMapping("/list/{requestId}")
    public ResponseEntity<ResponseMessage> deleteRequest(@PathVariable int requestId,
                                                         @RequestAttribute("memberId") int memberId) {
        String message = "요청서 삭제 성공!";
        try {
            requestsService.deleteRequest(requestId, memberId);
        } catch (Exception e){
            message = "요청서 삭제 실패!";
        }
        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.NO_CONTENT.value())
                        .message(message)
                        .build()
        );
    }

    // 요청서 수락
    @PutMapping("/list/accept/{requestId}")
    public ResponseEntity<ResponseMessage> acceptRequest(@PathVariable int requestId,
                                                         @RequestAttribute("memberId") int memberId) {
        String message = "요청서 수락 성공!";
        try {
            requestsService.acceptRequest(requestId);
        } catch (Exception e){
            message = "요청서 수락 실패!";
        }
        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.NO_CONTENT.value())
                        .message(message)
                        .build()
        );
    }

    // 요청서 거절
    @PutMapping("/list/reject/{requestId}")
    public ResponseEntity<ResponseMessage> rejectRequest(@PathVariable int requestId,
                                                                   @RequestAttribute("memberId") int memberId) {
        String message = "요청서 거절 성공!";

        CreateRequestsResponseDTO updatedRequest = requestsService.rejectRequest(requestId);
        return ResponseEntity.ok(updatedRequest);
    }
}