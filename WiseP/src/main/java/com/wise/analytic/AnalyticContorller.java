package com.wise.analytic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnalyticContorller {
	
	@GetMapping("/board/analytic")
	public String analytic() {
		return"analytic/analytic";
	}
    @GetMapping("/board/analytic/")
    public String redirectAnalytic() {
        return "redirect:/analytic/analytic";
    }
}
