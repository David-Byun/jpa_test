package jpa.test;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS") //order by 때문에 테이블에 예약어가 걸려있으므로 orders로 테이블 이름 생성
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    //관계형 설계에 맞춘 설계로 좋지 않다.
    @Column(name = "member_id")
    private Long memberId;

    private Member member;
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
