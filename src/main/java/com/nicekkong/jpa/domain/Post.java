package com.nicekkong.jpa.domain;

import lombok.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter@Setter
@ToString
@Builder @NoArgsConstructor @AllArgsConstructor
@SequenceGenerator(name="postSeq", sequenceName = "SEQ_POST", allocationSize = 1)
public class Post extends AbstractAggregateRoot<Post > {

    @Id
//    @GeneratedValue
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postSeq")
    private Long id;

    private String title;

    @Lob
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public Post publish() {
        this.registerEvent(new PostPublishedEvent(this));
        return this;
    }


}
