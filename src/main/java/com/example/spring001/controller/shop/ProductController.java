package com.example.spring001.controller.shop;

import java.io.File;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring001.model.shop.dto.ProductDTO;
import com.example.spring001.service.shop.ProductService;


@Controller
@RequestMapping("/shop/product/*")
public class ProductController {

	@Inject	// 의존관계 주입(DI)
	ProductService productService;	// 스프링에서 만든 service 객체를 연결
	
	@RequestMapping("list.do")	// 세부적인 url mapping
	public ModelAndView list(ModelAndView mav) {
		
		mav.setViewName("/shop/product_list");	// 이동할 페이지 이름
		mav.addObject("list", productService.listProduct());  // 데이터 저장
		
		return mav;	// 페이지 이동
	}
	
	@RequestMapping("/detail/{product_id}")	// 세부적인 url mapping
	public ModelAndView detail(@PathVariable("product_id") int product_id, ModelAndView mav) {
		
		mav.setViewName("/shop/product_detail");	// 이동할 페이지 이름
		mav.addObject("dto", productService.detailProduct(product_id));  // 데이터 저장
		
		return mav;	// 페이지 이동
	}
	
	@RequestMapping("write.do")
	public String write() {
		
		return "shop/product_write";
	}
	
	@RequestMapping("insert.do")
	public String insert(ProductDTO dto) {
		
		String filename = "-";	// 처음에 null 값이 들어가면 안되므로  "-" 값을 넣어놓음
		if(!dto.getFile1().isEmpty()) {	// 첨부파일이 있을때
			
			filename = dto.getFile1().getOriginalFilename();	// 기존 파일이름 가져오고
			
			String path = "D:\\JavaWebProgram\\sts3\\itexpert\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\spring001\\"
					+ "src\\main\\webapp\\WEB-INF\\views\\images\\";
			
			// 배포 디렉토리  D:\JavaWebProgram\sts3\itexpert\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\spring001\
			// 개발 디렉토리  D:\\JavaWebProgram\\sts3\\itexpert\\spring001\\src\\main\\webapp\\WEB-INF\\views\\images 
			
			try {
				new File(path).mkdir();	 // 디렉토리가 없을수도 있으니까 디렉토리 생성
				dto.getFile1().transferTo(new File(path + filename));  // 파일을 지정된 디렉토리로 저장
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dto.setPicture_url(filename);	// 이미지 이름의 경로를 적는다
		productService.insertProduct(dto);
		
		return "redirect:/shop/product/list.do";
	}
	
	@RequestMapping("edit/{product_id}")
	public ModelAndView edit(@PathVariable("product_id") int product_id, ModelAndView mav) {
		
		mav.setViewName("/shop/product_edit");
		mav.addObject("dto", productService.detailProduct(product_id));
		
		return mav;
	}
	
	@RequestMapping("update.do")
	public String update(ProductDTO dto) {
				
		String filename = "-";	// 파일이 없을경우 null 값이 들어가면 안되니까  '-' 를 붙여놓음
		if(!dto.getFile1().isEmpty()) {	// 첨부파일이 있을때
			
			filename=dto.getFile1().getOriginalFilename();	// 기존 파일이름 가져오고
			
			String path = "D:\\JavaWebProgram\\sts3\\itexpert\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\spring001\\"
					+ "src\\main\\webapp\\WEB-INF\\views\\images\\";
			
			// 배포 디렉토리  D:\JavaWebProgram\sts3\itexpert\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\spring001\
			// 개발 디렉토리  D:\\JavaWebProgram\\sts3\\itexpert\\spring001\\src\\main\\webapp\\WEB-INF\\views\\images
			
			try {
				new File(path).mkdir();	 // 디렉토리가 없을수도 있으니까 디렉토리 생성
				dto.getFile1().transferTo(new File(path+filename));  // 파일을 지정된 디렉토리로 저장
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			dto.setPicture_url(filename);	// 이미지 이름의 경로를 적는다
		
		}else {
			ProductDTO dto2 = productService.detailProduct(dto.getProduct_id());  // 새로운 첨부파일이 올라오지않고 기존정보를 그대로 쓸경우
			dto.setPicture_url(dto2.getPicture_url());  // 지금 들어갈 dto에  기존의 dto2 주소를 넣는다
		}
		
		productService.updateProduct(dto);
		
		return "redirect:/shop/product/list.do";
	}
	
	@RequestMapping("delete.do")
	public String delete(@RequestParam int product_id) {
		
		String filename = productService.fileInfo(product_id);	// 삭제할 상품의  첨부파일 이름을 가져온다
		
		if(filename != null && !filename.equals("-")) {	// 파일이 없을경우 null 값이 들어가면 안되니까  '-' 를 붙여놓음
			
			String path = "D:\\JavaWebProgram\\sts3\\itexpert\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\spring001\\"
					+ "src\\main\\webapp\\WEB-INF\\views\\images\\";
			
			// 배포 디렉토리  D:\JavaWebProgram\sts3\itexpert\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\spring001\
			// 개발 디렉토리  D:\\JavaWebProgram\\sts3\\itexpert\\spring001\\src\\main\\webapp\\WEB-INF\\views\\images
			
			File f = new File(path + filename);
			
			if(f.exists()) {	// 파일이 존재하면
				f.delete();		// 파일을 지운다
			}
		}
		
		productService.deleteProduct(product_id);
		
		return "redirect:/shop/product/list.do";
	}
}
