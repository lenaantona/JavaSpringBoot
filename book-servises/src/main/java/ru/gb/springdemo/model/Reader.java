package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reader")
@Schema(name = "Читатель")
@Data
public class Reader {

  @Id
  @Schema(name = "Индентификатор")
  private long id;

  @Column(name = "name")
  @Schema(name = "Имя читателя")
  private String name;

}
