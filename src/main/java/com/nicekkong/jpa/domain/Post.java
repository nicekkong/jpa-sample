package com.nicekkong.jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter@Setter
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @OneToMany(mappedBy ="post",
//                cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
                cascade = CascadeType.ALL   // 보통 Cascade 상태 ALL를 전파하는 것으로 설정한다.
            )
    private Set<Comment> comments = new HashSet<>();

    public void addComment(Comment comment) {
        this.getComments().add(comment);
        comment.setPost(this);
    }
}