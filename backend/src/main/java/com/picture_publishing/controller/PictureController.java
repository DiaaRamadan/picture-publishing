package com.picture_publishing.controller;

import com.picture_publishing.entities.Picture;
import com.picture_publishing.enums.PictureStatus;
import com.picture_publishing.service.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PictureController {

    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/")
    public String getListOfPictures(Model model) {

        List<Picture> pictures = pictureService.getUnProcessPictures();
        model.addAttribute("pictures", pictures);
        return "index";
    }

    @GetMapping("/pictures/{id}")
    public String showPicture(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("picture", pictureService.getPictureById(id));
            return "picture";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }
    }

    @GetMapping("/pictures/{id}/{status}")
    public String updatePictureStatus(@PathVariable int id, @PathVariable PictureStatus status,
                                       RedirectAttributes redirectAttributes) {
        try {
            pictureService.updatePictureStatus(status, id);
            redirectAttributes.addFlashAttribute("message", "Picture was updated");
            return "redirect:/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }
    }

}
