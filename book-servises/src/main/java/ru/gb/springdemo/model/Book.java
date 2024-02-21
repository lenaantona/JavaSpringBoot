package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "book")
@Schema(name = "Книга")
@Data
public class Book{

  @Id
  @Schema(name = "Индентификатор")
  private Long id;

  @Column(name = "name")
  @Schema(name = "Наимнование книги")
  private String name;
}
