package com.gwell.crudtemplate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * @author : Sangji Lee
 * @see : https://github.com/sangdee
 * @since : 2021-03-05
 */


/**
 * Board Entity
 */
@Getter
@Setter
@Entity
@Table(name = "board")
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private LocalDateTime createDate = LocalDateTime.now();

    @NotNull
    @Size(min = 2, max = 10, message = "Please enter a name between 2 and 10 letters")
    private String name;

    @NotNull
    @Size(min = 4, max = 10, message = "Please enter a password between 2 and 10 letters")
    private String password;

    @NotNull
    @Size(min = 2, max = 100, message = "Please enter a title between 2 and 100 letters")
    private String title;

    @NotNull
    @Size(min = 2, message = "Please enter more than 2 characters for content")
    private String contents;

    @JsonIgnore
    @Column(columnDefinition = "bit(1) default 0", nullable = false, insertable = false)
    private boolean removed;
}
