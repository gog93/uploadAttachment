package com.attachment.uploadAttachment.services;

import com.attachment.uploadAttachment.exceptions.AttachmentDoesNotExistException;
import com.attachment.uploadAttachment.exceptions.NullAttachmentException;
import com.attachment.uploadAttachment.model.Attachment;
import com.attachment.uploadAttachment.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AttachementUploadServiceImpl implements AttachementUploadService {
    private final String upload = System.getProperty("user.home") + "/img/";

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public String uploadAttachment(Long userEmailId, MultipartFile file) {
        try {
            if (file.isEmpty() || userEmailId == null) {
                throw new NullAttachmentException("File or emailId is null");
            }
            String fileName = file.getOriginalFilename();
            byte[] uploadedFile = file.getInputStream().readAllBytes();
            Attachment savedAttachment = attachmentRepository.save(new Attachment(userEmailId, fileName, uploadedFile));

            return "File id: " + savedAttachment.getId();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Attachment> findAllaAttachments() {
        return attachmentRepository.findAll();
    }

    @Override
    public void deleteAttachment(Long attachmentId) {
        Attachment attachment = attachmentRepository.findById(attachmentId).orElseThrow(() -> new AttachmentDoesNotExistException("File not found"));
        attachmentRepository.delete(attachment);
    }

    @Override
    public Optional<Attachment> findStudentById(Long id) {
        return attachmentRepository.findById(id);
    }

    @Override
    public List<Attachment> findByEmailId(long id) {
        return attachmentRepository.findByEmailId(id);
    }

}
