package com.attachment.uploadAttachment.services;

import com.attachment.uploadAttachment.model.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface AttachementUploadService {
    String uploadAttachment(Long userEmailId, MultipartFile file);

    void deleteAttachment(Long attachmentId);
    List<Attachment> findAllaAttachments();
   Optional<Attachment> findStudentById(Long Id);

    List<Attachment> findByEmailId(long id);
}
