package com.attachment.uploadAttachment.controller;

import com.attachment.uploadAttachment.services.AttachementUploadService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping
public class AttachmentUploadController {

    @Autowired
    private AttachementUploadService attachmentService;

    //    @RequestMapping(method = RequestMethod.POST, value = "/fileUpload", consumes=
    @PostMapping("/fileUpload")
    @ResponseBody
    public  ResponseEntity<String> uploadAttachment( @RequestParam(value = "emailId")  long emailId, @RequestPart MultipartFile file) {

        return ResponseEntity.ok(attachmentService.uploadAttachment(emailId, file));
    }


    @GetMapping("/fileUpload/{id}/delete")

    public ResponseEntity<String> removeAttachment(@PathVariable(name = "id") Long id) {
        attachmentService.deleteAttachment(id);
        return ResponseEntity.noContent().build();
    }
}
