package klaeffer.web;

import java.util.List;
import javax.validation.Valid;
import klaeffer.service.Klaeff;
import klaeffer.service.KlaeffPage;
import klaeffer.service.KlaeffService;
import klaeffer.service.ProfilService;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class KlaeffController {

  private final KlaeffService service;

  public KlaeffController(KlaeffService service) {

    this.service = service;
  }

  @GetMapping("/")
  public String index(Model model,
                      OAuth2AuthenticationToken auth,
                      @RequestParam(name = "page", required = false, defaultValue = "1")
                      int page,
                      @RequestParam(name = "max", required = false, defaultValue = "10")
                      int max

  ) {
    int offset = page - 1;
    KlaeffPage list = service.getKlaeffs(offset, max);
    model.addAttribute("klaeffpage", list);
    model.addAttribute("page", page);
    model.addAttribute("max", max);
    model.addAttribute("loginname",auth.getPrincipal().getAttribute("login"));
    return "main";
  }

  @PostMapping("/")
  public String add(@Valid KlaeffForm form, RedirectAttributes attrs) {
    service.addKlaeff(form.getName(), form.getText());
    attrs.addFlashAttribute("name", form.getName());
    return "redirect:/";
  }

  @GetMapping("/profil")
  public String profilAnzeigen(OAuth2AuthenticationToken auth, Model model){
    ProfilService profilService = new ProfilService(auth);
    model.addAttribute("name", profilService.getName());
    model.addAttribute("profilBild", auth.getPrincipal().getAttribute("avatar_url"));
    model.addAttribute("githubProfilSeite",auth.getPrincipal().getAttribute("html_url"));

    return "profil";
  }
}
