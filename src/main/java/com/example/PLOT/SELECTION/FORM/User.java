package com.example.PLOT.SELECTION.FORM;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
    @Table(name = "users", uniqueConstraints ={
            @UniqueConstraint( columnNames = {"email"})
    })
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class User {
        @Id
        @SequenceGenerator(name = "user_sequence",
                sequenceName = "user_sequence",
                allocationSize = 1
        )
        @GeneratedValue(strategy = GenerationType.SEQUENCE,
                generator = "user_sequence"
        )

        private Long userId;

        private String name;

        private String email;

        private String phone_no;

        private String typeSelection;

        private int typeCPlots;

        private int typeBPlots;

        private int typeAPlots;

        private String timestamp;
}
