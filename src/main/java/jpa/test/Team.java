package jpa.test;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Team {

    @Id
    @GeneratedValue
    private Long id;
    private String teamName;

    /*
        연관관계 주인은 1:N의 경우 N쪽으로
        자신이 연관관계의 주인이 아닌 것을 표시하는 설정이 mappedBy
        N쪽이 항상 주인이기에 1쪽이 N쪽의 변수를 바라봐야함
     */
    @OneToMany(mappedBy = "team")
    List<Member> members = new ArrayList<>();

    public Team() {
    }
}
