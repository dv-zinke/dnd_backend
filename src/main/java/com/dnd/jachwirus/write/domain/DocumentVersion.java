package com.dnd.jachwirus.write.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DocumentVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @JsonIgnore
    public Long documentId;

    public String dataUrl;

    public LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "contributer")
    public User contributer;

    public DocumentVersion(Long documentId, String dataUrl, LocalDateTime createdAt, User contributer) {
        this.documentId = documentId;
        this.dataUrl = dataUrl;
        this.createdAt = createdAt;
        this.contributer = contributer;
    }
}
