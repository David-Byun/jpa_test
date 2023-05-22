package jpa.test;

import javax.persistence.*;

//jpa 사용하는 애 인식
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(length = 10, name="USERNAME")
    private String name;

    @Column(name = "TEAM_ID")
    private Long teamId;
}
