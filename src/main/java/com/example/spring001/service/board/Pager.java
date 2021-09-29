package com.example.spring001.service.board;

public class Pager {

	public static final int page_scale=10;	// 페이지당 게시물 수
	public static final int block_scale=10;	// 화면당 페이지 수
	
	private int curPage;	// 현재페이지
	private int prevPage;	// 이전페이지
	private int nextPage;	// 다음페이지
	private int totPage;	// 전체 페이지 개수
	private int totBlock;	// 전체 페이지 블록 개수
	private int curBlock;	// 현재 페이지 블록
	private int prevBlock;	// 이전 페이지 블록
	private int nextBlock;	// 다음 페이지 블록
	
	// where rn between #{start} and #{end}
	private int pageBegin;	// #{start}
	private int pageEnd;	// #{end}
	
	// [이전] blockBegin 42 43 44 45 46 47 48 49 blockEnd [다음]
	private int blockBegin;  // 현재 페이지 블록의 시작번호
	private int blockEnd;	  // 현재 페이지 블록의 끝번호
	
	// 생성자
	public Pager(int count, int curPage) {  // Pager(레코드 개수, 현재 페이지 번호)
		
		curBlock=1;	 // 현재 페이지 블록 번호
		this.curPage = curPage;	// 현재 페이지 설정
		setTotPage(count);	// 전체 페이지 갯수 계산
		setPageRange();	// between #{start} and #{end}에 입력될 값 계산
		setTotBlock();  // 전체 페이지 블록 갯수 계산
		// 페이지 블록의 시작,끝 번호 계산
		setBlockRange();
	}
	
	// where rn between #{start} and #{end}에 입력될 값
	public void setPageRange() {	
		
		pageBegin=(curPage-1) * page_scale+1;	// 시작번호 = (현재페이지-1) * 페이지당 게시물수 + 1
		
		pageEnd=pageBegin + page_scale-1;		// 끝번호 = 시작번호+페이지당 게시물수 - 1 
	}
	
	
	public void setBlockRange() {	
		
		curBlock = (int) (Math.ceil( (curPage-1)/block_scale) +1);	// 현재 몇페이지가 몇번째 페이지 블록에 속하는지 계산
		
		blockBegin=(curBlock-1)*block_scale+1;	// 현재 페이지 블록의 시작 번호 계산
		blockEnd=blockBegin+block_scale-1;		// 현재 페이지 블록의 끝 번호 계산
		
		if(blockEnd > totPage) blockEnd=totPage; // 마지막 페이지번호가 범위를 초과하지 않도록 처리
		
		prevPage=(curPage==1) ? 1:(curBlock-1)*block_scale;	   // [이전]을 눌렀을때 이동할 페이지 번호
		nextPage=curBlock>totBlock ? (curBlock*block_scale):(curBlock*block_scale)+1;	// [다음]을 눌렀을때 이동할 페이지 번호
		
		if(nextPage>=totPage) nextPage=totPage;	// 마지막 페이지가 범위를 초과하지 않도록 처리
	}
		
	// getter setter
	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getTotPage() {
		return totPage;
	}

	public void setTotPage(int count) {
		// Math.ceil(실수) 올림처리
		totPage = (int) Math.ceil(count*1.0 / page_scale);
	}

	public int getTotBlock() {
		return totBlock;
	}

	public void setTotBlock() {	// 페이지의 블록의 갯수 계산 (총 100페이지라면 10개 블록)
		
		totBlock = (int) Math.ceil(totPage / block_scale);
	}

	public int getCurBlock() {
		return curBlock;
	}

	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}

	public int getPrevBlock() {
		return prevBlock;
	}

	public void setPrevBlock(int prevBlock) {
		this.prevBlock = prevBlock;
	}

	public int getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}

	public int getPageBegin() {
		return pageBegin;
	}

	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public int getBlockBegin() {
		return blockBegin;
	}

	public void setBlockBegin(int blockBegin) {
		this.blockBegin = blockBegin;
	}

	public int getBlockEnd() {
		return blockEnd;
	}

	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}

	public static int getPageScale() {
		return page_scale;
	}

	public static int getBlockScale() {
		return block_scale;
	}

	
}
