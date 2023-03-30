package com.attachment.uploadAttachment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Attachment {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private long emailId;
    @Column(nullable = false)
    private String fileName;
    @Column(nullable = false)
    private byte[] file;

    public Attachment(Long emailId, String fileName, byte[] uploadedFile) {
        this.emailId = emailId;
        this.fileName = fileName;
        this.file = uploadedFile;
    }

}
