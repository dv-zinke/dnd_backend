package com.dnd.jachwirus.write.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "`title`")
    public String title;

    @Column(name="`like`")
    public Long like = 0L;

    @Column(name = "`dis_like`")
    public Long disLike = 0L;

    @Column(name="`thumbnail_url`")
    public String thumbnailUrl = null;

    @OneToOne
    @JoinColumn(name = "last_version")
    public DocumentVersion lastVersion = null;

    @Column(name = "`view_count`")
    public Long viewCount = 0L;

    public String category = null;
}
