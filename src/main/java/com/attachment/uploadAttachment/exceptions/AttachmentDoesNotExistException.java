package com.attachment.uploadAttachment.exceptions;

public class AttachmentDoesNotExistException extends RuntimeException{

    public AttachmentDoesNotExistException(String message) {
        super(message);
    }
}
