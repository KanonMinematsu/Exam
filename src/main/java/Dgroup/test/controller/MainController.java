package Dgroup.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Dgroup.test.model.Student;
import Dgroup.test.service.StudentService;
import io.micrometer.common.lang.NonNull;
@Controller
public class MainController {
	@Autowired
	private StudentService studentService;

	@GetMapping("/")
	  public String index(Model model) {
	    //model.addAttribute("message", "こんにちは");
	    return "index";
	 }
	@GetMapping("/user/")
	public String student(Model model) {
	model.addAttribute("list",this.studentService.getstudentList());
		// TODO: model.addAttributeに一覧取得結果を追加
		return "student";
	}
	
	
	@GetMapping("/add/")
	public ModelAndView add(Student students, ModelAndView model) {
		model.addObject("student", students);
		model.setViewName("form");
		return model;
	}

	@PostMapping("/add/")
	public String add(@Validated @ModelAttribute @NonNull Student students, RedirectAttributes result, ModelAndView model,
			RedirectAttributes redirectAttributes) {
		try {
			this.studentService.save(students);
			redirectAttributes.addFlashAttribute("exception", "");

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("exception", e.getMessage());
		}
		return "redirect:/user/";
	}
	
	//@GetMapping("/detail/{id}")
	//public ModelAndView detail(@PathVariable(name = "id") Long id, Student students, ModelAndView model) {
		//model.addObject("list",this.studentService.get(id));
		//model.setViewName("update");
		//return model;
	//}
	
	// 編集画面を表示する
	@GetMapping("/detail/{id}")
	public String student(@PathVariable(name = "id") Long id,Model model, Student student) {
		student = studentService.getOneBook(id);
	    model.addAttribute(student);		
	    return "update";
	}
	
	 // 本の情報を更新する
    @PostMapping("/detail/{id}")
    public String update(@ModelAttribute @Validated Student student, BindingResult result,Model model) {
		
        // バリデーションエラーの場合
        if (result.hasErrors()) {
            // 編集画面に遷移
            return "update";
        }
		
        // 本を更新する
    
   
        this.studentService.update(student);
    
        // 本の一覧画面にリダイレクト
        return "redirect:/user/";
    }
	
}
