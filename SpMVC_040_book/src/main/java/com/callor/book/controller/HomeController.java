package com.callor.book.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.callor.book.model.BookDTO;
import com.callor.book.service.NaverBookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

   @Qualifier("naverBookServiceV2")
   protected final NaverBookService nBookService;

   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String home() {
      return "redirect:/naver/BOOK";
   }

   @RequestMapping(value = "/not", method = RequestMethod.GET)
   public String home(@RequestParam(name = "category", required = false, defaultValue = "") String category,
         Model model) {

      // model.addAttribute("CAT",category);
      if (category.equalsIgnoreCase("BOOK")) {
         return "redirect:/book";
         
      } else if (category.equalsIgnoreCase("MOVIE")) {
         return "redirect:/movie";
         
      } else if (category.equalsIgnoreCase("NEWS")) {
         return "redirect:/news";
         
      }
      return "redirect:/book";
   }

   @RequestMapping(value = "/home", method = RequestMethod.GET)
   public String home1(@RequestParam(name = "search", required = false, defaultValue = "") String search, Model model)
         throws MalformedURLException, IOException, ParseException {

      if (search != null && !search.equals("")) {

         String queryURL = nBookService.queryURL(search.trim());
         String jsonString = nBookService.getJsonString(queryURL);
         List<BookDTO> bookList = nBookService.getNaverList(jsonString);
         model.addAttribute("BOOKS", bookList);
      }
      return "home";
   }

}