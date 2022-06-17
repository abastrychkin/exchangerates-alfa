package com.example.exchangeratesalfa.giphy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/giphygif")
public class GiphyGifController {
    @Autowired
    private GiphyService giphyService;

//    @GetMapping("/rich")
//    public GifUrl getRandomRichGif() {
//
//        GifUrl result = giphyService.getRandomRichGif();
//        return result;
//    }
//
//    @GetMapping("/broke")
//    public GifUrl getRandomBrokeGif() {
//        return giphyService.getRandomBrokeGif();
//    }
//
//    @GetMapping("/rich")
//    @ResponseBody
//    public String getRandomRichGif(RedirectAttributes attributes) {
//        String gifUrl = giphyService.getRandomRichGif().getUrl();
//        return  "<html>\n"
//                + "<header><title>"
//                + "rich"
//                + "</title></header>\n"
//                + "<body>\n"
//                + "<img src=\""+ gifUrl + "\" alt=\"rich\" />"
//                + "</body>\n"
//                + "</html>";
//    }
//
//    @GetMapping("/broke")
//    @ResponseBody
//    public String getRandomBrokeGif(RedirectAttributes attributes) {
//        String gifUrl = giphyService.getRandomBrokeGif().getUrl();
//
//        return  "<html>\n"
//                + "<header><title>"
//                + "broke"
//                + "</title></header>\n"
//                + "<body>\n"
//                + "<img src=\""+ gifUrl + "\" alt=\"broke\" />"
//                + "</body>\n"
//                + "</html>";
//    }


    @GetMapping("/rich")
    public String gifRichTemplate(Model model) {
        String gifUrl = giphyService.getRandomRichGif().getUrl();
        model.addAttribute("gifUrl", gifUrl);
        return "giftemplate";
    }

    @GetMapping("/broke")
    public String gifBrokeTemplate(Model model) {
        String gifUrl = giphyService.getRandomBrokeGif().getUrl();
        model.addAttribute("gifUrl", gifUrl);
        return "giftemplate";
    }


}
