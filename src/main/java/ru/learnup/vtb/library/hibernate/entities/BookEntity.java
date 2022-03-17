package ru.learnup.vtb.library.hibernate.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_generator")
    @SequenceGenerator(name = "book_generator", sequenceName = "books_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", length = 64)
    private String name;

    @JoinColumn(name = "author_id")
    @ManyToOne
    private AuthorEntity author;

    @Override
    public String toString() {
        return String.format("%s %s (%d)", author.getName(), name, id);
    }
}
