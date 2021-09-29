package com.example.spring001.controller.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring001.model.shop.dto.CartDTO;
import com.example.spring001.service.shop.CartService;

@Controller
@RequestMapping("/shop/cart/*")
public class CartController {

	@Inject
	CartService cartService;
	
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute CartDTO dto, HttpSession session) {	
		// product_detail.jsp의  product_id, amount가 dto에 쌓임
		
		String userid=(String) session.getAttribute("userid");
		
		if(userid==null) {	// 로그인 하지않으면 로그인페이지 이동
			
			return "redirect:/member/login.do";
		}
		
		dto.setUserid(userid);
		cartService.insert(dto);	// 장바구니 테이블에 cart_id, userid, product_id, amount가 저장됨
		
		return "redirect:/shop/cart/list.do";	// 장바구니 목록으로 이동
	}
	
	@RequestMapping("list.do")
	public ModelAndView list(HttpSession session, ModelAndView mav) {
		
		Map<String, Object> map = new HashMap<>();
		String userid = (String) session.getAttribute("userid");
		
		if(userid!=null) {
			List<CartDTO> list = cartService.listCart(userid);	// 장바구니 목록
			int sumMoney = cartService.sumMoney(userid);	// 금액합계
			int fee = sumMoney >= 30000? 0 :2500;	// 배송료 계산
			
			map.put("sumMoney", sumMoney);
			map.put("fee", fee);	// 배송료
			map.put("sum", sumMoney+fee);	// 전체금액
			map.put("list", list);	// 장바구니 목록
			map.put("count", list.size());	// 상품이 담긴 장바구니 개수
			
			mav.setViewName("shop/cart_list");	// 이동할 페이지 이름
			mav.addObject("map", map);	// 데이터 저장
			
			return mav;	// 화면이동
		
		}else {	// 로그인 X
			
			return new ModelAndView("member/login", "", null);	// (viewName, modelName, modelObject)
										
		}
	}
	
	@RequestMapping("delete.do")
	public String delete(@RequestParam int cart_id) {
		
		cartService.delete(cart_id);
		
		return "redirect:/shop/cart/list.do";
	}
	
	@RequestMapping("deleteAll.do")
	public String deleteAll(HttpSession session) {	
		
		String userid = (String) session.getAttribute("userid");
		
		if(userid != null) {
			cartService.deleteAll(userid);	// 해당 userid의 session에 있는 모든값을 지운다 
		}
		
		return "redirect:/shop/cart/list.do";
	}
	
	@RequestMapping("update.do")	 
	public String update(int[] amount, int[] cart_id, HttpSession session) {  //c:forEach 반복문이 돌면서 배열로 처리됨
		
		String userid = (String) session.getAttribute("userid");
		
		for(int i=0; i<cart_id.length; i++) {
			
			if(amount[i]==0) {	// 상품수량이 0개이면 삭제
				cartService.delete(cart_id[i]);
			}else {
				CartDTO dto = new CartDTO();
				dto.setUserid(userid);
				dto.setCart_id(cart_id[i]);
				dto.setAmount(amount[i]);
				cartService.modifyCart(dto);
			}
		}
			
		return "redirect:/shop/cart/list.do";
	}
	
	@RequestMapping("buy.do")
	public String buy() {
		
		return "";
	}
}
