package common;

import java.util.ArrayList;

public class CommonFunction {
	public static ArrayList<PageNavigator> createPagingNavigatorList(int pageCount, int currPage){
		ArrayList<PageNavigator> arr = new ArrayList<PageNavigator>();
		PageNavigator page;
		if(currPage > 1){
			page = new PageNavigator();
			page.setValue(1);
			page.setLabel("<<");
			page.setTitle("First Page");
			arr.add(page);
			
			page = new PageNavigator();
			page.setValue(currPage-1);
			page.setLabel("<");
			page.setTitle("Previous Page");
			arr.add(page);
		}
		
		int maxShownPageCount = Constant.pageNavigatorSize;
		
		int frontCount = (Constant.pageNavigatorSize/2);
		if(pageCount-currPage < Constant.pageNavigatorSize/2){
			frontCount += (currPage - pageCount + frontCount);
		}
		
		for(int i=currPage - frontCount ;i<currPage;i++){
			if(i > 0){
				page = new PageNavigator();
				page.setValue(i);
				page.setLabel(String.valueOf(i));
				page.setTitle("Page" + String.valueOf(i));
				arr.add(page);
				maxShownPageCount--;
			}
		}
		
		page = new PageNavigator();
		page.setValue(currPage);
		page.setLabel(String.valueOf(currPage));
		page.setTitle("");
		arr.add(page);
		maxShownPageCount--;
		
		for(int i=currPage + 1;i<=pageCount && maxShownPageCount > 0;i++){
			page = new PageNavigator();
			page.setValue(i);
			page.setLabel(String.valueOf(i));
			page.setTitle("Page " + String.valueOf(i));
			arr.add(page);
			maxShownPageCount--;
		}
		
		if(currPage < pageCount){
			page = new PageNavigator();
			page.setValue(currPage+1);
			page.setLabel(">");
			page.setTitle("Next Page");
			arr.add(page);
			
			page = new PageNavigator();
			page.setValue(pageCount);
			page.setLabel(">>");
			page.setTitle("Last Page");
			arr.add(page);
			
			
		}
		return arr;
	}
}
