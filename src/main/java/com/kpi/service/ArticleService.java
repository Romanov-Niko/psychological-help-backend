package com.kpi.service;

import com.kpi.domain.Article;
import com.kpi.domain.RoleName;
import com.kpi.domain.User;
import com.kpi.dto.request.ArticleRequestDto;
import com.kpi.exception.UserNotFoundException;
import com.kpi.repository.ArticleRepository;
import com.kpi.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

  private final ArticleRepository articleRepository;

  private final UserRepository userRepository;

  public Article getById(Integer id) {
    return articleRepository
        .findById(id)
        .orElseThrow(() -> new UserNotFoundException("Article with given id was not found!"));
  }

  public List<Article> getAll() {
    return articleRepository.findAll();
  }

  public Article save(ArticleRequestDto dto) {
    User specialist =
        userRepository
            .findByIdAndRoleName(dto.getSpecialistId(), RoleName.SPECIALIST)
            .orElseThrow(
                () -> new UserNotFoundException("Specialist with given id was not found!"));
    Article article =
        Article.builder()
            .specialist(specialist)
            .dateTime(dto.getDateTime())
            .title(dto.getTitle())
            .articleText(dto.getArticleText())
            .build();
    return articleRepository.save(article);
  }

  public Article update(ArticleRequestDto dto, Integer id) {
    User specialist =
        userRepository
            .findByIdAndRoleName(dto.getSpecialistId(), RoleName.SPECIALIST)
            .orElseThrow(
                () -> new UserNotFoundException("Specialist with given id was not found!"));
    Article article =
        Article.builder()
            .id(id)
            .specialist(specialist)
            .dateTime(dto.getDateTime())
            .title(dto.getTitle())
            .articleText(dto.getArticleText())
            .build();
    return articleRepository.save(article);
  }

  public void deleteById(Integer id) {
    articleRepository.deleteById(id);
  }
}
