package com.mysite.sbb.domain.answer.controller;

import com.mysite.sbb.domain.answer.AnswerForm;
import com.mysite.sbb.domain.answer.service.AnswerService;
import com.mysite.sbb.domain.question.Question;
import com.mysite.sbb.domain.question.service.QuestionService;
import com.mysite.sbb.domain.user.SiteUser;
import com.mysite.sbb.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id,
                               @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
        Question question = this.questionService.getQuestion(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }
        this.answerService.create(question, answerForm.getContent(), siteUser);// TODO: 답변을 저장한다.
        return String.format("redirect:/question/detail/%s", id);
    }


}
