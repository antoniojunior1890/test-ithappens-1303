package com.devaj.happens.model;

import com.devaj.happens.model.enums.TypeSolicitation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Solicitation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    private Branch branch;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    private UserSystem user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    private Client client;

    @Column(length = 50, nullable = false)
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private TypeSolicitation typeSolicitation;

    public Solicitation(Branch branch, UserSystem user, Client client, String note, TypeSolicitation typeSolicitation) {
        this.branch = branch;
        this.user = user;
        this.client = client;
        this.note = note;
        this.typeSolicitation = typeSolicitation;
    }

}
