package com.attachment.uploadAttachment.controller;

import com.attachment.uploadAttachment.model.Attachment;
import com.attachment.uploadAttachment.services.AttachementUploadService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class AttachmentController {
    private final AttachementUploadService attachementUploadService;
    private final String upload = System.getProperty("user.home") + "/img/";

    public AttachmentController(AttachementUploadService attachementUploadService) {
        this.attachementUploadService = attachementUploadService;
    }

    @GetMapping("/attachment/{id}")
    public String uploadFile(@PathVariable("id") long emailId, Model model) {
        List<Attachment> allAttachments = attachementUploadService.findByEmailId(emailId);
        model.addAttribute("attachments", allAttachments);
        model.addAttribute("emailId", emailId);
        return "attachment";
    }

    @GetMapping("/image")
    public void showImage(@Param("id") Long id, HttpServletResponse response, Optional<Attachment> attachment)
            throws ServletException, IOException {

        attachment = (attachementUploadService.findStudentById(id));
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif, image/pdf");
        response.getOutputStream().write(attachment.get().getFile());
        response.getOutputStream().close();
    }

    @GetMapping("/downloadfile")
    public void downloadFile(@Param("id") Long id, Model model, HttpServletResponse response) throws IOException {
        Optional<Attachment> temp = attachementUploadService.findStudentById(id);
        if (temp != null) {
            Attachment attachment = temp.get();
            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename = " + attachment.getFileName();
            response.setHeader(headerKey, headerValue);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(attachment.getFile());
            outputStream.close();
        }
    }
    @GetMapping("/attachment/{id}/delete")
    public String removeAttachment(@PathVariable(name = "id") Long id, @RequestParam("emailId") long emailId) {
        attachementUploadService.deleteAttachment(id);
        return "redirect:/attachment/"+ emailId;
    }
}

