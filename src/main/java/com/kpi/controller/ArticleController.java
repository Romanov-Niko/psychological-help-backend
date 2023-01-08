package com.kpi.controller;

import com.kpi.domain.Article;
import com.kpi.dto.request.ArticleRequestDto;
import com.kpi.dto.response.ArticleResponseDto;
import com.kpi.service.ArticleService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {
  private final ArticleService service;

  @GetMapping("/{id}")
  public ArticleResponseDto getById(@PathVariable Integer id) {
    return convertToDto(service.getById(id));
  }

  @GetMapping("/")
  public List<ArticleResponseDto> getAll() {
    List<Article> article = service.getAll();
    return article.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  @PostMapping("/")
  public ArticleResponseDto save(@RequestBody ArticleRequestDto dto) {
    return convertToDto(service.save(dto));
  }

  @PutMapping("/{id}")
  public ArticleResponseDto update(@RequestBody ArticleRequestDto dto, @PathVariable Integer id) {
    return convertToDto(service.update(dto, id));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Integer id) {
    service.deleteById(id);
  }

  private ArticleResponseDto convertToDto(Article article) {
    return ArticleResponseDto.builder()
        .id(article.getId())
        .specialistId(article.getSpecialist().getId())
        .dateTime(article.getDateTime())
        .title(article.getTitle())
        .articleText(article.getArticleText())
        .build();
  }
}
