package ru.gb.springdemo.api;

import lombok.Data;

/**
 * Запрос на выдачу
 */
@Data
public class IssueRequest {
  private long id;

  /**
   * Идентификатор читателя
   */
  private long readerId;

  /**
   * Идентификатор книги
   */
  private long bookId;

}
