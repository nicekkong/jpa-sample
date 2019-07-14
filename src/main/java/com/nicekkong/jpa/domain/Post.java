package com.nicekkong.jpa.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter@Setter
@ToString
@Builder @NoArgsConstructor @AllArgsConstructor
@SequenceGenerator(name="postSeq", sequenceName = "SEQ_POST", allocationSize = 1)
public class Post {

    @Id
//    @GeneratedValue
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postSeq")
    private Long id;

    private String title;

    @Lob
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;


}
