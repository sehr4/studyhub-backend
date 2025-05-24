package com.studyhub.model;

import com.studyhub.constant.ResourceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;

    @Column(nullable = false)
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResourceType type;

    @Column(nullable = true)
    private String fileUrl; // Kept for now

    @Lob
    @Column(nullable = true)
    private byte[] fileContent; // TEST: Trying to store file bytes for FILE type

    @Column(nullable = true)
    private String originalFileName;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String content;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Resource() {
    }

    public Resource(Module module, String title, ResourceType type, String fileUrl, byte[] fileContent, String content) {
        this.module = module;
        this.title = title;
        this.type = type;
        this.fileUrl = fileUrl;
        this.fileContent = fileContent;
        this.content = content;
    }
}