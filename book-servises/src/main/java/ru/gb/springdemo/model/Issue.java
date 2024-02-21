package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Entity
@Table(name = "issue")
@Schema(name = "Выдача книги")
@Data
// @Entity
public class Issue {

  @Id
  @Schema(name = "Индентификатор")
  private long id;

  @Column(name = "bookId")
  @Schema(name = "Индентификатор книги")
  private long bookId;

  @Column(name = "readerId")
  @Schema(name = "Индентификатор читателя")
  private long readerId;

  /**
   * Дата выдачи
   */
  @Column(name = "timestamp")
  private LocalDateTime timestamp;

}
