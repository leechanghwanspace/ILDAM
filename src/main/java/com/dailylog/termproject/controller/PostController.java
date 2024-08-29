package com.dailylog.termproject.controller;

import com.dailylog.termproject.entity.Post;
import com.dailylog.termproject.service.PostService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String listPosts(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("posts", postService.findAll(PageRequest.of(page, 5)));
        model.addAttribute("currentPage", page);
        return "post-list";
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "post-view";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "post-form";
    }

    @PostMapping
    public String createPost(@ModelAttribute Post post, @RequestParam("image") MultipartFile image) throws IOException {
        handleImageUpload(post, image);
        postService.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "post-form";
    }

    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable("id") Long id, @ModelAttribute Post post, @RequestParam("image") MultipartFile image) throws IOException {
        post.setId(id);
        handleImageUpload(post, image);
        postService.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        postService.deleteById(id);
        return "redirect:/posts";
    }

    private void handleImageUpload(Post post, MultipartFile image) throws IOException {
        if (!image.isEmpty()) {
            // 외부 디렉토리 설정
            String uploadDir = System.getProperty("user.dir") + "/uploaded-images/";
            File uploadDirFile = new File(uploadDir);

            // 디렉토리가 존재하지 않으면 생성
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }

            String imageName = UUID.randomUUID() + "-" + image.getOriginalFilename();
            String imagePath = uploadDir + imageName;
            File destinationFile = new File(imagePath);

            // 이미지 크기 조정 (예: 400x300 픽셀)
            Thumbnails.of(image.getInputStream())
                    .size(400, 300)
                    .toFile(destinationFile);

            post.setImagePath("/uploaded-images/" + imageName);
        }
    }
}
