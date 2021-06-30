package com.kazak.carrent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class SocialController {

  @RequestMapping("/instagram")
  public RedirectView instagramRedirect() {
    RedirectView redirectView = new RedirectView();
    redirectView.setUrl("https://www.instagram.com/1diaboli/");
    return redirectView;
  }

  @RequestMapping("/youtube")
  public RedirectView youtubeRedirect() {
    RedirectView redirectView = new RedirectView();
    redirectView.setUrl("https://www.youtube.com/channel/UCzZZnOjQVPWuxZC6dqZzEKw");
    return redirectView;
  }

  @RequestMapping("/viber")
  public RedirectView viberRedirect() {
    RedirectView redirectView = new RedirectView();
    redirectView.setUrl("viber://chat?number=+375293590590");
    return redirectView;
  }

  @RequestMapping("/linkedin")
  public RedirectView linkedinRedirect() {
    RedirectView redirectView = new RedirectView();
    redirectView.setUrl("https://www.linkedin.com/in/vadim-kazak-046b53208/");
    return redirectView;
  }


}
