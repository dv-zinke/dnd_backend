package com.dnd.jachwirus.write.domain.data;

import com.dnd.jachwirus.write.domain.Document;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.Valid;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UpdateDocumentParam {
    @Valid
    Long documentId;

    @Valid
    String content;
}
