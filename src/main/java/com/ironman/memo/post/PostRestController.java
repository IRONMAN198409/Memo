package com.ironman.memo.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ironman.memo.post.service.PostService;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/create")
	public Map<String, String> createMemo(
			@RequestParam("title") String title
			, @RequestParam("content") String content
			, HttpSession session) {
		
		
		// 로그인된 사용자의 user id 가져오기
		// 다형성개념: session에 있는 값을 가져올때는 down casting이 있어야 함 
		int userId = (Integer)session.getAttribute("userId");
		
		int count = postService.addPost(userId, title, content);
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1) {
			//성공
			resultMap.put("result", "success");
		} else {
			// 실패
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
}
